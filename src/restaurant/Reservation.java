package restaurant;

import menu.Menu;
import menu.MenuElement;
import services.MenuGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private int nGuests;
    private Date eventDate;
    private ArrayList<Menu> createdMenu, optimizedMenu;
    private String reservationCode, customerNameSurname, customerMail;
    
    // TODO: update UML
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

    public void createMenu(String name, int people, String[] selectedDishes) {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        Catalogue catalogue = restaurant.getDishesCatalogue();

        if (name.isEmpty())
            name = "Menu personalizzato";

        if (selectedDishes != null) {
            Menu menu = new Menu(name, people);
            for (String id : selectedDishes) {
                MenuElement item = catalogue.getElementByCode(id);
                menu.addElement(item);
            }

            this.addMenu(menu);
        }
    }

    public void addOptimizedMenu (int index) {
        Menu selected = optimizedMenu.get(index);
        this.addMenu(selected);
    }

    public void removeMenu(int index) {
        createdMenu.remove(index);
    }

    public void addDish(Menu menuWhereAddDish, MenuElement dishToAdd) {
        menuWhereAddDish.addElement(dishToAdd);
    }

    public void removeDish(Menu menuWhereRemoveDish, MenuElement dishToRemove) {
        menuWhereRemoveDish.removeElement(dishToRemove);
    }

    public double getReservationCost() {
        double reservationCost = 0;
        for (Menu m : createdMenu)
            reservationCost += m.getMenuCost() * m.getnMenuGuests();
        return reservationCost;
    }

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

    public void setReservationCode(String id) {
        reservationCode = id;
    }

    public void generateOptimizedMenus(double budget) {
        MenuGenerator menuGenerator = new MenuGenerator(budget);
        optimizedMenu = menuGenerator.getGeneratedMenu();
    }
}