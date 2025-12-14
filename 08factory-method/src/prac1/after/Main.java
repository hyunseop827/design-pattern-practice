package prac1.after;

// Product
interface Vehicle {
    void deliver();
}

// Concrete Product
class Truck implements Vehicle {
    @Override
    public void deliver() {
        System.out.println("Deliver by land in a box");
    }
}

class Ship implements Vehicle {
    @Override
    public void deliver() {
        System.out.println("Deliver by sea in a container");
    }
}

// Creator
abstract class Logistics {
    public void planDelivery() {
        Vehicle v = createVeichle();
        v.deliver();
    }
    protected abstract Vehicle createVeichle();
}

// Concrete Creator
class TruckLogistic extends Logistics{
    @Override
    protected Vehicle createVeichle() {
        return new Truck();
    }
}

class ShipLogistic extends Logistics{
    @Override
    protected Vehicle createVeichle() {
        return new Ship();
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("\n--- Sea ---");
        Logistics logistics1 = new ShipLogistic();
        logistics1.planDelivery();

        System.out.println("--- Road ---");
        Logistics logistics2 = new TruckLogistic();
        logistics2.planDelivery();
    }
}
