package services;

import java.sql.*;

public class DbReader implements Runnable {

    private static String connectionString;
    private String query;

    public DbReader(String username, String password){
        this.connectionString="jdbc:mysql://127.0.0.1:3306/restaurant?user="+username +"&password="+password;
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

            //PROVA QUERY

            ResultSet rs = stm.executeQuery(this.query);

            if(this.query.contains(" DISHES")){
                while (rs.next()) {

                    System.out.println(rs.getString("DISH_CODE") + " " + rs.getString("DISH_NAME") + " "+ rs.getString("DISH_PRICE") +" " +rs.getString("DISH_TYPE") );
                }
            }

            if(this.query.contains(" STARTERS")){
                while (rs.next()) {
                    System.out.println(rs.getString("DISH_CODE") + " " + rs.getString("DISH_NAME") + " "+ rs.getString("DISH_PRICE") +" " +rs.getString("DISH_TYPE") );
                }
            }

            if(this.query.contains(" FIRST_COURSES")){
                while (rs.next()) {

                    System.out.println(rs.getString("DISH_CODE") + " " + rs.getString("DISH_NAME") + " "+ rs.getString("DISH_PRICE") +" " +rs.getString("DISH_TYPE") );
                }
            }

            if(this.query.contains(" MAIN_COURSES")){
                while (rs.next()) {
                    System.out.println(this.query);
                    System.out.println(rs.getString("DISH_CODE") + " " + rs.getString("DISH_NAME") + " "+ rs.getString("DISH_PRICE") +" " +rs.getString("DISH_TYPE") );
                }
            }

            if(this.query.contains(" DESSERTS")){
                while (rs.next()) {
                    System.out.println(rs.getString("DISH_CODE") + " " + rs.getString("DISH_NAME") + " "+ rs.getString("DISH_PRICE") +" " +rs.getString("DISH_TYPE") );
                }
            }

            if(this.query.contains(" DRINKS")){
                while (rs.next()) {
                    System.out.println(rs.getString("DISH_CODE") + " " + rs.getString("DISH_NAME") + " "+ rs.getString("DISH_PRICE") +" " +rs.getString("DISH_TYPE") );
                }
            }

            if(this.query.contains(" ALLERGENS_IN_DISHES")){
                while (rs.next()) {
                    System.out.println(rs.getString("ALLERGEN_CODE") + " " + rs.getString("DISH_CODE"));
                }
            }

            if(this.query.contains(" INGREDIENTS_IN_DISHES")){
                while (rs.next()) {
                    System.out.println(rs.getString("INGREDIENT_CODE") + " " + rs.getString("DISH_CODE"));
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
}
