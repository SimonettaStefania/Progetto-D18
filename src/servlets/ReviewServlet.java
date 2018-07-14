package servlets;

import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ReviewServlet", urlPatterns = "/reservations")
public class ReviewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String code = request.getParameter("res-id");

        ArrayList<Reservation> resList = Restaurant.getRestaurantInstance().getReservationList();
        for (Reservation r : resList)
            if (code.equalsIgnoreCase(r.getReservationCode()) && email.equalsIgnoreCase(r.getCustomerMail()))
                request.setAttribute("pickedReservation", r);

        forwardTo(request, response, "/views/reservations.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/views/reservations.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }
}
