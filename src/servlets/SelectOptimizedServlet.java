package servlets;

import menu.Menu;
import restaurant.Reservation;
import restaurant.Restaurant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SelectOptimizedServlet", urlPatterns = "/selectOptimized")
public class SelectOptimizedServlet extends HttpServlet {
    Restaurant restaurant;
    {
        try {
            restaurant = Restaurant.getRestaurantInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int optimizedMenuCode=Integer.parseInt(request.getParameter("code"));
        String menuName;
        int reservationListSize = restaurant.getReservationList().size() ;
        Reservation lastReservation=null;

        if(!(restaurant.getReservationList().isEmpty())){
            lastReservation=restaurant.getReservationList().get(reservationListSize-1);
        }

        switch (optimizedMenuCode){
            case 1:
                menuName="OPTIMIZED BUDGET ON STARTERS";
                break;
            case 2:
                menuName="OPTIMIZED BUDGET ON FIRST COURSES";
                break;
            case 3:
                menuName="OPTIMIZED BUDGET ON MAIN COURSES";
                break;
            case 4:
                menuName="JUST OPTIMIZED BUDGET";
                break;
            default:
                menuName="NOT OPTIMIZED";
                break;
        }

        for (Menu elem: lastReservation.getOptimizedMenu()){

            if (elem.getName().equals(menuName)){
                lastReservation.getCreatedMenu().add(elem);
            }
        }

        forwardTo(request, response, "/views/optimizedMenus.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/index.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }
}
