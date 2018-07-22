import menu.*;
import org.junit.*;
import restaurant.Reservation;

import java.util.Date;

public class ReservationTest {

    private Reservation r1 = new Reservation("R1",50,new Date("2018/5/13"),"Piero Angela","ciao@ciao.com");

    private Menu m1 = new Menu("first menu", 10);
    private Menu m2 = new Menu("second menu", 12);
    private Menu m3 = new Menu("third menu", 8);

    private MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER, 4.00, false, false, false);
    private MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE,10.0, false, false, false);
    private MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE,10.00, false, false, false);
    private MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
    private MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
    private MenuElement drink_2 = new MenuElement("Caffè", "DR002", DishType.DRINK, 1.0, false, false, false);

    @Before
    public void initialize() {
        // add menu in the reservation
        r1.addMenu(m1);
        r1.addMenu(m2);
        r1.addMenu(m3);

        // add some elements in the menus
        m2.addElement(drink_2);
        m3.addElement(drink_1);
        m3.addElement(first_1);
        m3.addElement(main_1);
        m1.addElement(dessert_1);
        m1.addElement(starter_1);
        m2.addElement(starter_1);
        m1.addElement(first_1);
        m2.addElement(first_1);
        m1.addElement(drink_1);
    }

   @Test
    public void reservationCostTest() {
       double expectedCost = 19.0*10 + 15.0*12 + 21.0*8;
       Assert.assertEquals(expectedCost, r1.getReservationCost(), 0.0);
   }

    @Test
    public void removeMenuTest() {
        r1.removeMenu(0);
        double expectedCost = 15.0*12 + 21.0*8;
        Assert.assertEquals(expectedCost, r1.getReservationCost(), 0.0);
    }
}
