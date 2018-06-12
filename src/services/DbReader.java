package services;

import menu.Allergen;
import menu.DishType;
import menu.MenuElement;
import restaurant.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DbReader implements Runnable {

    private static String connectionString;
    private String query;
    private ArrayList<MenuElement> dishesList = new ArrayList<>();
    private ArrayList<Allergen> allergensDishesList = new ArrayList<>();
    private ArrayList<Reservation> reservationsList = new ArrayList<>();


    public DbReader(String username, String password){
        this.connectionString="jdbc:mysql://127.0.0.1:3306/restaurant?useSSL=false&user="+username +"&password="+password;
    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
            Statement stm = connection.createStatement();

            if(this.query.contains("SELECT ")){

                if(this.query.contains(" DISHES")){
                    ResultSet rsDishes = stm.executeQuery(this.query);
                    MenuElement tmpElem;
                    while (rsDishes.next()) {
                        tmpElem=new MenuElement(rsDishes.getString("DISH_NAME"),rsDishes.getString("DISH_CODE"), DishType.valueOf(rsDishes.getString("DISH_TYPE")),
                                rsDishes.getDouble("DISH_PRICE"),rsDishes.getBoolean("VEGAN"),rsDishes.getBoolean("VEGETARIAN"),rsDishes.getBoolean("CELIAC"));
                        dishesList.add(tmpElem);
                    }
                    this.query=Query.SELECT_ALLERGENS_IN_DISHES;
                    ResultSet rsAllergens = stm.executeQuery(this.query);
                    while(rsAllergens.next()){
                        for(MenuElement elem : dishesList){
                            if(elem.getElementCode().equals(rsAllergens.getString("DISH_CODE"))){
                                elem.addAllergen(new Allergen(rsAllergens.getString("ALLERGEN_CODE"),rsAllergens.getString("ALLERGEN_DESCR")));
                            }
                        }
                    }
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

                if(this.query.contains(" RESERVATIONS")){
                    ResultSet rsReservations = stm.executeQuery(this.query);
                    while (rsReservations.next()) {
                        Date eventDate = rsReservations.getTimestamp("EVENT_DATE");
                        reservationsList.add(new Reservation(rsReservations.getString("RES_CODE"),rsReservations.getInt("N_GUESTS"),
                                eventDate,rsReservations.getString("CUSTOMER_NAME_SURNAME"),rsReservations.getString("CUSTOMER_EMAIL")));
                    }
                }
            }else {
                if(this.query.contains("INSERT ")){
                    if(this.query.contains(" RESERVATIONS ")){
                        stm.executeUpdate(this.query);
                        //System.out.println("Insert OK");
                    }
                }
                if(this.query.contains("DELETE ")){
                    if(this.query.contains(" RESERVATIONS ")){
                        stm.executeUpdate(this.query);
                        //System.out.println("Delete OK");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // gestione errore in chiusura
            }
        }
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
}
