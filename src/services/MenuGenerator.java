package services;
import restaurant.*;
import menu.*;

import java.util.ArrayList;

public class MenuGenerator {
    private int people;
    private double budget;
    private Catalogue catalogue;
    private ArrayList<Menu> generatedMenu;

    public MenuGenerator (double budget, int people) {
        this.budget = budget;
        this.people = people;
        this.catalogue = Restaurant.getRestaurantInstance().getDishesCatalogue();
        this.generatedMenu = new ArrayList<>();

        generate();
    }

    /**
     * Method to generate the optimized menus starting from the budget.
     * It generates four menus, three optimized for the three main categories, the other
     * one balanced on all the dishes type (i.e. one menu is focused on starters, another one in
     * firt courses, the third on main and the last balanced on all)
     *
     */
    public void generate() {
        catalogue.getDishes().sort(MenuElement.priceComparator);
        catalogue.getDishes().sort(MenuElement.typeComparator);

        generatedMenu.add(optimizeBudget(0.4,0.25,0.25));
        generatedMenu.add(optimizeBudget(0.25,0.4,0.25));
        generatedMenu.add(optimizeBudget(0.25,0.25,0.4));
        generatedMenu.add(optimizeBudget(0.3,0.3,0.3));
    }

    /**
     * Method to be called when is necessary to set the budget to create
     * the optimized menus
     *
     * @param starterQuote set the total quote for the starter section
     * @param firstQuote set the total quote for the first section
     * @param mainQuote set the total quote for the main section
     *
     * @return the optimized menu every time is called
     */
    private Menu optimizeBudget(double starterQuote, double firstQuote, double mainQuote){
        double starterBudget = (budget*starterQuote);
        double firstBudget = (budget*firstQuote);
        double mainBudget = (budget*mainQuote);
        double dessertBudget = (budget*0.1);

        Menu optimizedMenu = new Menu("", people);
        ArrayList<MenuElement> dishesList = optimizedMenu.getMenuElementsList();
        dishesList.addAll(findDishes(DishType.STARTER, starterBudget));
        dishesList.addAll(findDishes(DishType.FIRST_COURSE, firstBudget));
        dishesList.addAll(findDishes(DishType.MAIN_COURSE, mainBudget));
        dishesList.addAll(findDishes(DishType.DESSERT, dessertBudget));

        if (starterBudget == firstBudget && firstBudget == mainBudget)
            optimizedMenu.setName("JUST OPTIMIZED BUDGET");
        else if (Math.max(starterBudget, Math.max(firstBudget,mainBudget)) == starterBudget)
            optimizedMenu.setName("OPTIMIZED BUDGET ON STARTERS");
        else if (Math.max(firstBudget,mainBudget) == firstBudget)
            optimizedMenu.setName("OPTIMIZED BUDGET ON FIRST COURSES");
        else optimizedMenu.setName("OPTIMIZED BUDGET ON MAIN COURSES");

        return optimizedMenu;
    }

    /**
     * Method to get best dish starting from a budget
     *
     * @param type set the type of dish to be picked
     * @param budget set the budget for the dish to be picked
     *
     * @return the selected dish, optimized for the budget
     */
    private ArrayList<MenuElement> findDishes(DishType type, double budget){
        MenuElement tmpElem = null;
        boolean first = true;

        ArrayList<MenuElement> tmp = new ArrayList<>();
        for (MenuElement element : catalogue.getDishes()) {
            if (first) {
                tmpElem = element;
                first = false;
            } else if (tmpElem.getType()==type && element.getType()==type && (tmpElem.getPrice()+element.getPrice()<=budget)) {
                tmp.add(tmpElem);
                tmp.add(element);
                break;
            } else tmpElem = element;
        }

        if (tmp.isEmpty()) {
            for (MenuElement element : catalogue.getDishes()) {
                if (element.getType()==type && element.getPrice()<=budget) {
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
