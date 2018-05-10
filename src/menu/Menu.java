package menu;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Menu {
    private String name;
    private ArrayList<MenuElement> menuElementsList;
    private double menuCost;  //TODO exception if menuCost > budgetMenu
    private int nMenuGuests;

    public Menu(String name, int nMenuGuests) {          // TODO exception if nMenusGuests > nGuests or nMenusGuests < nGuests
        this.name = name;
        this.menuElementsList = new ArrayList<>();
        this.nMenuGuests = nMenuGuests;
    }

    public void addElement(MenuElement newElement) {

        if(!menuElementsList.contains(newElement))  //check if newElement is already in the menuElementList
        menuElementsList.add(newElement);
    }

    public void removeElement(MenuElement element) {
       menuElementsList.remove(element);
    }

    public void setNMenuGuests(int nGuest) {
        this.nMenuGuests = nGuest;
    }

    public void updateMenuCost(double cost) {
        this.menuCost = cost;
    }

    public double getMenuCost() {
        return menuCost;
    }

    public int getnMenuGuests() {
        return nMenuGuests;
    }


    public void sortMenuElements(){ Collections.sort(this.menuElementsList); }  // Method that sorts elementListMenu TODO add this method in the UML


    // method that calculates all menus's cost  TODO add this method in the UML
    public void calculateMenuCost(){
        menuCost = 0;
        for (MenuElement el : menuElementsList) {
            menuCost += el.getPrice();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append("\t menuCost: ").append(this.menuCost).append("\t nGuest: ").append(this.nMenuGuests).append("\t");
        for (MenuElement el : menuElementsList) {
            sb.append(el.toString()).append("\t");
        }
        return sb.toString();
    }
}
