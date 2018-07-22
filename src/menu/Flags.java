package menu;
import java.util.HashMap;

/**
 * Class made to memorize the type of filters that can be selected during the creation of a customized menu.
 */
class Flags {
    private HashMap<Filter,Boolean> filters;

    /**
     * Enumeration of the filters
     */
    enum Filter {
        VEGAN, VEGETARIAN, CELIAC
    }

    /**
     * Constructor with boolean values for each category (vegan, vegetarian, celiac) which are used to describe a dish
     * @param vegan if true, the dish is vegan
     * @param veggie if true, the dish is vegetarian
     * @param celiac if true, the dish is gluten free
     */
    Flags(boolean vegan, boolean veggie, boolean celiac) {
        filters = new HashMap<>();
        filters.put(Filter.VEGAN, vegan);
        filters.put(Filter.VEGETARIAN, veggie);
        filters.put(Filter.CELIAC, celiac);
    }

    /**
     * Method that controls if the dish belongs to the categories given by parameters
     * @param veg vegan
     * @param vgt vegetarian
     * @param cel celiac
     * @return true if the dish passed the control, false otherwise
     */
    boolean check(boolean veg, boolean vgt, boolean cel) {
        return (!veg || filters.get(Filter.VEGAN)) &&
               (!vgt || filters.get(Filter.VEGETARIAN)) &&
               (!cel || filters.get(Filter.CELIAC));
    }

    /**
     * Creates a description of the stored flags
     * @return string describing the filters
     */
    String showFilters(){
        StringBuilder tmp = new StringBuilder();
        for (Filter key : filters.keySet()){
            if (filters.get(key))
                tmp.append(key.toString()).append(" ; ");
        }
        return tmp.toString();
    }
}
