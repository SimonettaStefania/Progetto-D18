package servlets;

import menu.DishType;
import menu.MenuElement;
import restaurant.Catalogue;
import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "SelectionServlet", urlPatterns = "/selection")
public class SelectionServlet extends HttpServlet {

    Restaurant restaurant;

    {
        try {
            restaurant = Restaurant.getRestaurantInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Catalogue catalogue = restaurant.getDishesCatalogue();

        request.setAttribute("catalogue", catalogue);
        forwardTo(request, response, "/views/dishesSelection.jsp");
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

}
