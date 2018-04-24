package restaurant;

import menu.Menu;
import menu.MenuElement;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private int nCover;
    private Catalogue dishesCatalogue;
    private ArrayList<Reservation> reservationList;

    public Restaurant(String name, int nCover, Catalogue dishesCatalogue, ArrayList<Reservation> reservationList) {
        this.name = name;
        this.nCover = nCover;
        this.dishesCatalogue = dishesCatalogue;
        this.reservationList = new ArrayList<>();
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

    public void addDish(Reservation res, Menu menu, MenuElement menuElement){
        res.addDish(menu, menuElement);
    }

    public void removeDish(Reservation res, Menu menu, MenuElement menuElement){
        res.removeDish(menu, menuElement);
    }
}