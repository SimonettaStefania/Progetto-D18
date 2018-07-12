package restaurant;

import menu.Menu;
import menu.MenuElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private int nGuests;
    private Date eventDate;
    private ArrayList<Menu> createdMenu, optimizedMenu;
    private String reservationCode, customerNameSurname, customerMail;


    public Reservation(String reservationCode, int nGuests, Date eventDate, String customerNameSurname, String customerMail) {
        this.reservationCode = reservationCode;
        this.nGuests = nGuests;
        this.createdMenu = new ArrayList<>();
        this.optimizedMenu = new ArrayList<>();
        this.eventDate = eventDate;
        this.customerNameSurname = customerNameSurname;
        this.customerMail = customerMail;
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
        double reservationCost = 0;
        for (Menu m : createdMenu) {
            m.calculateMenuCost();
            reservationCost += m.getMenuCost() * m.getnMenuGuests();
        }
        return reservationCost;
    }

    // TODO add this method in the UML
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sb.append("Reservation: ").append(this.reservationCode).append("\tCliente: ").append(this.customerNameSurname).append("\t nGuest: ").append(this.nGuests)
                .append("\t ReservationCost: ").append(this.getReservationCost()).append(("\t eventDate: ")).append(sdf.format(this.eventDate)).append("\n");
        for (Menu m : createdMenu) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public int getnGuests() {
        return nGuests;
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