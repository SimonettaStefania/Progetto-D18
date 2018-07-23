package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Class that handles requests between web pages(catalogue.jsp, confirmPage.jsp, navbar.jsp, reservationState.jsp).
 */

@WebServlet(name = "WelcomeServlet", urlPatterns = "/home")
public class WelcomeServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/welcome.jsp";

    /**
     * Method to handle the HTTP post request, redirecting it to the defined route (DEFAULT_ROUTE)
     * @param request represents the HTTP request
     * @param response represents the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O problem
     */

    // TODO: improve
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String backToHome = request.getParameter("backToHome");

        if (backToHome != null)
            request.getSession().removeAttribute("reservation");

        forwardTo(request, response, DEFAULT_ROUTE);
    }

    /**
     * Method to handle the HTTP get request; in this case is possible to reach welcome.jsp either from doGet or doPost
     * method
     * @param request is the HTTP request
     * @param response is the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O error
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }
}
