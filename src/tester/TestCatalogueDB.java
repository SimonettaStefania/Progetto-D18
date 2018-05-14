package tester;

import menu.MenuElement;
import restaurant.Catalogue;
import restaurant.Restaurant;
import services.DbReader;
import services.Query;

public class TestCatalogueDB {

    public static void main(String[] args) throws InterruptedException {
        DbReader dbr = new DbReader("root","root");
        Thread readerThread=new Thread(dbr);

        dbr.setQuery((Query.SELECT_ALL_DISHES));

        readerThread.start();
        readerThread.join();

        Restaurant rest=new Restaurant("Da Nino",150);

        for(MenuElement elem:dbr.getDishesList()){          //TODO to be checked
            rest.addToCatalogue(elem);
        }


        System.out.println(rest.showCatalogue());


    }
}