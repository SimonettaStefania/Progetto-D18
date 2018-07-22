package servlets;

import restaurant.Reservation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ReservationServlet", urlPatterns = "/status")
public class ReservationServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/reservationState.jsp";

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

        Reservation reservation = new Reservation(null, formGuests, eventDate, formName, formEmail);
        request.getSession().setAttribute("reservation", reservation);
    }

    private void submitMenu(HttpServletRequest request) {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        String selected[] = request.getParameterValues("selected-id");
        String menuName = request.getParameter("menuName");
        int nGuests = Integer.parseInt(request.getParameter("people"));

        reservation.createMenu(menuName, nGuests, selected);
    }

    private void submitOptimizedMenu(HttpServletRequest request) {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        int optimizedMenuCode = Integer.parseInt(request.getParameter("code"));
        reservation.addOptimizedMenu(optimizedMenuCode);
    }

    private void removeMenu(HttpServletRequest request) {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        int removedMenu = Integer.parseInt(request.getParameter("removedMenu"));
        reservation.removeMenu(removedMenu);
    }

    private boolean checkDate(HttpServletRequest request){
        String validity = request.getParameter("validity");
        return validity == null || validity.equals("1");
    }
}

