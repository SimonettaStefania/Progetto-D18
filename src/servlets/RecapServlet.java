package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "RecapServlet", urlPatterns = "/recap")
public class RecapServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/recap.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    /**
     *  Redirects to homepage if trying to access the page from its address.
     *  Only access through post method is allowed.
     * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, INDEX_ROUTE);
    }
}
