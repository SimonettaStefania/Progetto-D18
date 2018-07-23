package restaurant;

import services.DatabaseManager;
import java.util.ArrayList;
import java.util.Date;

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

    public String getName() {
        return name;
    }

    public int getnCover() {
        return nCover;
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public Catalogue getDishesCatalogue() {
        return dishesCatalogue;
    }

    public void setCatalogue(Catalogue cat) {
        dishesCatalogue = cat;
    }

    public synchronized void insertReservation(Reservation reservation) {
        databaseManager.setReservationId(reservation);

        if (database)  databaseManager.insertReservation(reservation);
        reservationList.add(reservation);
    }

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