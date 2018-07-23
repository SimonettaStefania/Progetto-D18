package servlets;

import restaurant.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This Servlet:
 * - search a certain reservation using email and reservation code
 * - calls a Reservation method to delete a certain reservation
 */

@WebServlet(name = "ReviewServlet", urlPatterns = "/reservations")
public class ReviewServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/reservations.jsp";

    /**
     * Method to handle the HTTP post request, redirecting it to the defined route (DEFAULT_ROUTE)
     * @param request represents the HTTP request
     * @param response represents the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O problem
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("confirm"))
            searchReservations(request);
        else if (action.equals("delete"))
            deleteReservation(request);

        forwardTo(request, response, DEFAULT_ROUTE);
    }

    /**
     * Method to handle the HTTP get request; in this case is possible to reach reservations.jsp either from doGet or doPost
     * method
     * @param request is the HTTP request
     * @param response is the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O error
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    /**
     * Method that select a certain reservation from the reservations list contained in Restaurant
     * @param request the http servlet request
     */

    private void searchReservations (HttpServletRequest request) {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        String email = request.getParameter("email");
        String code = request.getParameter("res-id");

        ArrayList<Reservation> resList = restaurant.getReservationList();
        for (Reservation r : resList)
            if (code.equalsIgnoreCase(r.getReservationCode()) && email.equalsIgnoreCase(r.getCustomerMail()))
                request.setAttribute("pickedReservation", r);
    }

    /**
     * Method that delete a certain reservation using the Restaurant instance
     * @param request the http servlet request
     */

    private void deleteReservation (HttpServletRequest request) {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        String resToDelete = request.getParameter("reservationId");

        restaurant.deleteReservation(resToDelete);
    }
}
