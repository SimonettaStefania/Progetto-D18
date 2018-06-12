package servlets;

import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/home")
public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restaurant restaurant=null;
        {
            try {
                restaurant = Restaurant.getRestaurantInstance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String backToHome=request.getParameter("backToHome");

        if (backToHome==null){
            forwardTo(request, response, "/views/welcome.jsp");
        }else{

            //REMOVE THE LAST RESERVATION FROM THE LIST
            backToHome=null;
            int reservationListSize = restaurant.getReservationList().size();
            Reservation lastReservation=null;

            if(!(restaurant.getReservationList().isEmpty())){
                lastReservation=restaurant.getReservationList().get(reservationListSize-1);
            }
            restaurant.getReservationList().remove(lastReservation);
            forwardTo(request, response, "/views/welcome.jsp");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/views/welcome.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }
}
