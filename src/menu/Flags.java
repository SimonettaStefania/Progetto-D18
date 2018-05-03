package menu;

public class Flags {
    private boolean vegan, veggie, celiac;

    public Flags(boolean vegan, boolean veggie, boolean celiac) {
        this.vegan = vegan;
        this.veggie = veggie;
        this.celiac = celiac;
    }

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
}
