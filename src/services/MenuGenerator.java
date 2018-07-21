package services;
import restaurant.*;
import menu.*;

import java.util.ArrayList;
import java.util.TreeMap;

public class MenuGenerator {
    private int people;
    private double budget;
    private ArrayList<Menu> generatedMenu;
    private ArrayList<MenuElement> catalogueList;
    private TreeMap<Optimization, double[]> optimizationMap;

    enum Optimization {
        BUDGET, STARTERS, FIRST_COURSES, MAIN_COURSES
    }

    public MenuGenerator (double budget, int people) {
        Restaurant restaurant = Restaurant.getRestaurantInstance();
        Catalogue catalogue = restaurant.getDishesCatalogue();

        this.budget = budget;
        this.people = people;
        this.generatedMenu = new ArrayList<>();
        this.optimizationMap = new TreeMap<>();
        this.catalogueList = catalogue.getCompleteList();

        initOptimizationMap();
        generate();
    }

    // TODO: add to javadoc and UML
    private void initOptimizationMap() {
        double budget[] = {0.35, 0.25, 0.25};
        double starters[] = {0.25, 0.3, 0.3};
        double first[] = {0.2, 0.4, 0.25};
        double main[] = {0.2, 0.25, 0.4};

        optimizationMap.put(Optimization.BUDGET, budget);
        optimizationMap.put(Optimization.STARTERS, starters);
        optimizationMap.put(Optimization.FIRST_COURSES, first);
        optimizationMap.put(Optimization.MAIN_COURSES, main);
    }

    /**
     * Method to generate the optimized menus starting from the budget.
     * It generates four menus, three optimized for the three main categories, the other
     * one balanced on all the dishes type (i.e. one menu is focused on starters, another one in
     * firt courses, the third on main and the last balanced on all)
     *
     */
    private void generate() {
        catalogueList.sort(MenuElement.priceComparator);
        catalogueList.sort(MenuElement.typeComparator);

        for (Optimization opt : optimizationMap.keySet()) {
            double quote[] = optimizationMap.get(opt);
            Menu optimizedMenu = optimizeBudget(pickName(opt), quote[0], quote[1], quote[2]);
            generatedMenu.add(optimizedMenu);
        }
    }

    private String pickName(Optimization opt) {
        String name = "";
        switch (opt) {
            case BUDGET:
                name = "Budget-optimized menu";
                break;
            case STARTERS:
                name = "Budget-optimized menu on starters";
                break;
            case FIRST_COURSES:
                name = "Budget-optimized menu on first courses";
                break;
            case MAIN_COURSES:
                name = "Budget-optimized menu on main courses";
                break;
        }
        return name;
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
    private Menu optimizeBudget(String name, double starterQuote, double firstQuote, double mainQuote) {
        double starterBudget = budget * starterQuote;
        double firstBudget = budget * firstQuote;
        double mainBudget = budget * mainQuote;
        double dessertBudget = budget * 0.1;
        double drinkBudget = budget * 0.05;

        Menu optimizedMenu = new Menu(name, people);
        ArrayList<MenuElement> dishesList = optimizedMenu.getMenuElementsList();
        dishesList.addAll(findDishes(DishType.STARTER, starterBudget));
        dishesList.addAll(findDishes(DishType.FIRST_COURSE, firstBudget));
        dishesList.addAll(findDishes(DishType.MAIN_COURSE, mainBudget));
        dishesList.addAll(findDishes(DishType.DESSERT, dessertBudget));
        dishesList.addAll(findDishes(DishType.DRINK, drinkBudget));

        return optimizedMenu;
    }

    /**
     * Method to get best dishes couple (if possible) starting from a budget
     *
     * @param type set the type of dish to be picked
     * @param budget set the budget for the dishes to be picked
     *
     * @return the selected dishes, optimized for the budget
     */
    private ArrayList<MenuElement> findDishes(DishType type, double budget){
        MenuElement tmpElem = null;

        ArrayList<MenuElement> tmp = new ArrayList<>();
        for (MenuElement element : catalogueList) {
            if (element.getType() == type) {
                if (tmpElem == null)
                    tmpElem = element;
                else if (tmpElem.getPrice() + element.getPrice() <= budget) {
                    tmp.add(tmpElem);
                    tmp.add(element);
                    break;
                } else tmpElem = element;
            }
        }

        if (tmp.isEmpty()) {
            for (MenuElement element : catalogueList) {
                if (element.getType() == type && element.getPrice() <= budget) {
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
