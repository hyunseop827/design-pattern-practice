package prac2.before;

// Target - defines the domain-specific interface that Client uses
interface Robot {
    void walk(int meters);

    void attack();
}

// Existing implementation that conforms to Target interface
class HumanoidRobot implements Robot {
    public void walk(int meters) {
        System.out.println("Humanoid walking " + meters + " meters");
    }

    public void attack() {
        System.out.println("Humanoid punching!");
    }
}

// Adaptee - defines an existing interface that needs adapting
class AlienMachine {
    public void moveLegs(double feet) {
        System.out.println("Alien moving " + feet + " feet");
    }

    public void fireLaser() {
        System.out.println("Alien firing laser!");
    }
}

// Client - collaborates with objects conforming to the Target interface
class Commander {
    public void order(Robot robot) {
        System.out.println("--- Commander Ordering Robot ---");
        robot.walk(10); // Command to move 10 meters
        robot.attack();
    }
}

public class Main {
    public static void main(String[] args) {
        Commander commander = new Commander();

        // 1. Existing robot
        HumanoidRobot humanBot = new HumanoidRobot();
        commander.order(humanBot);

        // 2. Alien robot (incompatible interface)
        AlienMachine alien = new AlienMachine();

        // TODO: Create an adapter to make the code below work.
        // Robot alienAdapter = new ...
        // commander.order(alienAdapter);
    }
}
