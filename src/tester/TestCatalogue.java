package tester;

import menu.DishType;
import menu.MenuElement;
import restaurant.Restaurant;

import java.util.Collections;

public class TestCatalogue {

    public static void main(String[] args) throws InterruptedException {

        checkCatalogue();

    }

    public static void checkCatalogue() throws InterruptedException {

        Restaurant r = new Restaurant("Ristorante Italiano", 100,false);

        MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER,
                                                4.00, false, false, false);
        MenuElement starter_2 = new MenuElement("Aragosta","S002", DishType.STARTER,
                6.00, false, false, false);
        MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001",
                                                    DishType.FIRST_COURSE,10.0, false, false, false);
        MenuElement first_2 = new MenuElement("Spaghetti allo scoglio", "P002",
                DishType.FIRST_COURSE,11.0, false, false, false);
        MenuElement main_1 = new MenuElement("Pepata di cozze", "M001",
                                                    DishType.MAIN_COURSE,10.00, false, false, false);
        MenuElement main_2 = new MenuElement("cozze", "M002",
                DishType.MAIN_COURSE,8.00, false, false, false);
        MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001",
                                                    DishType.DESSERT, 4.00, false, false, false);
        MenuElement dessert_2 = new MenuElement("Tiramisù", "D002",
                DishType.DESSERT, 2.00, false, false, false);
        MenuElement drink_1 = new MenuElement("Acqua", "DR001",
                                                    DishType.DRINK, 1.0, false, false, false);
        MenuElement drink_2 = new MenuElement("Caffè", "DR002",
                                                    DishType.DRINK, 1.0, false, false, false);

        r.addToCatalogue(dessert_1);
        r.addToCatalogue(dessert_2);
        r.addToCatalogue(drink_1);
        r.addToCatalogue(drink_2);
        r.addToCatalogue(starter_1);
        r.addToCatalogue(starter_2);
        r.addToCatalogue(first_1);
        r.addToCatalogue(first_2);
        r.addToCatalogue(main_1);
        r.addToCatalogue(main_2);


        System.out.println(" -------------STAMPA DEL CATALOGO DEI PIATTI :----------");
        System.out.println(r.showCatalogue());

        r.removeFromCatalogue(drink_2);

        System.out.println(" -------------STAMPA DEL CATALOGO DEI PIATTI DOPO AVER TOLTO IL CAFFE':----------");

        System.out.println(r.showCatalogue());

        Collections.sort(r.getDishesCatalogue().getDishes(), MenuElement.priceComparator);
        Collections.sort(r.getDishesCatalogue().getDishes(), MenuElement.typeComparator);

        System.out.println(r.showCatalogue());

    }

}
