package restaurant;

import services.DatabaseManager;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class containing all the methods to handle the restaurant, which is initialized on the welcome page
 * It is described by:
 * - Name
 * - N_Covers
 * - An instance of the restaurant itself
 */
public class Restaurant {
    private static final String NAME = "Progetto D-18";
    private static final int N_COVERS =  150;
    private static Restaurant restaurantInstance;
    private static boolean database = true;

    private String name;
    private int nCover;
    private Catalogue dishesCatalogue;
    private ArrayList<Reservation> reservationList;
    private DatabaseManager databaseManager;

    /**
     * Class constructor, creates the Restaurant
     * @param name name of the restaurant
     * @param nCover number of restaurant covers
     */
    private Restaurant(String name, int nCover) {
        this.name = name;
        this.nCover = nCover;
        this.dishesCatalogue = new Catalogue();
        this.reservationList = new ArrayList<>();
        this.databaseManager = new DatabaseManager();
    }

    public static synchronized Restaurant getRestaurantInstance() {
        if (restaurantInstance == null)
            initRestaurant();

        return restaurantInstance;
    }

    /**
     * Method that read the database and uses the "readDatabase" method to
     * initialize the catalogue
     */
    private static synchronized void initRestaurant() {
        restaurantInstance = new Restaurant(NAME, N_COVERS);

        if (database)
            restaurantInstance.databaseManager.readDatabase();
    }

    public static void setDatabase(boolean value) {
        if (restaurantInstance == null)
            database = value;
    }

    public static Reservation makeReservation(String id, int guests, Date date, String name, String mail) {
        return new Reservation(id, guests, date, name, mail);
    }

    /**
     * Getter that returns restaurant name
     * @return name of the restaurant
     */
    public String getName() {
        return name;
    }

    /**
     * Getter that returns number of covers of the instanced restaurant
     * @return number of covers
     */
    public int getnCover() {
        return nCover;
    }

    /**
     * Getter that returns the arraylist containing all the already created reservations
     * for the instanced restaurant. It is in the reservations page to find the
     * reservations that corresponds to email-id set
     * @return list of existing reservations for the restaurant
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     * Getter that returns the dishes catalogue
     * @return the catalogue of dishes
     */
    public Catalogue getDishesCatalogue() {
        return dishesCatalogue;
    }

    /**
     * Setter that get a catalogue as a paramether and set that as dishes catalogue
     * in the initialized restaurant
     * @param cat catalogue to be passed
     */
    public void setCatalogue(Catalogue cat) {
        dishesCatalogue = cat;
    }

    /**
     * Method that get a reservation as a parameter and insert it into the database
     * thought the databaseManager. Must be synchronized to avoid concurrency problems that may
     * occur due to contemporary access to the method
     * @param reservation reservation to be inserted
     */
    public synchronized void insertReservation(Reservation reservation) {
        databaseManager.setReservationId(reservation);

        if (database)  databaseManager.insertReservation(reservation);
        reservationList.add(reservation);
    }


    /**
     * Method that uses the databaseManger to delete a reservation using the unique generated
     * code as a parameter to identify the selected reservation.
     * Must be synchronized to avoid concurrency problems that may
     * occur due to contemporary access to the method
     * @param code code of reservation to be deleted
     */
    public synchronized void deleteReservation(String code) {
        Reservation toDelete = null;

        for(Reservation r : reservationList)
            if (code.equals(r.getReservationCode()))
                toDelete = r;

        if (toDelete != null) {
            if (database)  databaseManager.deleteReservation(toDelete);
            reservationList.remove(toDelete);
        }
    }
}