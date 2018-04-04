import java.sql.*;

public class Tester {
    public static void main(String[] args) {
        String connectionString="jdbc:mysql://127.0.0.1:3306/restaurant?user=esame&password=123456";

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
            ResultSet rs = stm.executeQuery("SELECT * FROM ALLERGENS");
            while (rs.next()) {
                System.out.println(rs.getString("ALLERGEN_CODE") + " " + rs.getString("ALLERGEN_DESCR"));
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
}
