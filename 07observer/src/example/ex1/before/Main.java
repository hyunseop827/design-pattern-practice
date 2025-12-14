package example.ex1.before;

class Battery {
    private int level = 100;
    private BatteryLevelDisplay display;
    private LowBatteryWarning warning;

    public void setDisplay(BatteryLevelDisplay display) {
        this.display = display;
    }

    public void setWarning(LowBatteryWarning warning) {
        this.warning = warning;
    }

    public void consume(int amount) {
        level -= amount;

        display.update();
        warning.update();
    }

    public int getLevel() {
        return level;
    }
}

class BatteryLevelDisplay {
    private final Battery battery;

    public BatteryLevelDisplay(Battery battery) {
        this.battery = battery;
    }

    public void update() {
        int level = battery.getLevel();
        System.out.println("Level: " + level);
    }
}

class LowBatteryWarning {
    private static final int LOW_BATTERY = 30;
    private final Battery battery;

    public LowBatteryWarning(Battery battery) {
        this.battery = battery;
    }

    public void update() {
        int level = battery.getLevel();
        if (level < LOW_BATTERY)
            System.out.println("<Warning> Low Battery : " + level + " Compared with " + LOW_BATTERY);
    }
}

public class Main {
    public static void main(String[] args) {
        Battery battery = new Battery();

        BatteryLevelDisplay batteryDisplay = new BatteryLevelDisplay(battery);
        LowBatteryWarning lowBatteryWarning = new LowBatteryWarning(battery);

        battery.setDisplay(batteryDisplay);
        battery.setWarning(lowBatteryWarning);

        battery.consume(20);
        battery.consume(50);
        battery.consume(10);
    }
}

