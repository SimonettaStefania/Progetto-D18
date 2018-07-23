import menu.DishType;
import menu.Menu;
import menu.MenuElement;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import restaurant.Reservation;

import java.util.ArrayList;
import java.util.Date;

public class ReservationTest {

    Reservation r1 = new Reservation("R1", 50, new Date("2018/5/13"), "Piero Angela", "ciao@ciao.com");
    Reservation r0 = new Reservation("R0", 0, new Date("2018/5/13"), "Pikachu", "pika@ciao.com");

    Menu m1 = new Menu("first menu", 10);
    Menu m2 = new Menu("second menu", 30);
    Menu m3 = new Menu("MenuNoElements", 8);
    Menu m0 = new Menu("MenuNoGuests", 0);

    Menu mCreated = new Menu("menu creato", 10);

    MenuElement starter_1 = new MenuElement("Bruschetta", "S001", DishType.STARTER, 4.00, false, false, false);
    MenuElement starter_2 = new MenuElement("TAGLIERE SALUMI MISTI", "A01", DishType.STARTER, 5.00, false, false, false);
    MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE, 10.0, false, false, false);
    MenuElement first_2 = new MenuElement("PAELLA", "P01", DishType.FIRST_COURSE, 12.5, false, false, false);
    MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE, 10.00, false, false, false);
    MenuElement main_2 = new MenuElement("ARAGOSTA ALLA CATALANA", "S01", DishType.MAIN_COURSE, 18.5, false, false, false);
    MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
    MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
    MenuElement drink_2 = new MenuElement("Caffè", "DR002", DishType.DRINK, 1.0, false, false, false);


    @Before
    public void initialize() {
        // add menu in the reservation
        r1.addMenu(m1);
        r1.addMenu(m2);
        r1.addMenu(m3);
        r1.addMenu(m0);

        r0.addMenu(m3);

        // add some elements in the menus
        m1.addElement(main_1);
        m1.addElement(drink_1);
        m1.addElement(dessert_1);
        m1.addElement(starter_1);
        m1.addElement(first_1);
        m2.addElement(starter_1);
        m2.addElement(drink_2);
        m2.addElement(first_1);
    }

    @Test
    public void calculateGuestTest() {
        int expected1 = 10 + 30 + 8 + 0;
        int expected0 = 8;

        Assert.assertEquals(expected1, r1.calculateGuests());
        Assert.assertEquals(expected0, r0.calculateGuests());
    }

    @Test
    public void checkPeopleTest() {

        Assert.assertFalse(r1.checkPeople());
        Assert.assertFalse(r0.checkPeople());
    }

    @Test
    public void reservationCostTest() {
        double expectedCost1 = 29.0 * 10 + 15.0 * 30 + 0.0 * 8 + 0.0 * 0;
        double expectedCost0 = 0.0 * 10;

        Assert.assertEquals(expectedCost1, r1.getReservationCost(), 0.0);
        Assert.assertEquals(expectedCost0, r0.getReservationCost(), 0.0);
    }


    @Test
    public void createdMenuTest() {
        ArrayList<Menu> expected = new ArrayList<>();

        mCreated.addElement(starter_2);
        mCreated.addElement(first_2);
        mCreated.addElement(main_2);

        expected.add(m1);
        expected.add(m2);
        expected.add(m3);
        expected.add(m0);
        expected.add(mCreated);

        r1.createMenu("menu creato", 10, new String[]{"A01", "P01", "S01"});

        Assert.assertEquals(expected.get(4).toString(), r1.getCreatedMenu().get(4).toString());
    }

    @Test
    public void removeMenuTest() {
        double expectedCost = 15.0 * 30 + 0.0 * 8 + 0.0 * 0;

        r1.removeMenu(0);

        Assert.assertEquals(expectedCost, r1.getReservationCost(), 0.0);
    }
}

