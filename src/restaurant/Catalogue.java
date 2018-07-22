package restaurant;

import menu.*;
import java.util.ArrayList;

public class Catalogue {
    private ArrayList<MenuElement> dishesList;
    private ArrayList<Allergen> allergensList;

    public Catalogue() {
        dishesList = new ArrayList<>();
        allergensList = new ArrayList<>();
    }

    public ArrayList<MenuElement> getDishes() {
        return dishesList;
    }

    // TODO: add to UML
    public ArrayList<Allergen> getAllergens() {
        return allergensList;
    }

    // TODO: add to UML
    public void addAllergen(Allergen item) {
        if (!allergensList.contains(item))
            allergensList.add(item);
    }

    public void addElement(MenuElement elem) {
        if (!dishesList.contains(elem))
            dishesList.add(elem);
    }

    public void removeElement (MenuElement elem) {
        dishesList.remove(elem);
    }

    public ArrayList<MenuElement> getFilteredList(boolean vegan, boolean vegetarian, boolean celiac, String allergens) {
        ArrayList<MenuElement> filterSelection = new ArrayList<>();

        for (MenuElement element : dishesList)
            if (element.respectsFilters(vegan, vegetarian, celiac))
                if (element.respectsAllergens(allergens))
                    filterSelection.add(element);

        return filterSelection;
    }

    public MenuElement getElementByCode(String id) {
        for (MenuElement element : dishesList)
            if (element.getElementCode().equals(id))
                return element;

        return null;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("CATALOGO DI PORTATE : \n");
        for (MenuElement element : dishesList)
            tmp.append(element.toString()).append("\n");

        return tmp.toString();
    }
}
