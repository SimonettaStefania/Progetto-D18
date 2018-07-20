package restaurant;
import java.util.ArrayList;
import menu.*;

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

    private void addDish(MenuElement elem) {
        if (!dishesList.contains(elem))
            dishesList.add(elem);
    }

    private void addDrink(MenuElement elem) {
        if (!drinksList.contains(elem))
            drinksList.add(elem);
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

    // NOTA: ho dovuto modificarlo perchè probabilmente non era thread-safe avere un attributo del catalogue
    //      utilizzato da tutti, essendo un oggetto locale non dovrebbero esserci problemi ora (credo)
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

    public MenuElement getElementByCode(String id) {
        for (MenuElement element : dishesList)
            if (element.getElementCode().equals(id))
                return element;

        for (MenuElement element : drinksList)
            if (element.getElementCode().equals(id))
                return element;

        return null;
    }

    // TODO: add to UML
    public ArrayList<Allergen> getAllergens() {
        return allergensList;
    }

    public void addAllergen(Allergen item) {
        if (!allergensList.contains(item))
            allergensList.add(item);
    }
}
