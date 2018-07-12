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
public class ReservationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkStatus(request);
        forwardTo(request, response, "/views/reservationState.jsp");
    }

    /**
     *  Redirects to homepage if trying to access the page from its address.
     *  Only access through post method is allowed.
     * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/index.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }

    private void checkStatus(HttpServletRequest request) {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        String backToStatus = request.getParameter("backToStatus");

        if (backToStatus == null) {
            reservation = makeReservation(request);
            request.getSession().setAttribute("reservation", reservation);
        } else if (backToStatus.equalsIgnoreCase("new-menu")) {
            String selected[] = request.getParameterValues("selected-id");
            String menuName = request.getParameter("menuName");
            int nGuests = Integer.parseInt(request.getParameter("people"));

            reservation.createMenu(menuName, nGuests, selected);
        } else if (backToStatus.equalsIgnoreCase("sel-opt-menu")) {
            int optimizedMenuCode = Integer.parseInt(request.getParameter("code"));
            reservation.addOptimizedMenu(optimizedMenuCode);
        } else if (backToStatus.equalsIgnoreCase("rem-menu")) {
            int removedMenu = Integer.parseInt(request.getParameter("removedMenu"));
            reservation.removeMenu(removedMenu);
        }
    }

    private Reservation makeReservation(HttpServletRequest request) {
        String formName = request.getParameter("name");
        String formSurname = request.getParameter("surname");
        String formEmail = request.getParameter("email");
        String formStringDate = request.getParameter("date");
        int formGuests = Integer.parseInt(request.getParameter("guestsNumber"));

        Date eventDate = null;
        try {
            eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(formStringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Reservation(null, formGuests,
                eventDate,formName+ " " + formSurname, formEmail);
    }
}


