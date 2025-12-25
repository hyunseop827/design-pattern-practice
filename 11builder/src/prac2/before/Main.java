package prac2.before;

class Car {
    private final String brand;
    private final String model;
    private final String color;
    private final int year;
    private final boolean hasAircon;
    private final boolean hasNavigation;
    private final boolean hasSunroof;
    private final String fuelType;

    public Car(String brand, String model, String color, int year,
               boolean hasAircon, boolean hasNavigation, boolean hasSunroof, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.hasAircon = hasAircon;
        this.hasNavigation = hasNavigation;
        this.hasSunroof = hasSunroof;
        this.fuelType = fuelType;
    }

    public Car(String brand, String model) {
        this(brand, model, "White", 2024, true, false, false, "Gasoline");
    }

    public Car(String brand, String model, String color) {
        this(brand, model, color, 2024, true, false, false, "Gasoline");
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
}

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car(
                "BMW",
                "X5",
                "Black",
                2023,
                true,
                true,
                true,
                "Hybrid"
        );
        System.out.println(car1);

        Car car2 = new Car(
                "Toyota",
                "Camry",
                "Silver",
                2024,
                true,
                false,
                false,
                "Gasoline"
        );
        System.out.println(car2);

        Car car3 = new Car("Honda", "Civic");
        System.out.println(car3);

        Car car4 = new Car("Hyundai", "Sonata", "Blue");
        System.out.println(car4);
    }
}