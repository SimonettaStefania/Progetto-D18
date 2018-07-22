package services;

import menu.*;
import restaurant.*;

import java.util.ArrayList;
import java.util.Random;

public class DatabaseManager {
    private Catalogue catalogue;
    private ArrayList<Reservation> reservations;
    private QueryHandler queryHandler;

    public DatabaseManager (Catalogue cat, ArrayList<Reservation> res) {
        this.catalogue = cat;
        this.reservations = res;
        this.queryHandler = new QueryHandler();
    }


    public synchronized void readDatabase() {
        queryHandler.setupConnection();

        for (MenuElement elem : queryHandler.readCatalogue())
            catalogue.addElement(elem);

        for (Allergen elem : queryHandler.readAllergens())
            catalogue.addAllergen(elem);

        catalogue.getDishes().sort(MenuElement.priceComparator);
        catalogue.getDishes().sort(MenuElement.typeComparator);

        reservations.addAll(queryHandler.readReservations());

        queryHandler.closeConnection();
    }

    public synchronized void insertReservation (Reservation reservation) {
        String id = generateReservationId();
        reservation.setReservationCode(id);

        reservations.add(reservation);

        queryHandler.setupConnection();
        queryHandler.insertReservationInDB(reservation);
        queryHandler.closeConnection();
    }

    public synchronized void deleteReservation (String reservationCode) {
        Reservation toDelete = null;

        for(Reservation elem : reservations)
            if (elem.getReservationCode().equals(reservationCode))
                toDelete = elem;

        if (toDelete != null) {
            reservations.remove(toDelete);

            queryHandler.setupConnection();
            queryHandler.deleteReservation(reservationCode);
            queryHandler.closeConnection();
        }
    }

    private String generateReservationId() {
        String tmpId = null;
        boolean unique = false;

        while (!unique) {
            tmpId = generateCode();

            unique = true;
            for (Reservation res : reservations)
                if (tmpId.equalsIgnoreCase(res.getReservationCode()))
                    unique = false;
        }

        return tmpId;
    }

    private String generateCode() {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;

        for (int i = 0; i < length; i++)
            sb.append(candidateChars.charAt(random.nextInt(36)));

        return sb.toString();
    }
}
