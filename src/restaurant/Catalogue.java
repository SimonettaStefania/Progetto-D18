package restaurant;
import menu.*;
import java.util.ArrayList;

public class Catalogue {
    private ArrayList<MenuElement> dishesList;
    private ArrayList<MenuElement> drinksList;
    private ArrayList<Allergen> allergensList;

    public Catalogue() {
        dishesList = new ArrayList<>();
        drinksList = new ArrayList<>();
        allergensList = new ArrayList<>();
    }

    public ArrayList<MenuElement> getDishes() {
        return dishesList;
    }

    public ArrayList<MenuElement> getDrinks() {
        return drinksList;
    }

    // TODO: provvisorio
    public ArrayList<MenuElement> getCompleteList() {
        ArrayList<MenuElement> completeList = new ArrayList<>(dishesList);
        completeList.addAll(drinksList);
        return completeList;
    }

    // TODO: add to UML
    public ArrayList<Allergen> getAllergens() {
        return allergensList;
    }

    private void addDish(MenuElement elem) {
        if (!dishesList.contains(elem))
            dishesList.add(elem);
    }

    private void addDrink(MenuElement elem) {
        if (!drinksList.contains(elem))
            drinksList.add(elem);
    }

    // TODO: add to UML
    void addAllergen(Allergen item) {
        if (!allergensList.contains(item))
            allergensList.add(item);
    }

    // TODO: update UML (made package-private to improve security)
    void addElement(MenuElement elem) {
        if (elem.getType() != DishType.DRINK)
            addDish(elem);
        else addDrink(elem);
    }

    // TODO: update UML (made package-private to improve security)
    void removeElement (MenuElement elem) {
        if (dishesList.contains(elem))
            dishesList.remove(elem);
        else drinksList.remove(elem);
    }

    public ArrayList<MenuElement> getFilteredList(boolean vegan, boolean vegetarian, boolean celiac, String allergens) {
        ArrayList<MenuElement> filterSelection = new ArrayList<>();

        for (MenuElement element : dishesList)
            if (element.respectsFilters(vegan, vegetarian, celiac))
                if (element.respectsAllergens(allergens))
                    filterSelection.add(element);

        for (MenuElement element : drinksList)
            if (element.respectsFilters(vegan, vegetarian, celiac))
                if (element.respectsAllergens(allergens))
                    filterSelection.add(element);

        return filterSelection;
    }

    MenuElement getElementByCode(String id) {
        for (MenuElement element : dishesList)
            if (element.getElementCode().equals(id))
                return element;

        for (MenuElement element : drinksList)
            if (element.getElementCode().equals(id))
                return element;

        return null;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("CATALOGO DI PORTATE : \nPIATTI : \n");
        for (MenuElement element : dishesList)
            tmp.append(element.toString()).append("\n");

        tmp.append("\nBEVANDE :\n");
        for (MenuElement element : drinksList)
            tmp.append(element.toString()).append("\n");

        return tmp.toString();
    }
}
