package servlets;

import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/home")
public class WelcomeServlet extends HttpServlet {
    private Restaurant restaurant = Restaurant.getRestaurantInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String backToHome=request.getParameter("backToHome");

        if (backToHome == null){
            forwardTo(request, response, "/views/welcome.jsp");
        } else {
            backToHome = null;
            restaurant.removeLastReservation();
            forwardTo(request, response, "/views/welcome.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/views/welcome.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }
}
