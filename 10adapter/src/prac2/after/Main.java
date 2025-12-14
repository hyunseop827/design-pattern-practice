package prac2.after;

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

// ** Adapter **
class Adapter implements Robot {

    private AlienMachine alienMachine;

    public Adapter(AlienMachine alienMachine) {
        this.alienMachine = alienMachine;
    }

    @Override
    public void walk(int meters) {
        double feet = 3.281 * meters;
        alienMachine.moveLegs(feet);
    }

    @Override
    public void attack() {
        alienMachine.fireLaser();
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
        Robot adapterRobot =new  Adapter(alien);
        commander.order(adapterRobot);
    }
}