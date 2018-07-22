package servlets;

import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmServlet", urlPatterns = "/confirm")
public class ConfirmServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/confirmPage.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        Restaurant.getRestaurantInstance().insertReservation(reservation);
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, INDEX_ROUTE);
    }
}
