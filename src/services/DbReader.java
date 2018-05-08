package services;

import menu.DishType;
import menu.MenuElement;
import java.sql.*;
import java.util.ArrayList;

public class DbReader implements Runnable {

    private static String connectionString;
    private String query;
    private ArrayList<MenuElement> dishesList = new ArrayList<>();


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

            ResultSet rs = stm.executeQuery(this.query);

            if(this.query.contains(" DISHES")){
                MenuElement tmpElem;
                while (rs.next()) {
                    tmpElem=new MenuElement(rs.getString("DISH_NAME"),rs.getString("DISH_CODE"), DishType.valueOf(rs.getString("DISH_TYPE")),
                            rs.getDouble("DISH_PRICE"),rs.getBoolean("VEGAN"),rs.getBoolean("VEGETARIAN"),rs.getBoolean("CELIAC"));
                    dishesList.add(tmpElem);
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
}
