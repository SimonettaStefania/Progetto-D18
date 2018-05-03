package tester;

import menu.*;

public class TestFlags {
    public static void main(String[] args) {
        MenuElement starter = new MenuElement("Bruschetta","S001", DishType.STARTER,
                                              4.00, true, true, false);
        MenuElement first = new MenuElement("Spaghetti alla carbonara", "P001",
                                             DishType.FIRST_COURSE,10.0, false, false, false);


        // Si potrebbe passare come argomento un vettore di boolean per semplificare il codice.
        //  boolean[] filters = {true, false, false};
        System.out.println("[VEGAN TEST]");
        System.out.println(starter.respectsFilters(true, false, false));
        System.out.println(first.respectsFilters(true, false, false));
        System.out.println();

        System.out.println("[VEGGIE TEST]");
        System.out.println(starter.respectsFilters(false, true, false));
        System.out.println(first.respectsFilters(false, true, false));
        System.out.println();

        System.out.println("[CELIAC TEST]");
        System.out.println(starter.respectsFilters(false, false, true));
        System.out.println(first.respectsFilters(false, false, true));
        System.out.println();

        System.out.println("[VEG+VGT TEST]");
        System.out.println(starter.respectsFilters(true, true, false));
        System.out.println(first.respectsFilters(true, true, false));
    }
}
