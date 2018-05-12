package restaurant;
import java.util.ArrayList;
import menu.*;

public class Catalogue {
    private ArrayList<MenuElement> dishesList;
    private ArrayList<MenuElement> drinksList;
    private ArrayList<MenuElement> filterSelection ;

    public Catalogue() {
        dishesList = new ArrayList<>();
        drinksList = new ArrayList<>();
        filterSelection = new ArrayList<>();
    }

    public ArrayList<MenuElement> getDishes() {

        return dishesList;

    }
    public ArrayList<MenuElement> getDrinks() {

        return drinksList;

    }

    private void addDish(MenuElement elem) {

        if ( !dishesList.contains(elem) )
            dishesList.add(elem);

    }

    private void addDrink(MenuElement elem) {

        if ( !drinksList.contains(elem) )
            drinksList.add(elem);

    }

    public void addElement (MenuElement elem){

        if (elem.getType() != DishType.DRINK)
            addDish(elem);

        else if (elem.getType() == DishType.DRINK)
            addDrink(elem);

    }

    public void removeElement (MenuElement elem){

        if ( dishesList.contains(elem))
            dishesList.remove(elem);
        else if ( drinksList.contains(elem))
            drinksList.remove(elem);


    }

    @Override
    public String toString() {
        String tmp = "CATALOGO DI PORTATE : \nPIATTI : \n";

            for ( MenuElement element : dishesList)
                tmp += element.toString() + "\n";

            tmp += "\nBEVANDE :\n";

        for ( MenuElement element : drinksList)
            tmp += element.toString() + "\n";

        return tmp ;

    }

    public void generateFilter(boolean vegan, boolean vegetarian, boolean celiac) {

        filterSelection.clear();

        for (MenuElement element : dishesList){
            if (element.respectsFilters(vegan, vegetarian, celiac))
                filterSelection.add(element);
                        
        }


    }
}
