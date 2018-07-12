package restaurant;

import menu.Menu;
import menu.MenuElement;
import services.DbReader;
import services.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
    private static Restaurant restaurantInstance;
    private static int N_COVERS =  150 ;
    private String name;
    private int nCover;
    private Catalogue dishesCatalogue;
    private ArrayList<Reservation> reservationList;


    public Restaurant(String name, int nCover, boolean database) {
        this.name = name;
        this.nCover = nCover;
        this.dishesCatalogue = new Catalogue();
        this.reservationList = new ArrayList<>();

        if (database)
            readDatabase();
        // else readLocal();    TODO: creare una copia locale in caso di errori?
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

    public void setCatalogue(Catalogue cat) {
        dishesCatalogue = cat;
    }

    public void addToCatalogue (MenuElement element){
        dishesCatalogue.addElement(element);
    }

    public void removeFromCatalogue (MenuElement elem){
        dishesCatalogue.removeElement(elem);
    }

    // TODO: ha poco senso passare da qui per aggiungere/togliere elementi al menu, da rivedere i diagrammi di sequenza?
    public void addDish(Reservation res, Menu menu, MenuElement menuElement){
        res.addDish(menu, menuElement);
    }

    public void removeDish(Reservation res, Menu menu, MenuElement menuElement){
        res.removeDish(menu, menuElement);
    }

    public String showCatalogue (){
        String tmp = "Catalogo del ristorante \" " + name + " \" :\n" ;
            tmp += dishesCatalogue.toString();
        return tmp ;
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public static synchronized  Restaurant getRestaurantInstance() {
        if (restaurantInstance == null)
            restaurantInstance =  new Restaurant("Da Ciccio", 150,true);
        return restaurantInstance;
    }

    public Reservation getLastReservation(){
        int reservationListSize;

        if(!(reservationList.isEmpty())){
            reservationListSize = reservationList.size();
            return reservationList.get(reservationListSize-1);
        }
        return null;
    }

    public void removeLastReservation(){
        reservationList.remove(getLastReservation());
    }

    private void readDatabase() {
        DbReader dbr = DbReader.getDbReaderInstance();

        executeQuery(dbr, Query.SELECT_RESERVATION);
        reservationList.addAll(dbr.getReservationsList());

        executeQuery(dbr, Query.SELECT_ALL_DISHES);
        for (MenuElement elem : dbr.getDishesList())
            this.addToCatalogue(elem);

        dishesCatalogue.getDishes().sort(MenuElement.priceComparator);
        dishesCatalogue.getDishes().sort(MenuElement.typeComparator);
    }

    public synchronized void insertReservation(Reservation reservation) {
        DbReader dbr = DbReader.getDbReaderInstance();
        String id = generateReservationId();
        reservation.setReservationCode(id);

        reservationList.add(reservation);
        String addToQuery = String.format("('%s',%d,%s,'%s','%s','%s',NULL)", reservation.getReservationCode(),
                reservation.getnGuests(), reservation.getReservationCost(), new Date(reservation.getEventDate().getTime()),
                reservation.getCustomerNameSurname(), reservation.getCustomerMail());
        executeQuery(dbr,Query.editQuery(Query.INSERT_RESERVATION,addToQuery));

    }

    private void executeQuery(DbReader dbr, String query) {
        Thread dbThread = new Thread(dbr);
        dbr.setQuery(query);
        dbThread.start();
        try {
            dbThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private String generateReservationId() {
        String tmpId = null;
        boolean unique = false;

        while (!unique) {
            tmpId = generateCode();

            unique = true;
            for (Reservation res : reservationList)
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


    public static int getStaticCovers(){
        return N_COVERS;
    }
}