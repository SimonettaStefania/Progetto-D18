package servlets;

import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "ConfirmServlet", urlPatterns = "/confirm")
public class ConfirmServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/confirmPage.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        confirmReservation(request.getSession());
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, INDEX_ROUTE);
    }

    private void confirmReservation(HttpSession session) {
        Reservation reservation = (Reservation) session.getAttribute("reservation");

        Restaurant restaurant = Restaurant.getRestaurantInstance();
        restaurant.insertReservation(reservation);
    }
}
