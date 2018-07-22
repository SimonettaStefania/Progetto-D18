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
    private ArrayList<Menu> createdMenu;
    private String reservationCode, customerNameSurname, customerMail;
    private MenuGenerator menuGenerator;

    // TODO: update UML
    public Reservation(String reservationCode, int nGuests, Date eventDate, String customerNameSurname, String customerMail) {
        this.reservationCode = reservationCode;
        this.customerNameSurname = customerNameSurname;
        this.customerMail = customerMail;
        this.eventDate = eventDate;
        this.nGuests = nGuests;
        this.createdMenu = new ArrayList<>();
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public String getCustomerNameSurname() {
        return customerNameSurname;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public int getnGuests() {
        return nGuests;
    }

    public ArrayList<Menu> getCreatedMenu() {
        return createdMenu;
    }

    public void setReservationCode(String id) {
        reservationCode = id;
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

    public double getReservationCost() {
        double reservationCost = 0;
        for (Menu m : createdMenu)
            reservationCost += m.getMenuCost() * m.getnMenuGuests();
        return reservationCost;
    }

    public void addMenu(Menu newMenu) {
        createdMenu.add(newMenu);
    }

    public void removeMenu(int index) {
        createdMenu.remove(index);
    }

    public void createMenu(String name, int people, String[] selectedDishes) {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        Catalogue catalogue = restaurant.getDishesCatalogue();

        if (name.isEmpty())
            name = "Personalized menu";

        if (selectedDishes != null) {
            Menu menu = new Menu(name, people);
            for (String id : selectedDishes) {
                MenuElement item = catalogue.getElementByCode(id);
                menu.addElement(item);
            }

            this.addMenu(menu);
        }
    }

    public void generateOptimizedMenus(double budget, int people) {
        menuGenerator = new MenuGenerator(budget, people);
    }

    public ArrayList<Menu> getOptimizedMenu() {
        if (menuGenerator == null)  return null;
        return menuGenerator.getGeneratedMenu();
    }

    public void addOptimizedMenu (int index) {
        ArrayList<Menu> optimizedMenu = menuGenerator.getGeneratedMenu();
        Menu selected = optimizedMenu.get(index);
        this.addMenu(selected);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sb.append("Reservation: ").append(reservationCode).append((" - ")).append(sdf.format(eventDate)).append("\nCliente: ").append(customerNameSurname);
        sb.append(" - ").append(customerMail).append("\nGuests: ").append(nGuests).append("\nReservation cost: ").append(getReservationCost()).append("\n\n");

        for (Menu m : createdMenu)
            sb.append(m.toString());

        return sb.toString();
    }
}