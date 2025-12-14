package example.ex1.after;

import java.util.ArrayList;
import java.util.List;

// Observer - defines an updating interface for objects that should be notified of changes in a Subject
interface Observer {
    void update();
}

// ConcreteObserver - maintains a reference to a ConcreteSubject and implements the Observer updating interface
class BatteryLevelDisplay implements Observer {

    private final Battery battery;

    public BatteryLevelDisplay(Battery battery) {
        this.battery = battery;
    }

    @Override
    public void update() {
        int current = battery.getLevel();
        System.out.println("Level: " + current);
    }
}

// ConcreteObserver - displays warning when battery is low
class LowBatteryWarning implements Observer {

    private static final int LOW_BATTERY = 30;
    private final Battery battery;

    public LowBatteryWarning(Battery battery) {
        this.battery = battery;
    }

    @Override
    public void update() {
        int level = battery.getLevel();
        if (level < LOW_BATTERY)
            System.out.println("<Warning> Low Battery : " + level + " Compared with " + LOW_BATTERY);
    }
}

// Subject - knows its observers and provides an interface for attaching and detaching Observer objects
abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void add(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// ConcreteSubject - stores state of interest to ConcreteObserver objects and sends notification when state changes
class Battery extends Subject {
    private int level = 100;

    public void consume(int amount) {
        level -= amount;
        notifyObservers();
    }

    public int getLevel() {
        return level;
    }
}


public class Main {
    public static void main(String[] args) {
        Battery battery = new Battery();

        Observer batteryDisplay = new BatteryLevelDisplay(battery);
        Observer lowBatteryWarning = new LowBatteryWarning(battery);

        // Register observers
        battery.add(batteryDisplay);
        battery.add(lowBatteryWarning);

        battery.consume(20);
        battery.consume(50);
        battery.consume(10);
    }
}

