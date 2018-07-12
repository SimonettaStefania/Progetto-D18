package tester;

import menu.*;

import java.util.Collections;


public class TestMenu {
    public static void main(String[] args) {

    Menu m = new Menu("menuProva", 10);

    MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER, 4.00, false, false, false);
    MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE,10.0, false, false, false);
    MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE,10.00, false, false, false);
    MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
    MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
    MenuElement drink_2 = new MenuElement("Caffè", "DR002", DishType.DRINK, 1.0, false, false, false);

    // Add elements to menuProva
    m.addElement(drink_2);
    m.addElement(first_1);
    m.addElement(main_1);
    m.addElement(dessert_1);
    m.addElement(starter_1);
    m.addElement(drink_1);

    // Print all elements in the menu
    m.sortMenuElements();  // method to be called in the UI

    System.out.println(m.toString());

    // Remove some elements in the menu
    m.removeElement(main_1);
    m.removeElement(dessert_1);

    System.out.println(m.toString());

    // set budget and guests's number
    m.setNMenuGuests(20);

    System.out.println(m.toString());

    // menu creation simulation
    m.setNMenuGuests(50);

    m.addElement(drink_2);
    m.addElement(first_1);
    m.addElement(main_1);
    m.addElement(dessert_1);
    m.addElement(starter_1);
    m.addElement(drink_1);
    m.addElement(drink_2);
    m.addElement(first_1);
    m.addElement(main_1);
    m.addElement(dessert_1);
    m.addElement(starter_1);
    m.addElement(drink_1);

    m.setNMenuGuests(19);

    m.removeElement(drink_1);
    m.removeElement(starter_1);
    m.removeElement(first_1);

    m.addElement(starter_1);
    m.sortMenuElements();
    System.out.println(m.toString());
    }
}
