package servlets;

import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet that handles the redirection to catalogue jsp page. In this case it is
 * only allowed a doPost method, avoiding the user to reach the page from the URL
 * (i.e. avoiding the user to reach the page when he has not create the reservation before)
 */

@WebServlet(name = "ConfirmServlet", urlPatterns = "/confirm")
public class ConfirmServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/confirmPage.jsp";


    /**
     * Method to handle the HTTP post request, redirecting it to the defined route
     * (in this case confirmPage.jsp)
     * @param request represents the HTTP request
     * @param response represents the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O problem
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        confirmReservation(request.getSession());
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    /**
     * Method to handle the HTTP get request and redirect to INDEX_ROUTE (index.jsp)
     * in case of doGet. This behavior allows to prevent customer to reach the
     * confirmPage.jsp without making a reservation
     * @param request represents the HTTP request
     * @param response represents the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O problem
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, INDEX_ROUTE);
    }


    /**
     * Method to handle the reservation confirmation and add it to the restaurant.
     * Through the session.getAttribute it gets the reservation and with the
     * restaurant.insertReservation insert it into the restaurantInstance
     * @param session is the HTTP session that is running the page when the method
     *                is called
     */
    private void confirmReservation(HttpSession session) {
        Reservation reservation = (Reservation) session.getAttribute("reservation");

        Restaurant restaurant = Restaurant.getRestaurantInstance();
        restaurant.insertReservation(reservation);
    }
}
