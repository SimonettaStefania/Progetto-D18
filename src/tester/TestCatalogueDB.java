package tester;

import menu.MenuElement;
import restaurant.Restaurant;
import services.DbReader;
import services.Query;

import java.util.Collections;

public class TestCatalogueDB {
    public static void main(String[] args) throws InterruptedException {
        DbReader dbr = new DbReader("esame","123456");
        Thread dishesReaderThread=new Thread(dbr);


        dbr.setQuery((Query.SELECT_ALL_DISHES));

        dishesReaderThread.start();
        dishesReaderThread.join();

        Restaurant rest=new Restaurant("Da Nino",150,false);

        //POPULATING CATALOGUE
        for(MenuElement elem:dbr.getDishesList()){
            rest.addToCatalogue(elem);
        }

        Collections.sort(rest.getDishesCatalogue().getDishes(),MenuElement.priceComparator);
        Collections.sort(rest.getDishesCatalogue().getDishes(),MenuElement.typeComparator);

        System.out.println(rest.showCatalogue());


        System.out.println("\nALLERGENS LIST (FOR EVERY DISH)");
        //SHOWS ALLERGENES FOR EVERY ELEMENT OF THE CATALOGUE
        for (MenuElement elem: rest.getDishesCatalogue().getDishes()){
            System.out.print(elem.showAllergenes());
        }

        //SHOWS INGREDIENTS FOR EVERY ELEMENT OF THE CATALOGUE
        System.out.println("\n \nDISHES DETAILS");
        for (MenuElement elem: rest.getDishesCatalogue().getDishes()){
            System.out.print(elem.showDetails());
        }



    }
}
