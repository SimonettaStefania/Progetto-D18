package services;

import menu.*;
import restaurant.Catalogue;
import restaurant.Reservation;
import restaurant.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Class made to handle and execute queries on local mysql database.
 * It is described by:
 * - multiple String attributes, each one contains a complete query or a part of a it
 * - a Statement object which is used to execute sql query and return the result which the query produces
 * - a Connection object that represents the connection with the database
 */
class QueryHandler {
    private static final String SELECT_ALL_DISHES = "SELECT * FROM DISHES";
    private static final String SELECT_ALL_ALLERGENS = "SELECT * FROM ALLERGENS";
    private static final String SELECT_ALLERGENS_IN_DISHES = "SELECT * FROM ALLERGENS_IN_DISHES NATURAL JOIN ALLERGENS";
    private static final String SELECT_INGREDIENTS_IN_DISHES = "SELECT * FROM INGREDIENTS_IN_DISHES NATURAL JOIN INGREDIENTS";
    private static final String INSERT_RESERVATION = "INSERT INTO RESERVATIONS VALUES ";
    private static final String SELECT_RESERVATION = "SELECT * FROM  RESERVATIONS ";
    private static final String DELETE_RESERVATION = "DELETE FROM  RESERVATIONS ";
    private static final String INSERT_MENU = "INSERT INTO MENUS VALUES ";
    private static final String SELECT_MENU = "SELECT * FROM MENUS ";
    private static final String INSERT_DISH_IN_MENU = "INSERT INTO DISH_IN_MENU VALUES ";
    private static final String SELECT_DISH_IN_MENU = "SELECT * FROM DISH_IN_MENU ";

    private Connection connection = null;
    private Statement stm;

    /**
     * Method that creates a new connection with the mysql database
     */

