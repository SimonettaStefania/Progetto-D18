package restaurant;

import menu.Menu;
import menu.MenuElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Reservation {
    private String reservationCode;
    private int nGuests;
    private double budget;
    private ArrayList<Menu> createdMenu;
    private double reservationCost;
    private Date eventDate;
    private String customerNameSurname;
    private String customerMail;



    public Reservation(String reservationCode, int nGuests, Date eventDate, String customerNameSurname, String customerMail) {
        this.reservationCode=reservationCode;
        this.nGuests = nGuests;
        this.createdMenu = new ArrayList<>();
        this.reservationCost = 0;
        this.eventDate = eventDate;
        this.customerNameSurname=customerNameSurname;
        this.customerMail =customerMail;

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

    public void removeDish(Menu menuWhereRemoveDish, MenuElement dishToRemove) {
        menuWhereRemoveDish.removeElement(dishToRemove);
    }


    public void calculateReservationCost(){   // method to calculate all menus's cost  TODO add this method in the UML
        reservationCost = 0;
        for (Menu m : createdMenu) {
            m.calculateMenuCost();
            reservationCost += m.getMenuCost();
        }
    }

    public String toString() {                    // TODO add this method in the UML
        StringBuilder sb = new StringBuilder();
        sb.append("Reservation: ").append(this.reservationCode).append("\tCliente: ").append(this.customerNameSurname).append("\tBudget: ").append(this.budget).append("\t nGuest: ").append(this.nGuests)
                .append("\t ReservationCost: ").append(this.reservationCost).append(("\t eventDate: ")).append(this.eventDate).append("\n");
        for (Menu m : createdMenu) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
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

    public String getReservationCode() {
        return reservationCode;
    }

    public int getnGuests() {
        return nGuests;
    }

    public double getBudget() {
        return budget;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getCustomerNameSurname() {
        return customerNameSurname;
    }

    public String getCustomerMail() {
        return customerMail;
    }
}