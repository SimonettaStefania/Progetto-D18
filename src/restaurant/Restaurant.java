package restaurant;

import menu.Allergen;
import menu.MenuElement;
import services.DbReader;
import services.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Restaurant {
    private static final String NAME = "Progetto D-18";
    private static final int N_COVERS =  150;
    private static Restaurant restaurantInstance;

    private String name;
    private int nCover;
    private Catalogue dishesCatalogue;
    private ArrayList<Reservation> reservationList;


    public Restaurant(String name, int nCover, boolean database) {
        this.name = name;
        this.nCover = nCover;
        this.dishesCatalogue = new Catalogue();
        this.reservationList = new ArrayList<>();

        if (database) readDatabase();
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

    public void addToCatalogue (MenuElement element){
        dishesCatalogue.addElement(element);
    }

    public void removeFromCatalogue (MenuElement elem){
        dishesCatalogue.removeElement(elem);
    }

    public String showCatalogue (){
        String tmp = "Catalogo del ristorante \" " + name + " \" :\n" ;
            tmp += dishesCatalogue.toString();
        return tmp ;
    }

    public static synchronized  Restaurant getRestaurantInstance() {
        if (restaurantInstance == null)
            restaurantInstance =  new Restaurant(NAME, N_COVERS, true);
        return restaurantInstance;
    }

    private synchronized void readDatabase() {
        DbReader dbr = DbReader.getDbReaderInstance();

        executeQuery(dbr, Query.SELECT_ALL_DISHES);
        for (MenuElement elem : dbr.getDishesList())
            this.addToCatalogue(elem);

        executeQuery(dbr, Query.SELECT_ALL_ALLERGENS);
        for (Allergen item : dbr.getAllergensList())
            dishesCatalogue.addAllergen(item);

        dishesCatalogue.getDishes().sort(MenuElement.priceComparator);
        dishesCatalogue.getDishes().sort(MenuElement.typeComparator);

        populateReservations(dbr);

    }

    private synchronized void populateReservations(DbReader dbr){
        executeQuery(dbr, Query.SELECT_RESERVATION);
        reservationList.addAll(dbr.getReservationsList());

        for(Reservation res: reservationList){
            String addToQuery="WHERE RES_CODE ='"+res.getReservationCode()+"' ORDER BY MENU_CODE";
            executeQuery(dbr,Query.editQuery(Query.SELECT_MENU,addToQuery));

            res.getCreatedMenu().addAll(dbr.getMenuList());

            readMenuDishes(res,dbr);

        }
    }
    private synchronized void readMenuDishes(Reservation reservation, DbReader dbr){
        int arraySize = reservation.getCreatedMenu().size();
        String addToQuery;
        for (int menuIndex=0; menuIndex<arraySize; menuIndex++){
            Menu currentMenu = reservation.getCreatedMenu().get(menuIndex);

            addToQuery="WHERE (MENU_CODE='"+menuIndex+"' AND RES_CODE='"+reservation.getReservationCode()+"')";
            executeQuery(dbr,Query.editQuery(Query.SELECT_DISH_IN_MENU,addToQuery));
            for(String code: dbr.getMenuDishesCodeList()){
                currentMenu.getMenuElementsList().add(dishesCatalogue.getElementByCode(code));
            }
        }
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

        insertMenus(reservation,dbr);

    }

    private synchronized void insertMenus(Reservation reservation,DbReader dbr){
        Locale.setDefault(Locale.US);
        int arraySize = reservation.getCreatedMenu().size();
        String addToQuery;

        for (int menuIndex=0; menuIndex<arraySize; menuIndex++){
            Menu currentMenu = reservation.getCreatedMenu().get(menuIndex);

            addToQuery = String.format("('%s','%s','%s',%d)", reservation.getReservationCode(),
                menuIndex, currentMenu.getName(),currentMenu.getnMenuGuests());
            executeQuery(dbr,Query.editQuery(Query.INSERT_MENU,addToQuery));

            insertDishesInMenu(reservation,menuIndex,currentMenu,dbr);
        }
    }

    private synchronized  void insertDishesInMenu(Reservation reservation,int menuIndex,Menu menu,DbReader dbr){
        String addToQuery;
        for(MenuElement elem: menu.getMenuElementsList()){
            addToQuery = String.format("('%s',%s,'%s')", reservation.getReservationCode(),
                    menuIndex, elem.getElementCode());
            executeQuery(dbr,Query.editQuery(Query.INSERT_DISH_IN_MENU,addToQuery));
        }

    }

    // TODO: move to DatabaseReader
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

    // TODO: remove!!
    public static int getStaticCovers(){
        Restaurant r = Restaurant.getRestaurantInstance();
        return r.getnCover();
    }
}