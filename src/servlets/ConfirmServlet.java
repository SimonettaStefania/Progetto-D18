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
    Restaurant restaurant;
    {
        try {
            restaurant = Restaurant.getRestaurantInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double resCost=0;
        String addToQuery;
        DbReader dbr = new DbReader("root","root");
        Thread insertThread=new Thread(dbr);
        int reservationListSize = restaurant.getReservationList().size();
        Reservation lastReservation=null;
        if(!(restaurant.getReservationList().isEmpty())){
            lastReservation=restaurant.getReservationList().get(reservationListSize-1);
        }
        //CALCULATE RESERVATION COST
        for (Menu elem:lastReservation.getCreatedMenu()){
            resCost+=elem.getMenuCost();
        }
        lastReservation.setReservationCost(resCost);

        //ADD RESERVATION TO DATABASE
        addToQuery="('"+lastReservation.getReservationCode()+"',"+lastReservation.getnGuests()+","+lastReservation.getReservationCost() +",'"+new Date(lastReservation.getEventDate().getTime())+"','"+lastReservation.getCustomerNameSurname()+"','"+lastReservation.getCustomerMail()+"',NULL)";

        dbr.setQuery(Query.editQuery(Query.INSERT_RESERVATION,addToQuery));
        insertThread.start();
        try {
            insertThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        forwardTo(request, response, "/views/welcome.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/views/index.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }
}
