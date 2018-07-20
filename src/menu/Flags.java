package menu;
import java.util.HashMap;

/**
 * Class made to memorize the type of filters that can be selected during the creation of a customized menu.
 */
public class Flags {
    private boolean vegan, veggie, celiac;

    /**
     * Constructor with boolean values for each category ( vegan , vegetarian , celiac dish ) which are used to describe a dish
     * @param vegan if true , the dish is vegan
     * @param veggie if true, the dish is vegetarian
     * @param celiac if true, the dish is gluten free
     */
    public Flags(boolean vegan, boolean veggie, boolean celiac) {
        this.vegan = vegan;
        this.veggie = veggie;
        this.celiac = celiac;
    }

    /**
     * Method that controls if the dish belongs to the categories given by parameters
     * @param veg vegan
     * @param vgt vegetarian
     * @param cel celiac
     * @return true if the dish passed the control, false otherwise
     */
    public boolean check(boolean veg, boolean vgt, boolean cel) {
        if (veg && !this.vegan) {
            return false;
        }
        if (vgt && !this.veggie) {
            return false;
        }
        if (cel && !this.celiac) {
            return false;
        }
        return true;
    }

    public HashMap<String,Boolean> getFilters(){

        HashMap<String , Boolean> filters = new HashMap<>();
        filters.put("VEGAN", vegan);
        filters.put("VEGETARIAN", veggie);
        filters.put("CELIAC", celiac);

        return filters ;


    }
}
