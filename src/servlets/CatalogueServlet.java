package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "CatalogueServlet", urlPatterns = "/catalogue")
public class CatalogueServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/catalogue.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }
}
