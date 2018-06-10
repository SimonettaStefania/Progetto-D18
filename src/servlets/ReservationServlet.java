package servlets;

import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ReservationServlet", urlPatterns = "/status")
public class ReservationServlet extends HttpServlet {
    Restaurant restaurant;
    {
        try {
            restaurant = Restaurant.getRestaurantInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Non chiedete perchè questo try/catch faccia così schifo pls

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formName = request.getParameter("name");
        String formSurname = request.getParameter("surname");
        String formEmail = request.getParameter("email");
        String formStringDate = request.getParameter("date");
        Date eventDate=null;
        int reservationListSize = restaurant.getReservationList().size(),reservationsCount;
        String lastReservationCode="R000",newReservationCode;

        // GENERATING THE CODE FOR THE NEW RESERVATION
        if(!(restaurant.getReservationList().isEmpty())){
            lastReservationCode=restaurant.getReservationList().get(reservationListSize-1).getReservationCode();
        }
        reservationsCount= Integer.parseInt(lastReservationCode.substring(1));

        if(reservationsCount<10)
            newReservationCode="R00"+(reservationsCount+1);
        else
        {
            if(reservationsCount<100){
                newReservationCode="R0"+(lastReservationCode+1);
            }
            else
                newReservationCode="R"+(lastReservationCode+1);
        }

        // MANAGING EVENT DATE
        try {
            eventDate=new SimpleDateFormat("yyyy-MM-dd").parse(formStringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        restaurant.getReservationList().add(new Reservation(newReservationCode,eventDate,formName+" "+formSurname,formEmail));

        forwardTo(request, response, "/views/reservationState.jsp");
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
}


