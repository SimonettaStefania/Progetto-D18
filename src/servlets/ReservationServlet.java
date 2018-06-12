package servlets;

import menu.Menu;
import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ReservationServlet", urlPatterns = "/status")
public class ReservationServlet extends HttpServlet {
    private Restaurant restaurant;
    {
        try {
            restaurant = Restaurant.getRestaurantInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String backToStatus=request.getParameter("backToStatus");
        if(backToStatus==null){

            String formName = request.getParameter("name");
            String formSurname = request.getParameter("surname");
            String formEmail = request.getParameter("email");
            String formStringDate = request.getParameter("date");

            Date eventDate=null;
            try {
                eventDate=new SimpleDateFormat("yyyy-MM-dd").parse(formStringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            restaurant.getReservationList().add(new Reservation(generateReservationCode(),eventDate,formName+" "+formSurname,formEmail));
            forwardTo(request, response, "/views/reservationState.jsp");


        }else{
            backToStatus=null;
            clearOptimizedMenus();
            forwardTo(request, response, "/views/reservationState.jsp");
        }


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


    private String generateReservationCode(){
        int reservationsCount=0;
        String lastReservationCode="R000",newReservationCode;
        Reservation lastReservation = restaurant.getLastReservation();

        if(lastReservation!=null){
            lastReservationCode=lastReservation.getReservationCode();
            reservationsCount= Integer.parseInt(lastReservationCode.substring(1));
        }


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
        return newReservationCode;
    }

    private void clearOptimizedMenus(){
        Reservation lastReservation=restaurant.getLastReservation();
        lastReservation.getOptimizedMenu().clear();

    }

}


