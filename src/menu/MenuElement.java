package menu;

import java.util.ArrayList;

public class MenuElement {

    private String name, elementCode;
    private DishType type;
    private double price;
    private ArrayList<Allergen> allergenList;
    private ArrayList<String> ingredientsList ;

    public MenuElement(String name, String elementCode, DishType type, double price) {
        this.name = name;
        this.elementCode = elementCode;
        this.type = type;
        this.price = price;
        allergenList = new ArrayList<>();
        ingredientsList = new ArrayList<>() ;
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

    public void addIngredient (String name ){

        if ( ! ingredientsList.contains(name) )
            ingredientsList.add(name) ;

    }

    public boolean containsAllergen(Allergen a){

         return allergenList.contains(a);

    }

    public String toString(){

        String tmp = name + "\t" ;

        for ( int i=0 ; i< ingredientsList.size() ; i++)
            tmp += ingredientsList.get(i) + ", " ;

        tmp += "\t" + price + "€" ;

        return tmp ;

    }

    public String showAllergenes(){

        String tmp = name + "\t" ;

        for ( int i=0 ; i< allergenList.size() ; i++)
            tmp += allergenList.get(i) + ", " ;

        return tmp;
    }


}
