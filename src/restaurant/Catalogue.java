package restaurant;
import java.util.ArrayList;
import menu.*;

public class Catalogue {
    private ArrayList<MenuElement> dishesList;
    private ArrayList<MenuElement> drinksList;

    public Catalogue() {
        dishesList = new ArrayList<>();
        drinksList = new ArrayList<>();
    }

    public ArrayList<MenuElement> getDishes() {
        return dishesList;
    }
    public ArrayList<MenuElement> getDrinks() {
        return drinksList;
    }

    public void addDish(MenuElement elem) {
        if (elem.getType() != DishType.DRINK) {
            dishesList.add(elem);
        }
    }

    public void addDrink(MenuElement elem) {
        if (elem.getType() == DishType.DRINK) {
            drinksList.add(elem);
        }
    }
}
