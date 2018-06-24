package servlets;

import restaurant.Reservation;
import services.DbReader;
import services.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "ConfirmServlet", urlPatterns = "/confirm")
public class ConfirmServlet extends HttpServlet {
    private DbReader dbr = DbReader.getDbReaderInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        insertReservationInDB(reservation);
        forwardTo(request, response, "/views/confirmPage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, "/index.jsp");
    }

    private void forwardTo(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(route);
        rd.forward(request, response);
    }

    private void insertReservationInDB(Reservation reservation){
        Thread insertThread = new Thread(dbr);

        String addToQuery = String.format("('%s',%d,%s,'%s','%s','%s',NULL)", reservation.getReservationCode(),
                reservation.getnGuests(), reservation.getReservationCost(), new Date(reservation.getEventDate().getTime()),
                reservation.getCustomerNameSurname(), reservation.getCustomerMail());

        dbr.setQuery(Query.editQuery(Query.INSERT_RESERVATION,addToQuery));
        insertThread.start();
        try {
            insertThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
