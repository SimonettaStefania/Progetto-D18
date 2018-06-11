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

@WebServlet(name = "OptimizedMenusServlet", urlPatterns = "/optimize")
public class OptimizedMenusServlet extends HttpServlet {
    Restaurant restaurant;
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
        int reservationListSize = restaurant.getReservationList().size();
        MenuGenerator menuGenerator;
        Reservation lastReservation=null;

        if(!(restaurant.getReservationList().isEmpty())){
            lastReservation=restaurant.getReservationList().get(reservationListSize-1);
        }

        menuGenerator=new MenuGenerator(budget,restaurant.getDishesCatalogue());
        menuGenerator.generate();

        lastReservation.getOptimizedMenu().addAll(menuGenerator.getGeneratedMenu());

        forwardTo(request, response, "/views/optimizedMenus.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/views/index.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }
}
