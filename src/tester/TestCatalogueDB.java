package tester;

import menu.MenuElement;
import restaurant.Catalogue;
import restaurant.Restaurant;
import services.DbReader;
import services.Query;

public class TestCatalogueDB {

    public static void main(String[] args) throws InterruptedException {
        Catalogue cat =new Catalogue();
        DbReader dbr = new DbReader("esame","123456");
        Thread readerThread=new Thread(dbr);

        dbr.setQuery((Query.SELECT_ALL_DISHES));

        readerThread.start();
        readerThread.join();

        for(MenuElement elem:dbr.getDishesList()){
            cat.addElement(elem);
        }


        Restaurant rest=new Restaurant("Da Nino",150,cat);
        System.out.println(rest.showCatalogue());


    }
}