package servlets;

import menu.Menu;
import restaurant.Reservation;
import restaurant.Restaurant;
import services.MenuGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OptimizedMenusServlet", urlPatterns = "/optimize")
public class OptimizedMenusServlet extends HttpServlet {
    private Restaurant restaurant;
    {
        try {
            restaurant = Restaurant.getRestaurantInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formStringBudget = request.getParameter("budget");
        double budget= Double.parseDouble(formStringBudget);

        generateOptimizedMenus(budget);

        forwardTo(request, response, "/views/optimizedMenus.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/index.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }

    private void generateOptimizedMenus(double budget){
        Reservation lastReservation= restaurant.getLastReservation();
        MenuGenerator menuGenerator= new MenuGenerator(budget,restaurant.getDishesCatalogue());

        menuGenerator.generate();
        lastReservation.getOptimizedMenu().addAll(menuGenerator.getGeneratedMenu());

    }
}
