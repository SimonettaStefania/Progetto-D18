package services;

import menu.*;
import restaurant.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class which stands between Restaurant and QueryHandler
 */
public class DatabaseManager {
    private QueryHandler queryHandler;

    public DatabaseManager () {
        this.queryHandler = new QueryHandler();
    }


    public synchronized void readDatabase() {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        Catalogue catalogue = restaurant.getDishesCatalogue();
        ArrayList<Reservation> reservations = restaurant.getReservationList();

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
        queryHandler.setupConnection();
        queryHandler.insertReservationInDB(reservation);
        queryHandler.closeConnection();
    }

    public synchronized void deleteReservation (Reservation toDelete) {
        queryHandler.setupConnection();
        queryHandler.deleteReservation(toDelete.getReservationCode());
        queryHandler.closeConnection();
    }

    public void setReservationId(Reservation reservation) {
        String id = generateReservationId();
        reservation.setReservationCode(id);
    }

    /**
     * Method that returns a generatd ID if it is not used by any reservation
     * @return tmpId is the generated ID
     */
    private String generateReservationId() {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        ArrayList<Reservation> reservations = restaurant.getReservationList();

        boolean unique = false;
        String tmpId = null;

        while (!unique) {
            tmpId = generateCode();

            unique = true;
            for (Reservation res : reservations)
                if (tmpId.equalsIgnoreCase(res.getReservationCode()))
                    unique = false;
        }

        return tmpId;
    }

    /**
     * Method that generates a 6 character random code
     * @return the random code
     */
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
