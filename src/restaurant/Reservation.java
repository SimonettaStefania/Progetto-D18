package restaurant;

import menu.Menu;
import menu.MenuElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Reservation {
    private String reservationCode;
    private int nGuests;
    private double budget;
    private ArrayList<Menu> createdMenu;
    private ArrayList<Menu> optimizedMenu;
    private double reservationCost;
    private Date eventDate;
    private String customerNameSurname;
    private String customerMail;



    public Reservation(String reservationCode, int nGuests, Date eventDate, String customerNameSurname, String customerMail) {
        this.reservationCode=reservationCode;
        this.nGuests = nGuests;
        this.createdMenu = new ArrayList<>();
        this.optimizedMenu = new ArrayList<>();
        this.reservationCost = 0;
        this.eventDate = eventDate;
        this.customerNameSurname=customerNameSurname;
        this.customerMail =customerMail;

    }

    public Reservation(String reservationCode, Date eventDate, String customerNameSurname, String customerMail) {
        this.reservationCode=reservationCode;
        this.createdMenu = new ArrayList<>();
        this.optimizedMenu = new ArrayList<>();
        this.reservationCost = 0;
        this.eventDate = eventDate;
        this.customerNameSurname=customerNameSurname;
        this.customerMail =customerMail;

    }

    public boolean checkPeople() {
        return nGuests == calculateGuests();
    }

    public int calculateGuests() {
        int realGuests = 0;
        for(Menu m : createdMenu)
            realGuests += m.getnMenuGuests();

        return realGuests;
    }

    public void addMenu(Menu newMenu) {
        createdMenu.add(newMenu);
    }

    public void removeMenu(Menu menuWantRemove) {
        createdMenu.removeIf(m -> m.equals(menuWantRemove));
    }

    public void addDish(Menu menuWhereAddDish, MenuElement dishToAdd) {
        menuWhereAddDish.addElement(dishToAdd);
    }

    public void removeDish(Menu menuWhereRemoveDish, MenuElement dishToRemove) {
        menuWhereRemoveDish.removeElement(dishToRemove);
    }

    // TODO change this method in the UML
    public double getReservationCost() {
        reservationCost = 0;
        for (Menu m : createdMenu) {
            m.calculateMenuCost();
            reservationCost += m.getMenuCost();
        }
        return reservationCost;
    }

    // TODO add this method in the UML
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sb.append("Reservation: ").append(this.reservationCode).append("\tCliente: ").append(this.customerNameSurname).append("\tBudget: ").append(this.budget).append("\t nGuest: ").append(this.nGuests)
                .append("\t ReservationCost: ").append(this.reservationCost).append(("\t eventDate: ")).append(sdf.format(this.eventDate)).append("\n");
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

    public ArrayList<Menu> getOptimizedMenu() {
        return optimizedMenu;
    }

    public ArrayList<Menu> getCreatedMenu() {
        return createdMenu;
    }
}