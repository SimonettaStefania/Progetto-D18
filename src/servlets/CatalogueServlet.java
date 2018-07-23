package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet that handles the redirection to the catalogue jsp page. In this case
 * it allows a get method, making the page reachable from the URL, from the navbar
 * and from the buttons.
 */


@WebServlet(name = "CatalogueServlet", urlPatterns = "/catalogue")
public class CatalogueServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/catalogue.jsp";

    /**
     * Method that handle the HTTP post request, redirecting it to the defined route
     * (in this case catalogue.jsp)
     * @param request is the HTTP request
     * @param response is the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O error
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }
}
