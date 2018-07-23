package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "RecapServlet", urlPatterns = "/recap")
public class RecapServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/recap.jsp";


    /**
     * Method to handle the HTTP post request, redirecting it to the defined route
     * (in this case recap.jsp.jsp)
     * @param request represents the HTTP request
     * @param response represents the HTTP response
     * @throws ServletException Exception coming from the servlet itself
     * @throws IOException Exception coming from an I/O problem
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
