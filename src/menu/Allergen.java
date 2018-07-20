package menu;

/**
 * Class made for the definition of an allergen that may be present in a dish.
 */
public class Allergen {
    private String name, allergenCode;

    /**
     * Constructor for an allergen given its name and its code
     * @param name String
     * @param allergenCode String
     */
    public Allergen(String allergenCode, String name) {
        this.name = name;
        this.allergenCode = allergenCode;
    }

    /**
     * Returns the allergen's name
     * @return String
     */
    public String getName() {
        return name;
    }

    // TODO method not used
    /**
     * Returns the allergen's code
     * @return String
     */

    public String getAllergenCode() {
        return allergenCode;
    }

    /**
     * Generates a description for the allergen
     * @return String
     */

    @Override
    public String toString() {
        return allergenCode + " (" + name + ")";
    }
}
