package menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Class that represents a single dish in the menu . Each dish is described by :
 *  - a name and a code
 *  - a type ( starter, first course, main course , dessert or drink )
 *  - a price
 *  - flags setted to true if the dish is vegan or vegetarian or gluten free
 *  - a list of allergen and a list of ingredients
 */
public class MenuElement  {
    private String name, elementCode;
    private DishType type;
    private double price;
    private Flags flags;
    private ArrayList<Allergen> allergenList;
    private ArrayList<String> ingredientsList;

    /**
     * Constructor with parameters
     * @param name tbe name of the dish
     * @param elementCode the code of the dish
     * @param type the type of dish
     * @param price the price of the dish
     * @param veg true if the dish is vegan
     * @param vgt true if the dish is vegetarian
     * @param cel true if the dish is gluten free
     */

    public MenuElement(String name, String elementCode, DishType type,
                       double price, boolean veg, boolean vgt, boolean cel) {
        this.name = name;
        this.elementCode = elementCode;
        this.type = type;
        this.price = price;
        this.flags = new Flags(veg, vgt, cel);

        allergenList = new ArrayList<>();
        ingredientsList = new ArrayList<>();
    }

    /**
     * @return the name of the dish
     */
    public String getName() {
        return name;
    }

    /**
     * @return the code of the dish
     */
    public String getElementCode() {
        return elementCode;
    }

    /**
     * @return the price of the dish
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the type of the dish
     */
    public DishType getType() {
        return type;
    }

    /**
     * Checks whether the dish respects the filters or not
     * @param veg vegan filter
     * @param vgt vegetarian filter
     * @param cel celiac filter ( gluten free dish )
     * @return true if the filters are respected , false otherwise
     */
    public boolean respectsFilters(boolean veg, boolean vgt, boolean cel){
        return flags.check(veg, vgt, cel);
    }

    /**
     * Adds an ingredient to the ingredient's list
     * @param name the ingredient to be added
     */

    public void addIngredient(String name) {
        if (!ingredientsList.contains(name))
            ingredientsList.add(name);
    }

    /**
     * Adds an allergen to the allergen's list
     * @param a the allergen to be added
     */
    public void addAllergen(Allergen a) {
        if (!allergenList.contains(a))
            allergenList.add(a);
    }

    /**
     * Removes an allergen to the allergen's list
     * @param a the allergen to be removed
     */

    public void removeAllergen(Allergen a) {
        allergenList.remove(a);
    }

    /**
     * Checks whether the element contains the specified allergens or not
     * @param filter string of allergen's codes to be checked
     * @return true if none of the specified allergens is present, false otherwise
     */
    public boolean respectsAllergens(String filter) {
        for (Allergen allergen : allergenList)
            if (filter.contains(allergen.getAllergenCode()))
                return false;
        return true;
    }

    // TODO: add doc and improve
    public String showFilters(){
        String tmp="";
        HashMap<String,Boolean> filters = flags.getFilters();

        for ( String element : filters.keySet()){

            if ( filters.get(element) )
                tmp += element + " ; ";
        }
        return tmp;
    }

    /**
     * Creates a description of the dish's ingredients
     * @return string description
     */
    public String showDetails() {
        String tmp = "";
        for (String element : ingredientsList)
            tmp += element + "  ;  ";
        return tmp;

    }

    /**
     * Creates a description of the dish ( name, price , type of dish )
     * @return string description
     */
    public String toString() {
        return name + "\t" + price + " €" + "\t" + type+"\n";
    }

    /**
     * Creates a description of the dish's allergens
     * @return string description
     */
    public String showAllergenes() {
        String tmp = "";
        for (Allergen element : allergenList)
            tmp += element + "  ;  ";
        return tmp;
    }

    /**
     * Comparator for the type of dishes. Its method compare( ) takes two MenuElements e1, e2 as parameters and returns
     * 0 if e1 and e2 are dishes of the same type
     */
    public static Comparator <MenuElement> typeComparator = new Comparator<MenuElement>() {
        @Override
        public int compare(MenuElement o1, MenuElement o2) {
            return o1.getType().compareTo(o2.getType());
        }
    };

    /**
     * Comparator for the type of dishes. Its method compare( ) takes two MenuElements e1, e2 as parameters and returns
     * 0 if e1 and e2  have the same price
     * positive integer if the price of e1 is lower than the one of e2
     * negative integer if the price of e1 is higher than the one of e2
     *
     */
    public static Comparator <MenuElement> priceComparator = (o1, o2) -> {
        Double p1 = o1.getPrice();
        Double p2 = o2.getPrice();

        return -p1.compareTo(p2);
    };

}
