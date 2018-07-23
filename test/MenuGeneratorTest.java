import menu.DishType;
import menu.Menu;
import menu.MenuElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restaurant.Reservation;
import restaurant.Restaurant;

import java.util.Date;

public class MenuGeneratorTest{

    Reservation r1 = new Reservation("RES PROVA 1", 30, new Date(), "nomeCognome", "nome@gmail.it");

    @Before
    public void inizialize(){

        r1.generateOptimizedMenus(110, 20);
    }

    @Test
    // This method controls if the prizes of dishes are consistent with optimization
    public void optimizedMenuTest() {
        double b= 110; //budget
        int i=0;

        double bdS[] = {0.25*b, 0.35*b, 0.2*b, 0.2*b};
        double bdF[] = {0.3*b, 0.25*b, 0.4*b, 0.25*b};
        double bdM[] = {0.3*b, 0.25*b, 0.25*b, 0.4*b};

        for(Menu m: r1.getOptimizedMenu()) {

            for (MenuElement el : m.getMenuElementsList()) {

                if (el.getType() == DishType.STARTER) {
                    Assert.assertTrue(bdS[i] >= el.getPrice());
                }
                if (el.getType() == DishType.FIRST_COURSE) {
                    Assert.assertTrue(bdF[i] >= el.getPrice());
                }
                if (el.getType() == DishType.MAIN_COURSE) {
                    Assert.assertTrue(bdM[i] >= el.getPrice());
                }
            }

            i++;
        }

    }



}
