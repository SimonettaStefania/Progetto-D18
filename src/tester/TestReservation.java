package tester;

import menu.DishType;
import menu.Menu;
import menu.MenuElement;
import restaurant.Reservation;

import java.util.Date;

public class TestReservation {

    public static void main(String[] args) {

        Reservation r1 = new Reservation(30,300,new Date(2018-05-13));

        Menu m1 = new Menu("first menu", 10);
        Menu m2 = new Menu("second menu", 12);
        Menu m3 = new Menu("third menu", 8);

        MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER, 4.00, false, false, false);
        MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE,10.0, false, false, false);
        MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE,10.00, false, false, false);
        MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
        MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
        MenuElement drink_2 = new MenuElement("Caffè", "DR002", DishType.DRINK, 1.0, false, false, false);

        // add menu in the reservation
        r1.addMenu(m1);
        r1.addMenu(m2);
        r1.addMenu(m3);

        // add some elements in the menus
        r1.addDish(m2, drink_2);
        r1.addDish(m3, drink_1);
        r1.addDish(m3, first_1);
        r1.addDish(m3, main_1);
        r1.addDish(m1, dessert_1);
        r1.addDish(m1, starter_1);
        r1.addDish(m2, starter_1);
        r1.addDish(m1, first_1);
        r1.addDish(m2, first_1);
        r1.addDish(m1, drink_1);

        // remove some elements from the menus
        r1.removeDish(m2, drink_2);
        r1.removeDish(m1, starter_1);
        r1.removeDish(m3, starter_1);  // starter_1 there isn't in the third menu

        // now i have to sort elements in the menus
        m1.sortMenuElements();
        m2.sortMenuElements();
        m3.sortMenuElements();

        // remove second menu and edit nGuests and budget
        r1.removeMenu(m2);
        r1.setnGuests(18);
        r1.setBudget(200);

        // calculate reservationCost

        r1.calculateReservationCost();

        // check
        System.out.println(r1.toString());


    }

}
