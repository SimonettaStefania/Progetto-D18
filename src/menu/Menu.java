package menu;

import java.util.ArrayList;

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

    public Menu(){
        this.menuElementsList = new ArrayList<>();
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

    // TODO: is variable menuCost really needed?
    // Could be replaced by making the sum of all the elements in the menu
    public double getMenuCost() {
        return menuCost;
    }

    public int getnMenuGuests() {
        return nMenuGuests;
    }

    public String getName() {
        return name;
    }

    // Method that sorts elementListMenu TODO add this method in the UML
    public void sortMenuElements(){
        menuElementsList.sort(MenuElement.typeComparator);
    }

    // method that calculates all menus's cost  TODO add this method in the UML
    public void calculateMenuCost(){
        menuCost = 0;
        for (MenuElement el : menuElementsList) {
            menuCost += el.getPrice();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append("\t menuCost: ").append(this.menuCost).append("\t nGuest: ").append(this.nMenuGuests).append("\n");
        for (MenuElement el : menuElementsList) {
            sb.append(el.toString()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<MenuElement> getMenuElementsList() {
        return menuElementsList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
