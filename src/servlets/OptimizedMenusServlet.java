package servlets;

import restaurant.Reservation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "OptimizedMenusServlet", urlPatterns = "/optimize")
public class OptimizedMenusServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/optimizedMenus.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        double budget = Double.parseDouble(request.getParameter("budget"));
        int people = Integer.parseInt(request.getParameter("people"));

        reservation.generateOptimizedMenus(budget, people);
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, INDEX_ROUTE);
    }
}
