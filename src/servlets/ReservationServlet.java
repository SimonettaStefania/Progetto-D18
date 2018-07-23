package servlets;

import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that handles requests between web pages(welcome.jsp, dishesSelection.jsp,
 * optimizedMenus.jsp, recap.jsp, reservationState.jsp).
 * This Servlet:
 * - calls a Restaurant method to create a reservation
 * - calls Reservation methods to create a new menu, select an optimized one, delete a menu
 * - check the date inserted in Welcome Page
 */

@WebServlet(name = "ReservationServlet", urlPatterns = "/status")
public class ReservationServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/reservationState.jsp";

    /**
     * Method to handle the HTTP post request, redirecting it to the defined route (DEFAULT_ROUTE)
     * @param request represents the HTTP request
     * @param response represents the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O problem
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String backToStatus = request.getParameter("backToStatus");

        if (backToStatus == null && !checkDate(request)) {
            forwardTo(request, response, INDEX_ROUTE);
            return;
        }

        checkStatus(request, backToStatus);
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    /**
     *  Redirects to homepage if trying to access the page from its address.
     *  Only access through post method is allowed.
     * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, INDEX_ROUTE);
    }

    /**
     * Method that selects the servlet's action checking checking a parameter
     * @param request the http servlet request
     * @param backToStatus indicates the action to do
     */
    private void checkStatus(HttpServletRequest request, String backToStatus) {
        if (backToStatus == null)
            makeReservation(request);
        else if (backToStatus.equalsIgnoreCase("new-menu"))
            submitMenu(request);
        else if (backToStatus.equalsIgnoreCase("sel-opt-menu"))
            submitOptimizedMenu(request);
        else if (backToStatus.equalsIgnoreCase("rem-menu"))
            removeMenu(request);
    }

    /**
     * Method that creates a reservation for the current session using Restaurant method
     * @param request the http servlet request
     */
    private void makeReservation(HttpServletRequest request) {
        String formName = String.format("%s %s", request.getParameter("name"), request.getParameter("surname"));
        String formEmail = request.getParameter("email");
        String formStringDate = request.getParameter("date");
        int formGuests = Integer.parseInt(request.getParameter("guestsNumber"));

        Date eventDate = null;
        try {
            eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(formStringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Reservation reservation = Restaurant.makeReservation(null, formGuests, eventDate, formName, formEmail);
        request.getSession().setAttribute("reservation", reservation);
    }

    /**
     * Method that creates a new Menu using the current session Reservation instance
     * @param request the http servlet request
     */

    private void submitMenu(HttpServletRequest request) {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        String selected[] = request.getParameterValues("selected-id");
        String menuName = request.getParameter("menuName");
        int nGuests = Integer.parseInt(request.getParameter("people"));

        reservation.createMenu(menuName, nGuests, selected);
    }

    /**
     * Method that selects an optimized menu using the current session Reservation instance
     * @param request the http servlet request
     */

    private void submitOptimizedMenu(HttpServletRequest request) {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        int optimizedMenuCode = Integer.parseInt(request.getParameter("code"));
        reservation.addOptimizedMenu(optimizedMenuCode);
    }

    /**
     * Method that removes a menu from the chosen menu list using the current session Reservation instance
     * @param request the http servlet request
     */

    private void removeMenu(HttpServletRequest request) {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        int removedMenu = Integer.parseInt(request.getParameter("removedMenu"));
        reservation.removeMenu(removedMenu);
    }

    /**
     * Method that checks the validity of the date field in the ùwelcome page
     * @param request the http servlet request
     * @return true if the date is valid, otherwise it returns false
     */

    private boolean checkDate(HttpServletRequest request){
        String validity = request.getParameter("validity");
        return validity == null || validity.equals("1");
    }
}

