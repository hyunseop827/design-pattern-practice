package prac1.before;

class Truck {
    public void deliver() {
        System.out.println("Deliver by land in a box");
    }
}

class Ship {
    public void deliver() {
        System.out.println("Deliver by sea in a container");
    }
}

class Logistics {
    public void planDelivery(String transportType) {
        if (transportType.equals("TRUCK")) {
            Truck t = new Truck();
            t.deliver();
        } else if (transportType.equals("SHIP")) {
            Ship s = new Ship();
            s.deliver();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Logistics logistics = new Logistics();

        System.out.println("--- Road ---");
        logistics.planDelivery("TRUCK");

        System.out.println("\n--- Sea ---");
        logistics.planDelivery("SHIP");
    }
}