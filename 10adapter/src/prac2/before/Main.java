package prac2.before;

// Target Interface
interface Robot {
    void walk(int meters);

    void attack();
}

// Normal Implementation
class HumanoidRobot implements Robot {
    public void walk(int meters) {
        System.out.println("Humanoid walking " + meters + " meters");
    }

    public void attack() {
        System.out.println("Humanoid punching!");
    }
}

// Adaptee (Incompatible Interface)
class AlienMachine {
    public void moveLegs(double feet) {
        System.out.println("Alien moving " + feet + " feet");
    }

    public void fireLaser() {
        System.out.println("Alien firing laser!");
    }
}

// Client
class Commander {
    public void order(Robot robot) {
        System.out.println("--- Commander Ordering Robot ---");
        robot.walk(10); // 10 미터 이동 명령
        robot.attack();
    }
}

public class Main {
    public static void main(String[] args) {
        Commander commander = new Commander();

        // 1. 기존 로봇
        HumanoidRobot humanBot = new HumanoidRobot();
        commander.order(humanBot);

        // 2. 외계 로봇 (호환 불가)
        AlienMachine alien = new AlienMachine();

        // TODO: 어댑터를 만들어 아래 코드가 동작하게 하세요.
        // Robot alienAdapter = new ...
        // commander.order(alienAdapter);
    }
}
