package tester;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import menu.DishType;
import menu.MenuElement;
import restaurant.Restaurant;

public class TestCatalogue {

    public static void main(String[] args) {

        checkCatalogue();

    }

    public static void checkCatalogue(){

        Restaurant r = new Restaurant("Ristorante Italiano", 100);

        MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER,
                                                4.00, false, false, false);
        MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001",
                                                    DishType.FIRST_COURSE,10.0, false, false, false);
        MenuElement main_1 = new MenuElement("Pepata di cozze", "M001",
                                                    DishType.MAIN_COURSE,10.00, false, false, false);
        MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001",
                                                    DishType.DESSERT, 4.00, false, false, false);
        MenuElement drink_1 = new MenuElement("Acqua", "DR001",
                                                    DishType.DRINK, 1.0, false, false, false);
        MenuElement drink_2 = new MenuElement("Caffè", "DR002",
                                                    DishType.DRINK, 1.0, false, false, false);

        r.addToCatalogue(starter_1);
        r.addToCatalogue(first_1);
        r.addToCatalogue(main_1);
        r.addToCatalogue(dessert_1);
        r.addToCatalogue(drink_1);
        r.addToCatalogue(drink_2);

        System.out.println(" -------------STAMPA DEL CATALOGO DEI PIATTI :----------");
        System.out.println(r.showCatalogue());

        r.removeFromCatalogue(drink_2);

        System.out.println(" -------------STAMPA DEL CATALOGO DEI PIATTI DOPO AVER TOLTO IL CAFFE':----------");

        System.out.println(r.showCatalogue());





    }

}
