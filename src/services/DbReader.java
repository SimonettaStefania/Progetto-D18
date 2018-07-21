package services;

import menu.Allergen;
import menu.DishType;
import menu.Menu;
import menu.MenuElement;
import restaurant.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DbReader implements Runnable {

    private static DbReader dbReaderInstance;
    private String connectionString;
    private String query;
    private ArrayList<MenuElement> dishesList = new ArrayList<>();
    private ArrayList<Reservation> reservationsList = new ArrayList<>();
    private ArrayList<Menu> menuList = new ArrayList<>();
    private ArrayList<String> menuDishesCodeList = new ArrayList<>();
    private ArrayList<Allergen> allergensList = new ArrayList<>();
    private Connection connection=null;
    private Statement stm;


    private DbReader(){
        this.connectionString="jdbc:mysql://127.0.0.1:3306/RESTAURANT?useSSL=false&user=root&password=root";
    }

    @Override
    public void run() {

        try {
            setupConnection();
            chooseQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }

    private void setupConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString);
        stm = connection.createStatement();
    }

    private void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void chooseQuery() throws SQLException {
        if(this.query.contains("SELECT ")){
            executeSelectQuery();
        }else {
            if(this.query.contains("INSERT ")){
                stm.executeUpdate(this.query);
            }
            if(this.query.contains("DELETE ")){
                stm.executeUpdate(this.query);
            }
        }
    }

    private void executeSelectQuery() throws SQLException {
        if(this.query.contains(" DISHES"))
            populateCatalogue();
        else if(this.query.contains(" RESERVATIONS "))
            getReservationsFromDB();
        else if(this.query.contains(" MENUS "))
            getMenusFromDB();
        else if(this.query.contains(" DISH_IN_MENU "))
            getMenuDishesFromDB();
        else getAllAllergens();
    }

    private void populateCatalogue() throws SQLException {
        ResultSet rsDishes = stm.executeQuery(this.query);
        MenuElement tmpElem;
        while (rsDishes.next()) {
            tmpElem=new MenuElement(rsDishes.getString("DISH_NAME"),rsDishes.getString("DISH_CODE"), DishType.valueOf(rsDishes.getString("DISH_TYPE")),
                    rsDishes.getDouble("DISH_PRICE"),rsDishes.getBoolean("VEGAN"),rsDishes.getBoolean("VEGETARIAN"),rsDishes.getBoolean("CELIAC"));
            dishesList.add(tmpElem);
        }
        allergensInDishes();
        ingredientsInDishes();
    }

    private void allergensInDishes() throws SQLException {
        this.query=Query.SELECT_ALLERGENS_IN_DISHES;
        ResultSet rsAllergens = stm.executeQuery(this.query);
        while(rsAllergens.next()){
            for(MenuElement elem : dishesList){
                if(elem.getElementCode().equals(rsAllergens.getString("DISH_CODE"))){
                    elem.addAllergen(new Allergen(rsAllergens.getString("ALLERGEN_CODE"),rsAllergens.getString("ALLERGEN_DESCR")));
                }
            }
        }
    }

    private void ingredientsInDishes() throws SQLException {
        this.query=Query.SELECT_INGREDIENTS_IN_DISHES;
        ResultSet rsIngredients = stm.executeQuery(this.query);
        while(rsIngredients.next()){
            for(MenuElement elem : dishesList){
                if(elem.getElementCode().equals(rsIngredients.getString("DISH_CODE"))){
                    elem.addIngredient(rsIngredients.getString("INGREDIENT_NAME"));
                }
            }
        }
    }

    private void getReservationsFromDB() throws SQLException {
        ResultSet rsReservations = stm.executeQuery(this.query);
        while (rsReservations.next()) {
            Date eventDate = rsReservations.getTimestamp("EVENT_DATE");
            reservationsList.add(new Reservation(rsReservations.getString("RES_CODE"),rsReservations.getInt("N_GUESTS"),
                    eventDate,rsReservations.getString("CUSTOMER_NAME_SURNAME"),rsReservations.getString("CUSTOMER_EMAIL")));
        }
    }

    private void getMenusFromDB() throws SQLException {
        ResultSet rsMenus = stm.executeQuery(this.query);
        while(rsMenus.next()){
            menuList.add(new Menu(rsMenus.getString("MENU_NAME"),rsMenus.getInt("MENU_N_GUESTS")));
        }
    }

    private void getMenuDishesFromDB() throws SQLException {
        ResultSet rsMenuDishes= stm.executeQuery(this.query);
        while(rsMenuDishes.next()){
            menuDishesCodeList.add(rsMenuDishes.getString("DISH_CODE"));
        }
    }

    private void getAllAllergens() throws SQLException {
        ResultSet rsAllergens = stm.executeQuery(this.query);
        while(rsAllergens.next())
            allergensList.add(new Allergen(rsAllergens.getString("ALLERGEN_CODE"),rsAllergens.getString("ALLERGEN_DESCR")));
    }



    public void setQuery(String query) {
        this.query = query;
    }

    public ArrayList<MenuElement> getDishesList() {
        return dishesList;
    }

    public ArrayList<Reservation> getReservationsList() {
        return reservationsList;
    }

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }

    public ArrayList<String> getMenuDishesCodeList() {
        return menuDishesCodeList;
    }

    public ArrayList<Allergen> getAllergensList() {
        return allergensList;
    }

    public static synchronized DbReader getDbReaderInstance(){
        if(dbReaderInstance==null)
            dbReaderInstance =  new DbReader();
        return dbReaderInstance;
    }
}
