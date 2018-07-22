package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AbstractServlet extends HttpServlet {
    protected String INDEX_ROUTE = "/index.jsp";

    protected void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }
}
