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
        double starterBudget = (budget*starterQuote);
        double firstBudget = (budget*firstQuote);
        double mainBudget = (budget*mainQuote);
        double dessertBudget = (budget*0.1);

        Menu optimizedMenu = new Menu();
        optimizedMenu.getMenuElementsList().addAll(findDishes(DishType.STARTER,starterBudget));
        optimizedMenu.getMenuElementsList().addAll(findDishes(DishType.FIRST_COURSE,firstBudget));
        optimizedMenu.getMenuElementsList().addAll(findDishes(DishType.MAIN_COURSE,mainBudget));
        optimizedMenu.getMenuElementsList().addAll(findDishes(DishType.DESSERT,dessertBudget));
        optimizedMenu.calculateMenuCost();

        if(starterBudget==firstBudget && firstBudget==mainBudget){
            optimizedMenu.setName("JUST OPTIMIZED BUDGET");
        }else{
            if(Math.max(starterBudget,Math.max(firstBudget,mainBudget))==starterBudget) {
                optimizedMenu.setName("OPTIMIZED BUDGET ON STARTERS");
            }else {
                if(Math.max(firstBudget,mainBudget)==firstBudget){
                    optimizedMenu.setName("OPTIMIZED BUDGET ON FIRST COURSES");
                }else{
                    optimizedMenu.setName("OPTIMIZED BUDGET ON MAIN COURSES");
                }
            }
        }



        return optimizedMenu;

    }


    private ArrayList<MenuElement> findDishes(DishType type, double budget){
        MenuElement tmpElem=null;
        boolean first=true;

        ArrayList<MenuElement> tmp = new ArrayList<>();
        for ( MenuElement element : catalogue.getDishes() ) {
            if(first)
            {
                tmpElem=element;
                first=false;
            }else{
                if(tmpElem.getType()==type && element.getType()==type && (tmpElem.getPrice()+element.getPrice()<=budget)){
                    tmp.add(tmpElem);
                    tmp.add(element);
                    break;
                }else{
                    tmpElem=element;
                }
            }
        }
        if(tmp.isEmpty()){
            for ( MenuElement element : catalogue.getDishes() ) {
                if(element.getType()==type && element.getPrice()<=budget){
                    tmp.add(element);
                    break;
                }
            }
        }

        return tmp ;
    }

    public ArrayList<Menu> getGeneratedMenu() {
        return generatedMenu;
    }
}
