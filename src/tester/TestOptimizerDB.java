package tester;

import menu.Menu;
import menu.MenuElement;
import restaurant.Restaurant;
import services.DbReader;
import services.MenuGenerator;
import services.Query;

public class TestOptimizerDB {
    public static void main(String[] args) throws InterruptedException {
        DbReader dbr = new DbReader("root","root");
        Thread dishesReaderThread=new Thread(dbr);
        MenuGenerator mg;


        dbr.setQuery((Query.SELECT_ALL_DISHES));

        dishesReaderThread.start();
        dishesReaderThread.join();

        Restaurant rest=new Restaurant("Da Nino",150,false);

        //POPULATING CATALOGUE
        for(MenuElement elem:dbr.getDishesList()){
            rest.addToCatalogue(elem);
        }

        mg=new MenuGenerator(40,rest.getDishesCatalogue());
        mg.generate();

        for(Menu m:mg.getGeneratedMenu()){
            System.out.println(m.toString());
        }
    }

}
