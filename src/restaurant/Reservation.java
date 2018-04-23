package restaurant;

import menu.Menu;
import menu.MenuElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Reservation {
    private int nGuests;
    private double budget;
    private ArrayList<Menu> createdMenu;
    private double reservationCost;
    private Date eventDate;


    public Reservation(int nGuests, double budget, ArrayList<Menu> createdMenu, double reservationCost, Date eventDate) {
        this.nGuests = nGuests;
        this.budget = budget;
        this.createdMenu = new ArrayList<>();
        this.reservationCost = reservationCost;
        this.eventDate = eventDate;
    }

    public boolean checkPeople(int nPeople) {
        return nPeople == nGuests;
    }

    public boolean checkBudget(int realCost) {
        return realCost == reservationCost;

    }

    public void addMenu(Menu newMenu) {     // newMenu is the menu that you want to add in the arraylist createdMenu
        createdMenu.add(newMenu);
    }

    public void removeMenu(Menu menuWantRemove) {
        Iterator<Menu> iter = createdMenu.iterator();

        while(iter.hasNext()){
            Menu m = iter.next();
            if(m.equals(menuWantRemove)) {
                iter.remove();
            }
        }
    }

    public void addDish(Menu menuWhereAddDish, MenuElement dishToAdd) {
        menuWhereAddDish.addElement(dishToAdd);
    }

    public void revomeDish(Menu menuWhereRemoveDish, MenuElement dishToRemove) {
        menuWhereRemoveDish.removeElement(dishToRemove);
    }

    public void setnGuests(int nGuests) {
        this.nGuests = nGuests;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getReservationCost() {
        return reservationCost;
    }
}