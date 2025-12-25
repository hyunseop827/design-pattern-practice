package example.ex1.after;

// Product
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

    private Pizza(Builder builder) {
        this.size = builder.size;
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.hasPepperoni = builder.hasPepperoni;
        this.hasMushrooms = builder.hasMushrooms;
        this.hasOlives = builder.hasOlives;
        this.hasExtraCheese = builder.hasExtraCheese;
        this.hasSpicySauce = builder.hasSpicySauce;
    }

    // Builder - inner class
    public static class Builder {
        private final String size;
        private final String dough;
        
        private String sauce = "Tomato";
        private String cheese = "Mozzarella";
        private boolean hasPepperoni = false;
        private boolean hasMushrooms = false;
        private boolean hasOlives = false;
        private boolean hasExtraCheese = false;
        private boolean hasSpicySauce = false;

        public Builder(String size, String dough) {
            this.size = size;
            this.dough = dough;
        }

        public Builder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder hasPepperoni(boolean hasPepperoni) {
            this.hasPepperoni = hasPepperoni;
            return this;
        }

        public Builder hasMushrooms(boolean hasMushrooms) {
            this.hasMushrooms = hasMushrooms;
            return this;
        }

        public Builder hasOlives(boolean hasOlives) {
            this.hasOlives = hasOlives;
            return this;
        }

        public Builder hasExtraCheese(boolean hasExtraCheese) {
            this.hasExtraCheese = hasExtraCheese;
            return this;
        }

        public Builder hasSpicySauce(boolean hasSpicySauce) {
            this.hasSpicySauce = hasSpicySauce;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
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

// Client
public class Main {
    public static void main(String[] args) {
        Pizza pizza1 = new Pizza.Builder("Large", "Thin Crust")
                .sauce("Tomato")
                .cheese("Mozzarella")
                .hasPepperoni(true)
                .hasOlives(true)
                .hasSpicySauce(true)
                .build();
        System.out.println(pizza1);

        Pizza pizza2 = new Pizza.Builder("Medium", "Thick Crust")
                .sauce("BBQ")
                .cheese("Cheddar")
                .hasMushrooms(true)
                .hasExtraCheese(true)
                .build();
        System.out.println(pizza2);

        Pizza pizza3 = new Pizza.Builder("Small", "Regular")
                .build();
        System.out.println(pizza3);

        Pizza pizza4 = new Pizza.Builder("Large", "Thin Crust")
                .hasExtraCheese(true)
                .build();
        System.out.println(pizza4);
    }
}