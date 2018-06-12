package restaurant;

import menu.DishType;
import menu.Menu;
import menu.MenuElement;
import services.DbReader;
import services.Query;

import java.util.ArrayList;
import java.util.Collections;

public class Restaurant {
    private static Restaurant restaurantInstance;
    private String name;
    private int nCover;
    private Catalogue dishesCatalogue;
    private ArrayList<Reservation> reservationList;


    public Restaurant(String name, int nCover, boolean database) throws InterruptedException {
        this.name = name;
        this.nCover = nCover;
        this.dishesCatalogue = new Catalogue();
        this.reservationList = new ArrayList<>();

        if(database){
            DbReader dbr = DbReader.getDbReaderInstance();
            Thread reservationReaderThread = new Thread(dbr);
            Thread dishesReaderThread = new Thread(dbr);

            dbr.setQuery(Query.SELECT_RESERVATION);
            reservationReaderThread.start();
            reservationReaderThread.join();

            this.reservationList.addAll(dbr.getReservationsList());

            dbr.setQuery(Query.SELECT_ALL_DISHES);
            dishesReaderThread.start();
            dishesReaderThread.join();

            for(MenuElement elem : dbr.getDishesList()){
                this.addToCatalogue(elem);
            }

            Collections.sort(this.dishesCatalogue.getDishes(),MenuElement.priceComparator);
            Collections.sort(this.dishesCatalogue.getDishes(),MenuElement.typeComparator);
        }


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

    public void addToCatalogue ( MenuElement element){

        dishesCatalogue.addElement(element);

    }

    public void removeFromCatalogue ( MenuElement elem){

        dishesCatalogue.removeElement(elem);

    }

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

    public static synchronized  Restaurant getRestaurantInstance() throws InterruptedException {
        if(restaurantInstance==null)
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
}