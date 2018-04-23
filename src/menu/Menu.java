package menu;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu {
    private String name;
    private ArrayList<MenuElement> menuElementsList;
    private double menuCost;
    private int nMenuGuests;

    public Menu(String name) {
        this.name = name;
        this.menuElementsList = new ArrayList<>();
    }

    public void addElement(MenuElement newElement) {
        menuElementsList.add(newElement);
    }

    public void removeElement(MenuElement element) {
        Iterator<MenuElement> iter = menuElementsList.iterator();
        while (iter.hasNext()) {
            MenuElement el = iter.next();
            if (el.equals(element)) {
                iter.remove();
            }
        }
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append("\t").append(this.menuCost).append("\t").append(this.nMenuGuests).append("\t");
        for (MenuElement el : menuElementsList) {
            sb.append(el.toString()).append("\t");
        }
        return sb.toString();
    }
}
