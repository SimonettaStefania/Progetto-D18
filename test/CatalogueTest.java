import menu.*;
import restaurant.Catalogue;

import org.junit.*;
import java.util.ArrayList;

/**
 * Class made for testing the class Catalogue
 */
public class CatalogueTest {

    private Catalogue c = new Catalogue();

    private MenuElement starter_1 = new MenuElement("BRUSCHETTA", "A01", DishType.STARTER, 4.00, false, true, false);
    private MenuElement starter_2 = new MenuElement("TAGLIERE SALUMI MISTI", "A02", DishType.STARTER, 5.00, false, false, true);
    private MenuElement first_1 = new MenuElement("SPAGHETTI ALLA CARBONARA", "P01", DishType.FIRST_COURSE, 10.0, false, false, false);
    private MenuElement first_2 = new MenuElement("PAELLA", "P02", DishType.FIRST_COURSE, 12.5, false, false, false);
    private MenuElement main_1 = new MenuElement("PEPATA DI COZZE", "S01", DishType.MAIN_COURSE, 10.00, false, false, true);
    private MenuElement main_2 = new MenuElement("SETAN ALLA PIZZAIOLA", "S02", DishType.MAIN_COURSE, 18.5, true, true, true);
    private MenuElement dessert_1 = new MenuElement("CHEESECAKE AI LAMPONI", "D01", DishType.DESSERT, 4.00, false, true, false);
    private MenuElement drink_1 = new MenuElement("ACQUA", "B01", DishType.DRINK, 1.0, true, true, true);
    private MenuElement drink_2 = new MenuElement("CAFFÈ", "B02", DishType.DRINK, 1.0, true, true, true);

    private Allergen a1 = new Allergen("B", "CROSTACEI E DERIVATI");
    private Allergen a2 = new Allergen("G", "LATTE E DERIVATI");

    /**
     * Method for populate an object Catalogue and for add some allergens into dishes
     */
    @Before
    public void inizialize(){

        c.addElement(starter_1);
        c.addElement(starter_2);
        c.addElement(first_1);
        c.addElement(first_2);
        c.addElement(main_1);
        c.addElement(main_2);
        c.addElement(dessert_1);
        c.addElement(drink_1);
        c.addElement(drink_2);

        first_2.addAllergen(a1);
        first_1.addAllergen(a2);
        dessert_1.addAllergen(a2);

    }

    /**
     * Method for testing the method getFilteredTest:
     * from the first to third test are about flags,
     * the others are also on allergens
     */
    @Test
    public void getFilteredListTest(){

        ArrayList<MenuElement> expectedCel = new ArrayList<>();
        ArrayList<MenuElement> expectedVgt = new ArrayList<>();
        ArrayList<MenuElement> expectedVeg = new ArrayList<>();
        ArrayList<MenuElement> expectedAll = new ArrayList<>();
        ArrayList<MenuElement> expectedAllergens = new ArrayList<>();
        ArrayList<MenuElement> expectedAllergensVgt = new ArrayList<>();

        expectedAll.add(main_2);
        expectedAll.add(drink_1);
        expectedAll.add(drink_2);

        expectedCel.add(starter_2);
        expectedCel.add(main_1);
        expectedCel.add(main_2);
        expectedCel.add(drink_1);
        expectedCel.add(drink_2);

        expectedVeg.add(main_2);
        expectedVeg.add(drink_1);
        expectedVeg.add(drink_2);

        expectedVgt.add(starter_1);
        expectedVgt.add(main_2);
        expectedVgt.add(dessert_1);
        expectedVgt.add(drink_1);
        expectedVgt.add(drink_2);

        expectedAllergens.add(starter_1);
        expectedAllergens.add(starter_2);
        expectedAllergens.add(main_1);
        expectedAllergens.add(main_2);
        expectedAllergens.add(drink_1);
        expectedAllergens.add(drink_2);

        expectedAllergensVgt.add(starter_1);
        expectedAllergensVgt.add(main_2);
        expectedAllergensVgt.add(drink_1);
        expectedAllergensVgt.add(drink_2);

        Assert.assertArrayEquals(expectedAll.toArray(), c.getFilteredList(true, true, true, "").toArray());
        Assert.assertArrayEquals(expectedCel.toArray(), c.getFilteredList(false, false, true, "").toArray());
        Assert.assertArrayEquals(expectedVeg.toArray(), c.getFilteredList(true, false, false, "").toArray());
        Assert.assertArrayEquals(expectedVgt.toArray(), c.getFilteredList(false, true, false, "").toArray());
        Assert.assertArrayEquals(expectedAllergens.toArray(), c.getFilteredList(false, false, false, "BG").toArray());
        Assert.assertArrayEquals(expectedAllergensVgt.toArray(), c.getFilteredList(false, true, false, "BG").toArray());
    }

    /**
     * Method for testing method getElementByCode
     */
    @Test
    public void getElementByCodeTest(){

        Assert.assertEquals(first_1 ,c.getElementByCode("P01"));
        Assert.assertEquals(starter_1 ,c.getElementByCode("A01"));
        Assert.assertEquals(dessert_1 ,c.getElementByCode("D01"));
        Assert.assertEquals(main_2 ,c.getElementByCode("S02"));
        Assert.assertEquals(drink_2 ,c.getElementByCode("B02"));
    }

    /**
     * Method for testing method addAllergens
     */
    @Test
    public void addAllergensTest(){

        ArrayList<Allergen> expected = new ArrayList<>();

        expected.add(a1);
        expected.add(a2);

        c.addAllergen(a1);
        c.addAllergen(a2);

        Assert.assertArrayEquals(expected.toArray(), c.getAllergens().toArray());
    }
}
