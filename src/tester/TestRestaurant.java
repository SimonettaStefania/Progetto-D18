package tester;


import menu.DishType;
import menu.Menu;
import menu.MenuElement;
import restaurant.Reservation;
import restaurant.Restaurant;

import java.util.Date;

public class TestRestaurant {

    public static void main(String[] args) {
        // create dishes and drink
        MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER, 4.00, false, false, false);
        MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE,10.0, false, false, false);
        MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE,10.00, false, false, false);
        MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
        MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
        MenuElement drink_2 = new MenuElement("Caffè", "DR002", DishType.DRINK, 1.0, false, false, false);

        //create a resturant
        Restaurant ilTitolareDelCorso = new Restaurant("il titolare del corso",50,false);

        System.out.println("BENVENUTI AL RISTORANTE " + ilTitolareDelCorso.getName());

        // Add elements in the catalogue
        ilTitolareDelCorso.addToCatalogue(starter_1);
        ilTitolareDelCorso.addToCatalogue(first_1);
        ilTitolareDelCorso.addToCatalogue(main_1);
        ilTitolareDelCorso.addToCatalogue(dessert_1);
        ilTitolareDelCorso.addToCatalogue(drink_1);
        ilTitolareDelCorso.addToCatalogue(drink_2);

        System.out.println("Test metodi sul catalogo");

        System.out.println(ilTitolareDelCorso.showCatalogue());

        // Add elements in the catalogue
        ilTitolareDelCorso.removeFromCatalogue(drink_1);
        ilTitolareDelCorso.removeFromCatalogue(starter_1);
        ilTitolareDelCorso.removeFromCatalogue(dessert_1);

        System.out.println(ilTitolareDelCorso.showCatalogue());

        Reservation r1 = new Reservation("R1",50,new Date(2018- 5 -13),"Piero Angela","ciao@ciao.com");

        Menu m1 = new Menu("first menu", 10);
        Menu m2 = new Menu("second menu", 12);
        Menu m3 = new Menu("third menu", 8);

        r1.addMenu(m1);
        r1.addMenu(m2);
        r1.addMenu(m3);


        ilTitolareDelCorso.addDish(r1, m1, starter_1);
        ilTitolareDelCorso.addDish(r1, m1, first_1);
        ilTitolareDelCorso.addDish(r1, m1, dessert_1);
        ilTitolareDelCorso.addDish(r1, m1, drink_1);
        ilTitolareDelCorso.addDish(r1, m1, drink_2);

        ilTitolareDelCorso.addDish(r1, m2, drink_1);
        ilTitolareDelCorso.addDish(r1, m2, drink_2);

        ilTitolareDelCorso.addDish(r1, m3, first_1);
        ilTitolareDelCorso.addDish(r1, m3, dessert_1);
        ilTitolareDelCorso.addDish(r1, m3, drink_1);

        System.out.println("I CATALOGHI SONO MORTIIIIIIIIIII TOMMASOOO LI ABBIAMO UCCISI NOI: test metodi sui piatti");

        System.out.println(r1.toString());

        ilTitolareDelCorso.removeDish(r1, m1, starter_1);
        ilTitolareDelCorso.removeDish(r1, m1, drink_1);

        ilTitolareDelCorso.removeDish(r1, m2, drink_2);

        ilTitolareDelCorso.removeDish(r1, m3, first_1);
        ilTitolareDelCorso.removeDish(r1, m3, drink_1);

        System.out.println(r1.toString());

    }
}
