package services;

import menu.*;
import restaurant.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class which stands between Restaurant and QueryHandler.
 * It is described by the QueryHandler object used to interact with the database.
 */
public class DatabaseManager {
    private QueryHandler queryHandler;

    /**
     * Constructor initializing the QueryHandler
     */
    public DatabaseManager () {
        this.queryHandler = new QueryHandler();
    }

    /**
     * Method that populates catalogue and reservations list, reading from database by using QueryHandler instance
     */
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

    /**
     * Method that inserts a reservation into database by using a QueryHandler instance
     * @param reservation the reservation to insert
     */
    public synchronized void insertReservation (Reservation reservation) {
        queryHandler.setupConnection();
        queryHandler.insertReservationInDB(reservation);
        queryHandler.closeConnection();
    }

    /**
     *  Method that deletes a reservation from database by using QueryHandler instance.
     * @param toDelete the reservation to delete
     */
    public synchronized void deleteReservation (Reservation toDelete) {
        queryHandler.setupConnection();
        queryHandler.deleteReservation(toDelete.getReservationCode());
        queryHandler.closeConnection();
    }

    /**
     * Method that sets the id of the current session reservation
     * @param reservation the reservation that will have its id set
     */

    public void setReservationId(Reservation reservation) {
        String id = generateReservationId();
        reservation.setReservationCode(id);
    }

    /**
     * Method that returns a unique random ID not used by any other reservation
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
