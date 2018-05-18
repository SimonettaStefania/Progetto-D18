package tester;

import restaurant.Reservation;
import services.DbReader;
import services.Query;

import java.util.Date;

public class TestReservationDB {

    public static void main(String[] args) throws InterruptedException {
        int reservationCounter=0; //GENERATES THE RESERVATION CODE
        String addToQuery, resToDelete="R1";


        DbReader dbr = new DbReader("esame","123456");

        Thread insertThread=new Thread(dbr);
        Thread reader1Thread=new Thread(dbr);
        Thread reader2Thread=new Thread(dbr);
        Thread deleteThread=new Thread(dbr);

        reservationCounter++;
        Reservation res1=new Reservation("R"+reservationCounter,50,50,new Date(2018- 5 -13),"Piero Angela","ciao@ciao.com");
        addToQuery="('"+res1.getReservationCode()+"',"+res1.getnGuests()+",500,'"+res1.getEventDate()+"','"+res1.getCustomerNameSurname()+"','"+res1.getCustomerMail()+"')";

        dbr.setQuery(Query.editQuery(Query.INSERT_RESERVATION,addToQuery));
        insertThread.start();
        insertThread.join();

        dbr.setQuery((Query.SELECT_RESERVATION));
        reader1Thread.start();
        reader1Thread.join();

        addToQuery="WHERE RES_CODE='"+resToDelete+"'";
        dbr.setQuery(Query.editQuery(Query.DELETE_RESERVATION,addToQuery));
        deleteThread.start();
        deleteThread.join();

        dbr.setQuery((Query.SELECT_RESERVATION));
        reader2Thread.start();
        reader2Thread.join();
    }
}