package menu;

public class Allergen {
    private String name, allergenCode;

    public Allergen(String name, String allergenCode) {
        this.name = name;
        this.allergenCode = allergenCode;
    }

    public String getName() {
        return name;
    }

    public String getAllergenCode() {
        return allergenCode;
    }

    @Override
    public String toString() {
        return name + " ( " + allergenCode + " )";
    }
}
