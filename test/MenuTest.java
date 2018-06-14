import menu.DishType;
import menu.Menu;
import menu.MenuElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class MenuTest {

    MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER, 4.00, false, false, false);
    MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE,10.0, false, false, false);
    MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE,10.00, false, false, false);
    MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
    MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
    MenuElement drink_2 = new MenuElement("Caffè", "DR002", DishType.DRINK, 1.0, false, false, false);

    Menu menu;
    @Before
    public void initialize() {
        menu = new Menu("MenuTest", 20);
        menu.addElement(dessert_1);
        menu.addElement(drink_1);
        menu.addElement(drink_2);
        menu.addElement(starter_1);
        menu.addElement(first_1);
        menu.addElement(main_1);
    }


    // Method to test menu sorting
    @Test
    public void testSort() {
        ArrayList<MenuElement> expected = new ArrayList<>();
        expected.add(starter_1);
        expected.add(first_1);
        expected.add(main_1);
        expected.add(dessert_1);
        expected.add(drink_1);
        expected.add(drink_2);

        menu.sortMenuElements();

        Assert.assertArrayEquals(expected.toArray(), menu.getMenuElementsList().toArray());
    }


    // Method to test removeElement
    @Test
    public void testRemove(){
        ArrayList<MenuElement> expected = new ArrayList<>();
        expected.add(dessert_1);
        expected.add(drink_1);
        expected.add(drink_2);
        expected.add(main_1);

        menu.removeElement(starter_1);
        menu.removeElement(first_1);

        Assert.assertArrayEquals(expected.toArray(), menu.getMenuElementsList().toArray());
    }

}
