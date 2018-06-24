import menu.DishType;
import menu.Menu;
import menu.MenuElement;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import restaurant.Reservation;

import java.util.Date;

public class ReservationTest {

    Reservation r1 = new Reservation("R1",50,new Date("2018/5/13"),"Piero Angela","ciao@ciao.com");

    Menu m1 = new Menu("first menu", 10);
    Menu m2 = new Menu("second menu", 12);
    Menu m3 = new Menu("third menu", 8);

    MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER, 4.00, false, false, false);
    MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE,10.0, false, false, false);
    MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE,10.00, false, false, false);
    MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
    MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
    MenuElement drink_2 = new MenuElement("Caffè", "DR002", DishType.DRINK, 1.0, false, false, false);

    @Before
    public void initialize() {
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
    }

   @Test
    public void reservationCostTest(){
        double expectedCost = 55.0;
        Assert.assertEquals(expectedCost, r1.getReservationCost(), 0.0);
   }

    @Test
    public void removeMenuTest(){
        r1.removeMenu(m1);
        double expectedCost = 36.0;
        Assert.assertEquals(expectedCost, r1.getReservationCost(), 0.0);
    }

    /*
    @Test
    public void settersTest() {
        r1.setnGuests(120);
        r1.setBudget(250.0);
        int expectedGuests = 120;
        double expectedBudget = 250.0;

        Assert.assertEquals(expectedGuests, r1.getnGuests());
        Assert.assertEquals(expectedBudget, r1.getBudget(), 0.0);
    }
    */
}
