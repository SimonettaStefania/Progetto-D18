package servlets;

import menu.Menu;
import restaurant.Reservation;
import restaurant.Restaurant;
import services.DbReader;
import services.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "ConfirmServlet", urlPatterns = "/confirm")
public class ConfirmServlet extends HttpServlet {
    private Restaurant restaurant;
    {
        try {
            restaurant = Restaurant.getRestaurantInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private DbReader dbr=DbReader.getDbReaderInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        insertReservationInDB();
        forwardTo(request, response, "/views/confirmPage.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/index.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }

    private void insertReservationInDB(){
        String addToQuery;
        Thread insertThread=new Thread(dbr);
        Reservation lastReservation=restaurant.getLastReservation();

        addToQuery="('"+lastReservation.getReservationCode()+"',"+lastReservation.getnGuests()+","+lastReservation.getReservationCost() +",'"+new Date(lastReservation.getEventDate().getTime())+"','"+lastReservation.getCustomerNameSurname()+"','"+lastReservation.getCustomerMail()+"',NULL)";
        dbr.setQuery(Query.editQuery(Query.INSERT_RESERVATION,addToQuery));
        insertThread.start();
        try {
            insertThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
