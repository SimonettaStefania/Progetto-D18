package menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MenuElement  {
    private String name, elementCode;
    private DishType type;
    private double price;
    private Flags flags;
    private ArrayList<Allergen> allergenList;
    private ArrayList<String> ingredientsList;

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

    public String getName() {
        return name;
    }

    public String getElementCode() {
        return elementCode;
    }

    public double getPrice() {
        return price;
    }

    public DishType getType() {
        return type;
    }

    public boolean respectsFilters(boolean veg, boolean vgt, boolean cel){
        return flags.check(veg, vgt, cel);
    }

    public void addIngredient(String name) {
        if (!ingredientsList.contains(name))
            ingredientsList.add(name);
    }

    public void addAllergen(Allergen a) {
        if (!allergenList.contains(a))
            allergenList.add(a);
    }

    public void removeAllergen(Allergen a) {
        if (allergenList.contains(a))
            allergenList.remove(a);
    }

    public boolean containsAllergen(Allergen a) {
        return allergenList.contains(a);

    }

    public String showFilters(){
        String tmp="";
        HashMap<String,Boolean> filters = flags.getFilters();

        for ( String element : filters.keySet()){

            if ( filters.get(element) )
                tmp += element + " ; ";
        }
        return tmp;
    }

    public String showDetails() {
        String tmp = "";
        for (String element : ingredientsList)
            tmp += element + "  ;  ";
        return tmp;

    }

    public String toString() {
        return name + "\t" + price + " €" + "\t" + type+"\n";
    }

    public String showAllergenes() {
        String tmp = "";
        for (Allergen element : allergenList)
            tmp += element + "  ;  ";
        return tmp;
    }

    public static Comparator <MenuElement> typeComparator = new Comparator<MenuElement>() {
        @Override
        public int compare(MenuElement o1, MenuElement o2) {
            return o1.getType().compareTo(o2.getType());
        }
    };

    public static Comparator <MenuElement> priceComparator = new Comparator<MenuElement>() {
        @Override
        public int compare(MenuElement o1, MenuElement o2) {
            Double p1 = o1.getPrice();
            Double p2 = o2.getPrice();

            return -p1.compareTo(p2);

        }
    };

}
