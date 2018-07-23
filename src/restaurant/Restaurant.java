package restaurant;

import services.DatabaseManager;
import java.util.ArrayList;

/**
 * Class that represents the restaurant.
 * It is a Singleton and it is described by:
 * - a name
 * - the number of the covers
 * - a catalogue
 * - a list of reservations
 * - an object (databaseManager) which interfaces the restaurant with the database
 */

public class Restaurant {
    private static final String NAME = "Progetto D-18";
    private static final int N_COVERS =  150;
    private static Restaurant restaurantInstance;

    private String name;
    private int nCover;
    private Catalogue dishesCatalogue;
    private ArrayList<Reservation> reservationList;
    private DatabaseManager databaseManager;

    /**
     * Constructor with parameters:
     * @param name is the name of the restaurant
     * @param nCover is the number of the covers
     */

    public Restaurant(String name, int nCover) {
        this.name = name;
        this.nCover = nCover;
        this.dishesCatalogue = new Catalogue();
        this.reservationList = new ArrayList<>();
        this.databaseManager = new DatabaseManager(dishesCatalogue, reservationList);
    }

    /**
     * Method which returns the current Restaurant's instance if present, otherwise
     * it returns a new Restaurant's instance
     * @return restaurantInstance is the current or the new instance
     */

    public static synchronized Restaurant getRestaurantInstance() {
        if (restaurantInstance == null) {
            restaurantInstance = new Restaurant(NAME, N_COVERS);
            restaurantInstance.initRestaurant();
        }
        return restaurantInstance;
    }

    /**
     *@return restaurant's name
     */

    public String getName() {
        return name;
    }

    /**
     *@return restaurants's covers
     */
    public int getnCover() {
        return nCover;
    }

    /**
     *@return the list of the reservations
     */

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     *@return restaurant's catalogue
     */

    public Catalogue getDishesCatalogue() {
        return dishesCatalogue;
    }

    /**
     * Method that sets restaurant's catalogue from a given one
     * @param cat given Catalogue object
     */

    public void setCatalogue(Catalogue cat) {
        dishesCatalogue = cat;
    }

    /**
     * Method that populates the catalogue and the reservations list from database by using DatabaseManager instance.
     */

    private synchronized void initRestaurant() {
        databaseManager.readDatabase();
    }

    /**
     * Method which inserts a reservation into database by using a DatabaseManager instance
     * @param reservation the reservation to insert
     */

    public synchronized void insertReservation(Reservation reservation) {
        databaseManager.insertReservation(reservation);
    }

    /**
     * Method that deletes a reservation from database by using DatabaseManager instance.
     * @param code the code of the reservation to delete
     */
    public synchronized void deleteReservation(String code) {
        databaseManager.deleteReservation(code);
    }
}