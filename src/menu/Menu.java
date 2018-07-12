package menu;

import java.util.ArrayList;

public class Menu {
    private String name;
    private ArrayList<MenuElement> menuElementsList;
    private int nMenuGuests;

    public Menu(String name, int nMenuGuests) {
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

    public double getMenuCost() {
        double menuCost = 0;
        for (MenuElement el : menuElementsList)
            menuCost += el.getPrice();
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append("\t menuCost: ").append(this.getMenuCost()).append("\t nGuest: ").append(this.nMenuGuests).append("\n");
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
