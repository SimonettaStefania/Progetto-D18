package tester;

import menu.MenuElement;
import restaurant.Catalogue;
import restaurant.Reservation;
import restaurant.Restaurant;
import services.DbReader;
import services.Query;

import java.util.Date;

public class TestReservationDB {

    public static void main(String[] args) throws InterruptedException {
        int reservationCounter=0; //
        String addToQuery;


        DbReader dbr = new DbReader("root","root");

        Thread insertThread=new Thread(dbr);
        Thread readerThread=new Thread(dbr);

        reservationCounter++;
        Reservation res1=new Reservation("R"+reservationCounter,50,50,new Date(2018- 5 -13),"C01","Piero Angela","3334567654");
        addToQuery="('"+res1.getReservationCode()+"',"+res1.getnGuests()+",500,'"+res1.getEventDate()+"','"+res1.getCustomerId()+"','"+res1.getCustomerNameSurname()+"','"+res1.getCustomerPhone()+"')";

        dbr.setQuery(Query.editQuery(Query.INSERT_RESERVATION,addToQuery));
        insertThread.start();
        insertThread.join();

        dbr.setQuery((Query.SELECT_RESERVATION));
        readerThread.start();
        readerThread.join();





    }
}