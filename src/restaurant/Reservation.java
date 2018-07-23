package restaurant;

import menu.Menu;
import menu.MenuElement;
import services.MenuGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class that represents a reservation.
 * It is described by:
 * -nGuest: number of guest of the event
 * -eventDate: reservations's date
 * -createdMenu: list of chosen menus for the reservation
 * -reservationCode: unique identifier for the reservation
 * -customerNameSurname
 * -customerMail
 */

public class Reservation {
    private int nGuests;

    private Date eventDate;
    private ArrayList<Menu> createdMenu;
    private String reservationCode, customerNameSurname, customerMail;
    private MenuGenerator menuGenerator;

    /**
     * Constructor with the following parameters
     * @param reservationCode reservation identifier
     * @param nGuests number of guests
     * @param eventDate event's date
     * @param customerNameSurname customer's identity
     * @param customerMail customer's email
     */
    // TODO: update UML
    public Reservation(String reservationCode, int nGuests, Date eventDate, String customerNameSurname, String customerMail) {
        this.reservationCode = reservationCode;
        this.customerNameSurname = customerNameSurname;
        this.customerMail = customerMail;
        this.eventDate = eventDate;
        this.nGuests = nGuests;
        this.createdMenu = new ArrayList<>();
    }

    /**
     * Getter that returns reservation's identifier
     * @return code of the reservation
     */

    public String getReservationCode() {
        return reservationCode;
    }

    /**
     * Getter that returns cutomer identity
     * @return name and surname of the customer
     */

    public String getCustomerNameSurname() {
        return customerNameSurname;
    }

    /**
     * Getter that returns customer email
     * @return email of the customer
     */

    public String getCustomerMail() {
        return customerMail;
    }

    /**
     * Getter that returns event date
     * @return date of the event
     */

    public Date getEventDate() {
        return eventDate;
    }

    /**
     * Getter that returns the number of the guests
     * @return number of the guests
     */

    public int getnGuests() {
        return nGuests;
    }

    /**
     * Getter that returns the list of the chosen menus
     * @return chosen menus list
     */

    public ArrayList<Menu> getCreatedMenu() {
        return createdMenu;
    }

    /**
     * Method that sets the reservation identifier from a given code
     * @param id given code
     */

    public void setReservationCode(String id) {
        reservationCode = id;
    }

    /**
     * Method that checks that the number of guests of the reservation equals the sum of
     * the number of guests of each menu.
     * For a correct behavior of the program, the 2 quantities must match
     * @return true if nGuest equals the sum, otherwise it returns false
     */

    public boolean checkPeople() {
        return nGuests == calculateGuests();
    }

    /**
     * Method that calculates the sum of all the chosen menus guests
     * @return total number of the menus guests
     */

    public int calculateGuests() {
        int realGuests = 0;
        for(Menu m : createdMenu)
            realGuests += m.getnMenuGuests();

        return realGuests;
    }

    /**
     * Method that returns the total cost of the reservation by calculating the sum
     * of each chosen menu cost
     * @return reservation total cost
     */

    public double getReservationCost() {
        double reservationCost = 0;
        for (Menu m : createdMenu)
            reservationCost += m.getMenuCost() * m.getnMenuGuests();
        return reservationCost;
    }

    /**
     * Method that adds a menu to the chosen menus list (createdMenu)
     * @param newMenu menu to add
     */

    public void addMenu(Menu newMenu) {
        createdMenu.add(newMenu);
    }

    /**
     * Method that adds a menu from the chosen menus list
     * @param index index of the menu to remove
     */

    public void removeMenu(int index) {
        createdMenu.remove(index);
    }

    /**
     * Methot that creates a new Menu
     * @param name name of the menu
     * @param people number of the people which will eat the menu's dishes
     * @param selectedDishes array of the codes of the dishes to add to the Menu
     */
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

    /**
     * Method that generates optimized menus by using a MenuGenerator instance
     * @param budget budget for the menus
     * @param people number of the people which will eat the menu's dishes
     */
    public void generateOptimizedMenus(double budget, int people) {
        menuGenerator = new MenuGenerator(budget, people);
    }

    /**
     * Method that returns the optimized menus list generated by the menuGenerator
     * @return optimized menu list
     */

    public ArrayList<Menu> getOptimizedMenu() {
        if (menuGenerator == null)  return null;
        return menuGenerator.getGeneratedMenu();
    }

    /**
     * Method that select a specific menu from the optimized menus list (created by menuGenerator) and
     * add it to the chosen menus list (createdMenu)
     * @param index index of the menu to select
     */

    public void addOptimizedMenu (int index) {
        ArrayList<Menu> optimizedMenu = menuGenerator.getGeneratedMenu();
        Menu selected = optimizedMenu.get(index);
        this.addMenu(selected);
    }

    /**
     * Method that generates a string with the reservation details
     * @return string with the details
     */

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