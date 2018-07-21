package servlets;

import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ReviewServlet", urlPatterns = "/reservations")
public class ReviewServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/reservations.jsp";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String code = request.getParameter("res-id");

        ArrayList<Reservation> resList = Restaurant.getRestaurantInstance().getReservationList();
        for (Reservation r : resList)
            if (code.equalsIgnoreCase(r.getReservationCode()) && email.equalsIgnoreCase(r.getCustomerMail()))
                request.setAttribute("pickedReservation", r);

        forwardTo(request, response, DEFAULT_ROUTE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }
}
