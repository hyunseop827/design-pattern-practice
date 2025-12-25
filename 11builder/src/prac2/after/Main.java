package prac2.after;

class Car {
    private final String brand;
    private final String model;
    private final String color;
    private final int year;
    private final boolean hasAircon;
    private final boolean hasNavigation;
    private final boolean hasSunroof;
    private final String fuelType;

    private Car(Builder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.color = builder.color;
        this.year = builder.year;
        this.hasAircon = builder.hasAircon;
        this.hasNavigation = builder.hasNavigation;
        this.hasSunroof = builder.hasSunroof;
        this.fuelType = builder.fuelType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", hasAircon=" + hasAircon +
                ", hasNavigation=" + hasNavigation +
                ", hasSunroof=" + hasSunroof +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }

    public static class Builder {
        private String brand;
        private String model;
        private String color;
        private int year;
        private boolean hasAircon;
        private boolean hasNavigation;
        private boolean hasSunroof;
        private String fuelType;

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder hasAircon(boolean hasAircon) {
            this.hasAircon = hasAircon;
            return this;
        }

        public Builder hasNavigation(boolean hasNavigation) {
            this.hasNavigation = hasNavigation;
            return this;
        }

        public Builder hasSunroof(boolean hasSunroof) {
            this.hasSunroof = hasSunroof;
            return this;
        }

        public Builder fuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car.Builder()
                .brand("BMW")
                .model("X5")
                .color("Black")
                .year(2020)
                .hasAircon(true)
                .hasNavigation(true)
                .hasSunroof(true)
                .fuelType("Hybrid")
                .build();

        System.out.println(car);
    }
}
