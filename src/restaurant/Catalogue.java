package restaurant;

import menu.*;
import java.util.ArrayList;

/**
 * Class containing all the available dishes and appropriate methods to handle them.
 * It is described by:
 * - a list of dishes (MenuElement objects)
 * - a list of all the possible allergens (useful for item filtering)
 */
public class Catalogue {
    private ArrayList<MenuElement> dishesList;
    private ArrayList<Allergen> allergensList;

    /**
     * Class constructor, initializes the ArrayList objects
      */
    public Catalogue() {
        dishesList = new ArrayList<>();
        allergensList = new ArrayList<>();
    }

    /**
     * Getter that allows access to the stored dishes
     * @return list of elements in the catalogue
     */
    public ArrayList<MenuElement> getDishes() {
        return dishesList;
    }

    /**
     * Getter that allows access to the known allergens
     * @return list of allergens in the catalogue
     */
    public ArrayList<Allergen> getAllergens() {
        return allergensList;
    }

    /**
     * Method to insert elements in the allergen list
     * @param item Allergen to be stored
     */
    public void addAllergen(Allergen item) {
        if (!allergensList.contains(item))
            allergensList.add(item);
    }

    /**
     * Method to insert elements in the dishes list
     * @param elem MenuElement to be stored
     */
    public void addElement(MenuElement elem) {
        if (!dishesList.contains(elem))
            dishesList.add(elem);
    }

    /**
     * Allows to filter the dishes in the catalogue
     * @param vegan boolean specifying if only vegan dishes are needed
     * @param vegetarian boolean specifying if only vegetarian dishes are needed
     * @param celiac boolean specifying if only gluten-free dishes are needed
     * @param allergens string containing all the allergens to avoid
     * @return a list of dishes respecting the specified properties
     */
    public ArrayList<MenuElement> getFilteredList(boolean vegan, boolean vegetarian, boolean celiac, String allergens) {
        ArrayList<MenuElement> filterSelection = new ArrayList<>();

        for (MenuElement item : dishesList)
            if (item.respectsFilters(vegan, vegetarian, celiac) && item.respectsAllergens(allergens))
                filterSelection.add(item);

        return filterSelection;
    }

    /**
     * Selects the dish in the list with the specified identifier
     * @param id code representing uniquely a dish
     * @return the MenuElement item with the corresponding code, null otherwise
     */
    public MenuElement getElementByCode(String id) {
        for (MenuElement element : dishesList)
            if (element.getElementCode().equals(id))
                return element;

        return null;
    }

    /**
     * Simple textual representation of the catalogue
     * @return a string containing basic information about the catalogue
     */
    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("Catalogue\n");
        for (MenuElement element : dishesList)
            tmp.append(element.toString()).append("\n");

        tmp.append("Allergens:");
        for (Allergen allergen : allergensList)
            tmp.append(allergen.toString()).append("\n");

        return tmp.toString();
    }
}