    void setupConnection() {
        String connectionString = "jdbc:mysql://127.0.0.1:3306/RESTAURANT?useSSL=false&user=progettoD18&password=progettoD18";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            stm = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that closes the connection with the mysql database
     */

    void closeConnection() {
        try {
            connection.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that execute a query which selects everything from the "DISHES" table from the database and
     * calls allergensInDishes() and ingredientsInDishes() methods.
     * * @return dishesList contains a list of MenuElement created with the values contained in the database
     */

    ArrayList<MenuElement> readCatalogue() {
        ArrayList<MenuElement> dishesList = new ArrayList<>();

        try {
            ResultSet rsDishes = stm.executeQuery(SELECT_ALL_DISHES);
            MenuElement tmpElem;

            while (rsDishes.next()) {
                tmpElem = new MenuElement(rsDishes.getString("DISH_NAME"),rsDishes.getString("DISH_CODE"),
                        DishType.valueOf(rsDishes.getString("DISH_TYPE")), rsDishes.getDouble("DISH_PRICE"),
                        rsDishes.getBoolean("VEGAN"),rsDishes.getBoolean("VEGETARIAN"),rsDishes.getBoolean("CELIAC"));
                dishesList.add(tmpElem);
            }

            allergensInDishes(dishesList);
            ingredientsInDishes(dishesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishesList;
    }

    /**
     * Method that execute a query which select everything from the "ALLERGENS_IN_DISHES" table
     * and associate each allergen to a dish contained in dishesList.
     * @param dishesList list of MenuElement created with readCatalogue() method
     * @throws SQLException Exception coming from an SQL problem
     */

    private void allergensInDishes(ArrayList<MenuElement> dishesList) throws SQLException {
        ResultSet rsAllergens = stm.executeQuery(SELECT_ALLERGENS_IN_DISHES);

        while (rsAllergens.next()){
            for (MenuElement elem : dishesList){
                if(elem.getElementCode().equals(rsAllergens.getString("DISH_CODE")))
                    elem.addAllergen(new Allergen(rsAllergens.getString("ALLERGEN_CODE"),rsAllergens.getString("ALLERGEN_DESCR")));
            }
        }
    }

    /**
     * Method that execute a query which select everything from the "INGREDIENTS_IN_DISHES" table
     * and associate each ingredient to a dish contained in dishesList.
     * @param dishesList list of MenuElement created with readCatalogue() method
     * @throws SQLException  Exception coming from an SQL problem
     */

    private void ingredientsInDishes(ArrayList<MenuElement> dishesList) throws SQLException {
        ResultSet rsIngredients = stm.executeQuery(SELECT_INGREDIENTS_IN_DISHES);

        while (rsIngredients.next()){
            for(MenuElement elem : dishesList){
                if(elem.getElementCode().equals(rsIngredients.getString("DISH_CODE")))
                    elem.addIngredient(rsIngredients.getString("INGREDIENT_NAME"));
            }
        }
    }

    /**
     * Method that execute a query which select everything from the "ALLERGENS" table from the database.
     * @return allergensList contains a list of Allergen created with the values contained in the database
     */

    ArrayList<Allergen> readAllergens() {
        ArrayList<Allergen> allergensList = new ArrayList<>();

        try {
            ResultSet rsAllergens = stm.executeQuery(SELECT_ALL_ALLERGENS);

            while(rsAllergens.next())
                allergensList.add(new Allergen(rsAllergens.getString("ALLERGEN_CODE"),rsAllergens.getString("ALLERGEN_DESCR")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allergensList;
    }

    /**
     * Method that execute a query which select everything from the "RESERVATIONS" table from the database.
     * @return reservationsList contains a list of Reservation created with the values contained in the database
     */

    ArrayList<Reservation> readReservations() {
        ArrayList<Reservation> reservationsList = new ArrayList<>();

        try {
            ResultSet rsReservations = stm.executeQuery(SELECT_RESERVATION);

            while (rsReservations.next()) {
                Date eventDate = rsReservations.getTimestamp("EVENT_DATE");
                reservationsList.add(new Reservation(rsReservations.getString("RES_CODE"), rsReservations.getInt("N_GUESTS"),
                        eventDate, rsReservations.getString("CUSTOMER_NAME_SURNAME"), rsReservations.getString("CUSTOMER_EMAIL")));
            }

            readMenus(reservationsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationsList;
    }

    /**
     Method which reads from "MENUS" table and create, with the values contained in the database, a new Menu which is
     added to the Reservation it belongs to
     */

    private void readMenus(ArrayList<Reservation> reservationsList) throws SQLException {
        for (Reservation reservation : reservationsList) {
            String addToQuery = "WHERE RES_CODE ='" + reservation.getReservationCode() + "' ORDER BY MENU_CODE";
            ResultSet rsMenus = stm.executeQuery(SELECT_MENU + addToQuery);

            while(rsMenus.next())
                reservation.addMenu(new Menu(rsMenus.getString("MENU_NAME"), rsMenus.getInt("MENU_N_GUESTS")));

            readMenuDishes(reservation);
        }
    }

    /**
     * Method that execute a query which select everything from the "DISH_IN_MENU" table
     * and associate each dish to a Menu of a specific reservation..
     * @param reservation which contains the list of the menus that will be populated
     * @throws SQLException  Exception coming from an SQL problem
     */

    private void readMenuDishes(Reservation reservation) throws SQLException {
        Catalogue catalogue = Restaurant.getRestaurantInstance().getDishesCatalogue();
        int arraySize = reservation.getCreatedMenu().size();

        for (int menuIndex = 0; menuIndex < arraySize; menuIndex++) {
            Menu currentMenu = reservation.getCreatedMenu().get(menuIndex);

            String addToQuery = "WHERE (MENU_CODE='" + menuIndex + "' AND RES_CODE='" + reservation.getReservationCode() + "')";
            ResultSet rsMenuDishes = stm.executeQuery(SELECT_DISH_IN_MENU + addToQuery);

            while(rsMenuDishes.next()) {
                MenuElement item = catalogue.getElementByCode(rsMenuDishes.getString("DISH_CODE"));
                currentMenu.addElement(item);
            }

            currentMenu.sortMenuElements();
        }
    }

    /**
     * Method that execute a query which insert a specific Reservation into "RESERVATION" table.
     * @param reservation is the reservation to insert in the database
     */

    void insertReservationInDB(Reservation reservation) {
        String addToQuery = String.format("('%s',%d,%s,'%s',(?),(?))", reservation.getReservationCode(),
                reservation.getnGuests(), reservation.getReservationCost(), new java.sql.Date(reservation.getEventDate().getTime()));

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_RESERVATION + addToQuery);
            statement.setString(1, reservation.getCustomerNameSurname());
            statement.setString(2, reservation.getCustomerMail());
            statement.executeUpdate();

            insertMenus(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that insert the menus of a reservation into "MENUS" table.
     * @param reservation is the reservation which contains the menus to insert
     * @throws SQLException  Exception coming from an SQL problem
     */

    private void insertMenus(Reservation reservation) throws SQLException {
        int arraySize = reservation.getCreatedMenu().size();
        Locale.setDefault(Locale.US);

        for (int menuIndex = 0; menuIndex < arraySize; menuIndex++){
            Menu currentMenu = reservation.getCreatedMenu().get(menuIndex);

            String addToQuery = String.format("('%s','%s',(?),%d)", reservation.getReservationCode(),
                    menuIndex, currentMenu.getnMenuGuests());

            PreparedStatement statement = connection.prepareStatement(INSERT_MENU + addToQuery);
            statement.setString(1, currentMenu.getName());
            statement.executeUpdate();

            insertMenuDishes(reservation, currentMenu, menuIndex);
        }
    }

    /**
     * Method that populate "DISH_IN_MENU" table
     * @param reservation is the reservation which contains the menu
     * @param menu is the menu
     * @param menuIndex is the position of the menu in the ArrayList
     * @throws SQLException  Exception coming from an SQL problem
     */
    private void insertMenuDishes(Reservation reservation, Menu menu, int menuIndex) throws SQLException{
        for (MenuElement elem : menu.getMenuElementsList()) {
            String addToQuery = String.format("('%s',%s,'%s')", reservation.getReservationCode(),
                    menuIndex, elem.getElementCode());

            stm.executeUpdate(INSERT_DISH_IN_MENU + addToQuery);
        }
    }

    /**
     * Method that execute a query which deletes a specific reservation from "RESERVATIONS" table
     * @param resToDeleteCode is the code of the reservation to delete
     */
     void deleteReservation (String resToDeleteCode) {
        String addToQuery = "WHERE RES_CODE='" + resToDeleteCode + "'";

         try {
             stm.executeUpdate(DELETE_RESERVATION + addToQuery);
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }
}
