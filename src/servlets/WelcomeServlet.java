package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/home")
public class WelcomeServlet extends AbstractServlet {
    private String DEFAULT_ROUTE = "/views/welcome.jsp";

    // TODO: improve
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String backToHome = request.getParameter("backToHome");

        if (backToHome != null)
            request.getSession().removeAttribute("reservation");

        forwardTo(request, response, DEFAULT_ROUTE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, DEFAULT_ROUTE);
    }
}
