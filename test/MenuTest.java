import menu.*;

import org.junit.*;
import java.util.ArrayList;

/**
 * Class made for testing the class Menu
 */

public class MenuTest {

    private MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER, 4.00, false, false, false);
    private MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE,10.0, false, false, false);
    private MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE,10.00, false, false, false);
    private MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
    private MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
    private MenuElement drink_2 = new MenuElement("Caffè", "DR002", DishType.DRINK, 1.0, false, false, false);

    private Menu menu;
    private Menu menu1;
    private Menu menu0;

    /**
     * Method to create some menus and to populate them
     */
    @Before
    public void initialize() {
        menu = new Menu("MenuOrdinaryTest", 20);
        menu1 = new Menu("MenuLimitTest", 300);
        menu0 = new Menu("MenuEmptyTest", 10);

        menu.addElement(dessert_1);
        menu.addElement(drink_1);
        menu.addElement(drink_2);
        menu.addElement(starter_1);
        menu.addElement(first_1);
        menu.addElement(main_1);

        menu1.addElement(starter_1);
        menu1.addElement(drink_1);
    }


    /**
     * Method for testing the method sortMenu
     */
    @Test
    public void testSort() {
        ArrayList<MenuElement> expected = new ArrayList<>();
        ArrayList<MenuElement> expected1 = new ArrayList<>();
        ArrayList<MenuElement> expected0 = new ArrayList<>();

        expected.add(starter_1);
        expected.add(first_1);
        expected.add(main_1);
        expected.add(dessert_1);
        expected.add(drink_1);
        expected.add(drink_2);

        expected1.add(starter_1);
        expected1.add(drink_1);

        menu.sortMenuElements();
        menu1.sortMenuElements();
        menu0.sortMenuElements();

        Assert.assertArrayEquals(expected.toArray(), menu.getMenuElementsList().toArray());
        Assert.assertArrayEquals(expected1.toArray(), menu1.getMenuElementsList().toArray());
        Assert.assertArrayEquals(expected0.toArray(), menu0.getMenuElementsList().toArray());
    }


    /**
     * Method for testing the method removeElement
     */
    @Test
    public void testRemove(){
        ArrayList<MenuElement> expected = new ArrayList<>();
        ArrayList<MenuElement> expected1 = new ArrayList<>();
        ArrayList<MenuElement> expected0 = new ArrayList<>();

        expected.add(dessert_1);
        expected.add(drink_1);
        expected.add(drink_2);
        expected.add(main_1);

        menu.removeElement(starter_1);
        menu.removeElement(first_1);

        menu1.removeElement(starter_1);
        menu1.removeElement(drink_1);

        Assert.assertArrayEquals(expected.toArray(), menu.getMenuElementsList().toArray());
        Assert.assertArrayEquals(expected1.toArray(), menu1.getMenuElementsList().toArray());
        Assert.assertArrayEquals(expected0.toArray(), menu0.getMenuElementsList().toArray());
    }

    /**
     * Method for testing the method addElement
     */
    @Test
    public void testAddElement(){
        ArrayList<MenuElement> expected = new ArrayList<>();
        ArrayList<MenuElement> expected1 = new ArrayList<>();

        expected.add(starter_1);
        expected.add(first_1);
        expected.add(main_1);
        expected.add(dessert_1);
        expected.add(drink_1);
        expected.add(drink_2);

        expected1.add(starter_1);
        expected1.add(first_1);
        expected1.add(dessert_1);
        expected1.add(drink_1);

        menu.addElement(starter_1); //try to add element that is already in the menu

        menu1.addElement(first_1);
        menu1.addElement(dessert_1);

        menu0.addElement(starter_1);
        menu0.addElement(first_1);
        menu0.addElement(main_1);
        menu0.addElement(dessert_1);
        menu0.addElement(drink_1);
        menu0.addElement(drink_2);

        // have to sort menus for testing
        menu.sortMenuElements();
        menu1.sortMenuElements();

        Assert.assertArrayEquals(expected.toArray(), menu.getMenuElementsList().toArray());
        Assert.assertArrayEquals(expected1.toArray(), menu1.getMenuElementsList().toArray());
        Assert.assertArrayEquals(expected.toArray(), menu0.getMenuElementsList().toArray());
    }

    /**
     * Method for testing the method getMenuCost
     */
    @Test
    public void testGetMenuCost(){

        double expected = 4 + 10 + 10 + 4 + 1 + 1;
        double expected1 = 4 + 1;
        double expected0 = 0;

        Assert.assertEquals(expected, menu.getMenuCost(), 0.00);
        Assert.assertEquals(expected1, menu1.getMenuCost(), 0.00);
        Assert.assertEquals(expected0, menu0.getMenuCost(), 0.00);

    }

}
