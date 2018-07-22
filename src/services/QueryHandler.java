package services;

import menu.*;
import restaurant.Catalogue;
import restaurant.Reservation;
import restaurant.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

    private final String connectionString = "jdbc:mysql://127.0.0.1:3306/RESTAURANT?useSSL=false&user=progettoD18&password=progettoD18";
    private Connection connection = null;
    private Statement stm;

    void setupConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            stm = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void closeConnection() {
        try {
            connection.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

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


    private void allergensInDishes(ArrayList<MenuElement> dishesList) throws SQLException {
        ResultSet rsAllergens = stm.executeQuery(SELECT_ALLERGENS_IN_DISHES);

        while (rsAllergens.next()){
            for (MenuElement elem : dishesList){
                if(elem.getElementCode().equals(rsAllergens.getString("DISH_CODE")))
                    elem.addAllergen(new Allergen(rsAllergens.getString("ALLERGEN_CODE"),rsAllergens.getString("ALLERGEN_DESCR")));
            }
        }
    }

    private void ingredientsInDishes(ArrayList<MenuElement> dishesList) throws SQLException {
        ResultSet rsIngredients = stm.executeQuery(SELECT_INGREDIENTS_IN_DISHES);

        while (rsIngredients.next()){
            for(MenuElement elem : dishesList){
                if(elem.getElementCode().equals(rsIngredients.getString("DISH_CODE")))
                    elem.addIngredient(rsIngredients.getString("INGREDIENT_NAME"));
            }
        }
    }

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

    private void readMenus(ArrayList<Reservation> reservationsList) throws SQLException {
        for (Reservation reservation : reservationsList) {
            String addToQuery = "WHERE RES_CODE ='" + reservation.getReservationCode() + "' ORDER BY MENU_CODE";
            ResultSet rsMenus = stm.executeQuery(SELECT_MENU + addToQuery);

            while(rsMenus.next())
                reservation.addMenu(new Menu(rsMenus.getString("MENU_NAME"), rsMenus.getInt("MENU_N_GUESTS")));

            readMenuDishes(reservation);
        }
    }

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
        }
    }

    void insertReservationInDB(Reservation reservation) {
        String addToQuery = String.format("('%s',%d,%s,'%s','%s','%s',NULL)", reservation.getReservationCode(),
                reservation.getnGuests(), reservation.getReservationCost(), new java.sql.Date(reservation.getEventDate().getTime()),
                reservation.getCustomerNameSurname(), reservation.getCustomerMail());

        try {
            stm.executeUpdate(INSERT_RESERVATION + addToQuery);
            insertMenus(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertMenus(Reservation reservation) throws SQLException {
        int arraySize = reservation.getCreatedMenu().size();
        Locale.setDefault(Locale.US);

        for (int menuIndex = 0; menuIndex < arraySize; menuIndex++){
            Menu currentMenu = reservation.getCreatedMenu().get(menuIndex);

            String addToQuery = String.format("('%s','%s','%s',%d)", reservation.getReservationCode(),
                    menuIndex, currentMenu.getName(), currentMenu.getnMenuGuests());
            stm.executeUpdate(INSERT_MENU + addToQuery);

            insertMenuDishes(reservation, currentMenu, menuIndex);
        }
    }

    private void insertMenuDishes(Reservation reservation, Menu menu, int menuIndex) throws SQLException{
        for (MenuElement elem : menu.getMenuElementsList()) {
            String addToQuery = String.format("('%s',%s,'%s')", reservation.getReservationCode(),
                    menuIndex, elem.getElementCode());

            stm.executeUpdate(INSERT_DISH_IN_MENU + addToQuery);
        }
    }

     void deleteReservation (String resToDeleteCode) {
        String addToQuery = "WHERE RES_CODE='" + resToDeleteCode + "'";

         try {
             stm.executeUpdate(DELETE_RESERVATION + addToQuery);
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }
}
