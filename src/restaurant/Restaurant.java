package restaurant;

import services.DatabaseManager;
import java.util.ArrayList;

public class Restaurant {
    private static final String NAME = "Progetto D-18";
    private static final int N_COVERS =  150;
    private static Restaurant restaurantInstance;

    private String name;
    private int nCover;
    private Catalogue dishesCatalogue;
    private ArrayList<Reservation> reservationList;
    private DatabaseManager databaseManager;


    public Restaurant(String name, int nCover) {
        this.name = name;
        this.nCover = nCover;
        this.dishesCatalogue = new Catalogue();
        this.reservationList = new ArrayList<>();
        this.databaseManager = new DatabaseManager(dishesCatalogue, reservationList);
    }

    public String getName() {
        return name;
    }

    public int getnCover() {
        return nCover;
    }

    public Catalogue getDishesCatalogue() {
        return dishesCatalogue;
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public void setCatalogue(Catalogue cat) {
        dishesCatalogue = cat;
    }

    public static synchronized Restaurant getRestaurantInstance() {
        if (restaurantInstance == null) {
            restaurantInstance = new Restaurant(NAME, N_COVERS);
            restaurantInstance.readDatabase();
        }
        return restaurantInstance;
    }

    private synchronized void readDatabase() {
        databaseManager.readDatabase();
    }

    public synchronized void insertReservation(Reservation reservation) {
        databaseManager.insertReservation(reservation);
    }

    public synchronized void deleteReservation(String code) {
        databaseManager.deleteReservation(code);
    }

    // TODO: remove!!
    public static int getStaticCovers(){
        Restaurant r = Restaurant.getRestaurantInstance();
        return r.getnCover();
    }
}