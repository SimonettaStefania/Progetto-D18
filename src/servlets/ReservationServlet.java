package servlets;

import menu.Menu;
import menu.MenuElement;
import restaurant.Catalogue;
import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ReservationServlet", urlPatterns = "/status")
public class ReservationServlet extends HttpServlet {
    private Restaurant restaurant = Restaurant.getRestaurantInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        String backToStatus = request.getParameter("backToStatus");

        if (backToStatus == null) {
            // TODO: salvarsi questa reservation invece di prendere l'ultima
            reservation = makeReservation(request);
            request.getSession().setAttribute("reservation", reservation);
        } else if (backToStatus.equalsIgnoreCase("new-menu")) {
            Catalogue catalogue = restaurant.getDishesCatalogue();
            String selected[] = request.getParameterValues("selected-id");

            String menuName = request.getParameter("menuName");
            int nGuests = Integer.parseInt(request.getParameter("people"));
            if (menuName.isEmpty())
                menuName = "Menu personalizzato";

            if (selected != null) {
                Menu menu = new Menu(menuName, nGuests);
                for (String id : selected) {
                    MenuElement item = catalogue.getElementByCode(id);
                    menu.addElement(item);
                }

                reservation.addMenu(menu);
            }
        } else if (backToStatus.equalsIgnoreCase("sel-opt-menu")) {
            int optimizedMenuCode = Integer.parseInt(request.getParameter("code"));
            selectOptimizedMenu(reservation, optimizedMenuCode);
        } else if (backToStatus.equalsIgnoreCase("rem-menu")) {
            int removedMenu = Integer.parseInt(request.getParameter("removedMenu"));
            ArrayList<Menu> menu = reservation.getCreatedMenu();

            menu.remove(removedMenu);
        } else if (backToStatus.equalsIgnoreCase("back")){
            clearOptimizedMenus(reservation);
        }

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

    private Reservation makeReservation(HttpServletRequest request) {
        String formName = request.getParameter("name");
        String formSurname = request.getParameter("surname");
        String formEmail = request.getParameter("email");
        String formStringDate = request.getParameter("date");
        String formGuests = request.getParameter("guestsNumber");

        Date eventDate = null;
        try {
            eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(formStringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Reservation reservation = new Reservation(generateReservationCode(),eventDate,
                formName+ " " + formSurname, formEmail);
        reservation.setnGuests(Integer.parseInt(formGuests));
        restaurant.getReservationList().add(reservation);
        return reservation;
    }

    // TODO: genererarlo solo alla fine, una volta confermata la reservation?
    private String generateReservationCode(){
        int reservationsCount = 0;
        String lastReservationCode = "R000", newReservationCode;
        Reservation lastReservation = restaurant.getLastReservation();

        if (lastReservation != null) {
            lastReservationCode = lastReservation.getReservationCode();
            reservationsCount = Integer.parseInt(lastReservationCode.substring(1));
        }

        if (reservationsCount<10)
            newReservationCode = "R00" + (reservationsCount+1);
        else {
            if(reservationsCount < 100) {
                newReservationCode = "R0" + (lastReservationCode+1);
            }
            else
                newReservationCode ="R" + (lastReservationCode+1);
        }
        return newReservationCode;
    }

    private void clearOptimizedMenus(Reservation reservation){
        reservation.getOptimizedMenu().clear();
    }

    private void selectOptimizedMenu(Reservation reservation, int optimizedMenuCode){
        String menuName;

        switch (optimizedMenuCode){
            case 1:
                menuName="OPTIMIZED BUDGET ON STARTERS";
                break;
            case 2:
                menuName="OPTIMIZED BUDGET ON FIRST COURSES";
                break;
            case 3:
                menuName="OPTIMIZED BUDGET ON MAIN COURSES";
                break;
            case 4:
                menuName="JUST OPTIMIZED BUDGET";
                break;
            default:
                menuName="NOT OPTIMIZED";
                break;
        }

        for (Menu elem : reservation.getOptimizedMenu())
            if (elem.getName().equals(menuName))
                reservation.addMenu(elem);
    }
}


