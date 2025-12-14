package prac1.before;

class Toast {
    private boolean cheese;
    private boolean ham;
    private boolean jelly;

    public Toast(boolean cheese, boolean ham, boolean jelly) {
        this.cheese = cheese;
        this.ham = ham;
        this.jelly = jelly;
    }

    public int getPrice() {
        int basePrice = 2000;

        if (cheese) {
            basePrice += 500;
        }
        if (ham) {
            basePrice += 1000;
        }
        if (jelly) {
            basePrice += 300;
        }

        return basePrice;
    }

    public String getRecipe() {
        String recipe = "Bread";

        if (cheese) {
            recipe += " + Cheese";
        }
        if (ham) {
            recipe += " + Ham";
        }
        if (jelly) {
            recipe += " + Jelly";
        }

        return recipe;
    }
}

public class Main {
    public static void main(String[] args) {
        // Order toast with cheese and ham
        Toast myToast = new Toast(true, true, false);

        System.out.println("Menu: " + myToast.getRecipe());
        System.out.println("Price: " + myToast.getPrice());
    }
}