package servlets;

import restaurant.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ReviewServlet", urlPatterns = "/reservations")
public class ReviewServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/reservations.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("confirm"))
            searchReservations(request);
        else if (action.equals("delete"))
            deleteReservation(request);

        forwardTo(request, response, DEFAULT_ROUTE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    private void searchReservations (HttpServletRequest request) {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        String email = request.getParameter("email");
        String code = request.getParameter("res-id");

        ArrayList<Reservation> resList = restaurant.getReservationList();
        for (Reservation r : resList)
            if (code.equalsIgnoreCase(r.getReservationCode()) && email.equalsIgnoreCase(r.getCustomerMail()))
                request.setAttribute("pickedReservation", r);
    }

    private void deleteReservation (HttpServletRequest request) {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        String resToDelete = request.getParameter("reservationId");

        restaurant.deleteReservation(resToDelete);
    }
}
