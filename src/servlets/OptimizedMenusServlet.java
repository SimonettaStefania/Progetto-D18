package servlets;

import restaurant.Reservation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Servlet that handles the redirection to optimized menu jsp page. In this case it is
 * only allowed a doPost method, avoiding the user to reach the page from the URL
 * (i.e. avoiding the user to reach the page when he has not create the reservation before)
 */
@WebServlet(name = "OptimizedMenusServlet", urlPatterns = "/optimize")
public class OptimizedMenusServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/optimizedMenus.jsp";


    /**
     * /**
     *  Method to handle the HTTP post request, redirecting it to the defined route
     * (in this case confirmPage.jsp). With the post method the servlet handles the creation of the budget optimized menus
     * getting the attributes inserted by the customer and passing them to the reservation.generateOptimizedMenu method
     * @param request represents the HTTP request
     * @param response represents the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O problem
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        double budget = Double.parseDouble(request.getParameter("budget"));
        int people = Integer.parseInt(request.getParameter("people"));

        reservation.generateOptimizedMenus(budget, people);
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    /**
     * Method to handle the HTTP get request and redirect to INDEX_ROUTE (home.jsp)
     * in case of doGet. This behavior allows to prevent customer to reach the
     * confirmPage.jsp without making a reservation
     * @param request represents the HTTP request
     * @param response represents the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O problem
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, INDEX_ROUTE);
    }
}
