package example.ex1.before;

class Pizza {
    private final String size;
    private final String dough;
    private final String sauce;
    private final String cheese;
    private final boolean hasPepperoni;
    private final boolean hasMushrooms;
    private final boolean hasOlives;
    private final boolean hasExtraCheese;
    private final boolean hasSpicySauce;

    public Pizza(String size, String dough, String sauce, String cheese,
                 boolean hasPepperoni, boolean hasMushrooms, boolean hasOlives,
                 boolean hasExtraCheese, boolean hasSpicySauce) {
        this.size = size;
        this.dough = dough;
        this.sauce = sauce;
        this.cheese = cheese;
        this.hasPepperoni = hasPepperoni;
        this.hasMushrooms = hasMushrooms;
        this.hasOlives = hasOlives;
        this.hasExtraCheese = hasExtraCheese;
        this.hasSpicySauce = hasSpicySauce;
    }

    public Pizza(String size, String dough) {
        this(size, dough, "Tomato", "Mozzarella", false, false, false, false, false);
    }

    public Pizza(String size, String dough, String sauce) {
        this(size, dough, sauce, "Mozzarella", false, false, false, false, false);
    }

    public Pizza(String size, String dough, String sauce, String cheese) {
        this(size, dough, sauce, cheese, false, false, false, false, false);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", dough='" + dough + '\'' +
                ", sauce='" + sauce + '\'' +
                ", cheese='" + cheese + '\'' +
                ", hasPepperoni=" + hasPepperoni +
                ", hasMushrooms=" + hasMushrooms +
                ", hasOlives=" + hasOlives +
                ", hasExtraCheese=" + hasExtraCheese +
                ", hasSpicySauce=" + hasSpicySauce +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Pizza pizza1 = new Pizza(
                "Large",
                "Thin Crust",
                "Tomato",
                "Mozzarella",
                true,
                false,
                true,
                false,
                true
        );
        System.out.println(pizza1);

        Pizza pizza2 = new Pizza(
                "Medium",
                "Thick Crust",
                "BBQ",
                "Cheddar",
                false,
                true,
                false,
                true,
                false
        );
        System.out.println(pizza2);

        Pizza pizza3 = new Pizza("Small", "Regular");
        System.out.println(pizza3);

        Pizza pizza4 = new Pizza(
                "Large",
                "Thin Crust",
                "Tomato",
                "Mozzarella",
                false,
                false,
                false,
                true,
                false
        );
        System.out.println(pizza4);
    }
}