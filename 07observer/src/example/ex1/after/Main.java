package example.ex1.after;

import java.util.List;
import java.util.ArrayList;

// Observer
interface Observer {
    void update();
}

// Concrete Observer
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

// Subject
abstract class Subject{
    private List<Observer> observers = new  ArrayList<Observer>();

    public void add(Observer observer){
        observers.add(observer);
    }
    public void remove(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update();
        }
    }
}

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

        // 옵저버 등록
        battery.add(batteryDisplay);
        battery.add(lowBatteryWarning);

        battery.consume(20);
        battery.consume(50);
        battery.consume(10);
    }
}

