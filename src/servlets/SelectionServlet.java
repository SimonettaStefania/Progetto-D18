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

    /**
     *  Gli si passa l'istanza della reservation (o la si rende un singleton?) e si aggiunge il menu alla fine
     * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reservation r = (Reservation) request.getAttribute("reservation");

        request.setAttribute("catalogue", getCatalogue());
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


    // Solo un test
    private Catalogue getCatalogue() {
        Restaurant r = new Restaurant("Ristorante Italiano", 100);

        MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER,
                4.00, false, false, false);
        MenuElement starter_2 = new MenuElement("Aragosta","S002", DishType.STARTER,
                6.00, false, false, false);
        MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001",
                DishType.FIRST_COURSE,10.0, false, false, false);
        MenuElement first_2 = new MenuElement("Spaghetti allo scoglio", "P002",
                DishType.FIRST_COURSE,11.0, false, false, false);
        MenuElement main_1 = new MenuElement("Pepata di cozze", "M001",
                DishType.MAIN_COURSE,10.00, false, false, false);
        MenuElement main_2 = new MenuElement("Cozze", "M002",
                DishType.MAIN_COURSE,8.00, false, false, false);
        MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001",
                DishType.DESSERT, 4.00, false, false, false);
        MenuElement dessert_2 = new MenuElement("Tiramisù", "D002",
                DishType.DESSERT, 2.00, false, false, false);
        MenuElement drink_1 = new MenuElement("Acqua", "DR001",
                DishType.DRINK, 1.0, false, false, false);
        MenuElement drink_2 = new MenuElement("Kaffèèèèèè", "DR002",
                DishType.DRINK, 1.0, false, false, false);

        r.addToCatalogue(dessert_1);
        r.addToCatalogue(dessert_2);
        r.addToCatalogue(drink_1);
        r.addToCatalogue(drink_2);
        r.addToCatalogue(starter_1);
        r.addToCatalogue(starter_2);
        r.addToCatalogue(first_1);
        r.addToCatalogue(first_2);
        r.addToCatalogue(main_1);
        r.addToCatalogue(main_2);

        return r.getDishesCatalogue();
    }
}
