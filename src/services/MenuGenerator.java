package services;
import restaurant.*;
import menu.*;

import java.util.ArrayList;
import java.util.Collections;

public class MenuGenerator {

    private double budget;
    private Catalogue catalogue;
    private ArrayList<Menu> generatedMenu;

    public MenuGenerator (double budget, Catalogue catalogue) {
        this.budget = budget;
        this.catalogue = catalogue;
        generatedMenu = new ArrayList<>();
    }


    public void generate () {
        Collections.sort(catalogue.getDishes(), MenuElement.priceComparator);
        Collections.sort(catalogue.getDishes(), MenuElement.typeComparator);

        generatedMenu.add(optimizeBudget(0.4,0.25,0.25));
        generatedMenu.add(optimizeBudget(0.25,0.4,0.25));
        generatedMenu.add(optimizeBudget(0.25,0.25,0.4));
        generatedMenu.add(optimizeBudget(0.3,0.3,0.3));

    }


    private Menu optimizeBudget(double starterQuote, double firstQuote, double mainQuote){
        double starterBudget = (budget*starterQuote)/2;
        double firstBudget = (budget*firstQuote)/2;
        double mainBudget = (budget*mainQuote)/2;
        double dessertBudget = (budget*0.01)/2;

        Menu optimizedMenu = new Menu();
        optimizedMenu.getMenuElementsList().addAll(findDishes(DishType.STARTER,starterBudget));
        optimizedMenu.getMenuElementsList().addAll(findDishes(DishType.FIRST_COURSE,firstBudget));
        optimizedMenu.getMenuElementsList().addAll(findDishes(DishType.MAIN_COURSE,mainBudget));
        optimizedMenu.getMenuElementsList().addAll(findDishes(DishType.DESSERT,dessertBudget));

        return optimizedMenu;

    }


    private ArrayList<MenuElement> findDishes(DishType type, double budget){

        int n = 0 ;
        ArrayList<MenuElement> tmp = new ArrayList<>();
        for ( MenuElement element : catalogue.getDishes() ) {
            if (n == 2)
                break;
            if (element.getType() == type && element.getPrice() <= budget) {
                tmp.add(element);
                n++;
            }
        }

        return tmp ;
    }




}
