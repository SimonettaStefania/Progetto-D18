package tester;

import restaurant.Reservation;
import services.DbReader;
import services.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestReservationDB {

    public static void main(String[] args) throws InterruptedException, ParseException {
        int reservationCounter=0; //GENERATES THE RESERVATION CODE
        ArrayList<Reservation> resToDeleteArray=new ArrayList<>();
        String addToQuery, resToDelete="R1";

        String stringDate="12/06/2016";
        Date eventDate= new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);

        DbReader dbr = new DbReader("root","root");

        Thread insertThread=new Thread(dbr);
        Thread reader1Thread=new Thread(dbr);
        Thread reader2Thread=new Thread(dbr);
        Thread deleteThread=new Thread(dbr);

        reservationCounter++;
        Reservation res1=new Reservation("R"+reservationCounter,50,eventDate,"Piero Angela","ciao@ciao.com");
        addToQuery="('"+res1.getReservationCode()+"',"+res1.getnGuests()+",500,'"+new java.sql.Date(res1.getEventDate().getTime())+"','"+res1.getCustomerNameSurname()+"','"+res1.getCustomerMail()+"',NULL)";

        dbr.setQuery(Query.editQuery(Query.INSERT_RESERVATION,addToQuery));
        insertThread.start();
        insertThread.join();

        dbr.setQuery((Query.SELECT_RESERVATION));
        reader1Thread.start();
        reader1Thread.join();

        System.out.println("RESERVATIONS LIST: ");
        if(dbr.getReservationsList().isEmpty()){
            System.out.println("No reservations!");
        }else{
            for(Reservation elem: dbr.getReservationsList()){
                System.out.println(elem.toString());
            }
        }


        addToQuery="WHERE RES_CODE='"+resToDelete+"'";
        dbr.setQuery(Query.editQuery(Query.DELETE_RESERVATION,addToQuery));
        deleteThread.start();
        deleteThread.join();

        for(Reservation elem: dbr.getReservationsList()){
            if (elem.getReservationCode().equals(resToDelete)){
                resToDeleteArray.add(elem);
            }
        }
        dbr.getReservationsList().removeAll(resToDeleteArray);

        dbr.setQuery((Query.SELECT_RESERVATION));
        reader2Thread.start();
        reader2Thread.join();

        System.out.println("RESERVATIONS LIST: ");
        if(dbr.getReservationsList().isEmpty()){
            System.out.println("No reservations!");
        }else{
            for(Reservation elem: dbr.getReservationsList()){
                System.out.println(elem.toString());
            }
        }
    }
}