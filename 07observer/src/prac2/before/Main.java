package prac2.before;

import java.util.ArrayList;
import java.util.List;

// 1. Event type definition
enum EventType {
    MONSTER_KILLED,
    ITEM_PICKED,
    LEVEL_UP
}

// 2. Event data encapsulation (State)
class GameEvent {
    private final EventType type;
    private final String data; // Monster name, item name, etc.

    public GameEvent(EventType type, String data) {
        this.type = type;
        this.data = data;
    }

    public EventType getType() { return type; }
    public String getData() { return data; }
}

// 3. Observer interface (Pull-based, no arguments)
interface Observer {
    void update();
}

// 4. Subject - knows its observers and provides an interface for attaching Observer objects
abstract class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// 5. ConcreteSubject (Player)
class Player extends Subject {
    private int level = 1;
    // Key: Store the most recent event as state
    private GameEvent latestEvent;

    public GameEvent getLatestEvent() {
        return latestEvent;
    }

    public int getLevel() {
        return level;
    }

    public void killMonster(String name) {
        // Update state
        this.latestEvent = new GameEvent(EventType.MONSTER_KILLED, name);
        System.out.println("Action: Killed " + name);
        // Send notification (signal only, no data)
        notifyObservers();
    }

    public void pickItem(String name) {
        this.latestEvent = new GameEvent(EventType.ITEM_PICKED, name);
        System.out.println("Action: Picked up " + name);
        notifyObservers();
    }

    public void levelUp() {
        this.level++;
        this.latestEvent = new GameEvent(EventType.LEVEL_UP, String.valueOf(level));
        System.out.println("Action: Leveled Up!");
        notifyObservers();
    }
}

// --- Complete the Observer classes below using Pull-based approach ---

// ConcreteObserver
class AchievementSystem implements Observer {
    private final Player player; // Must know Subject for Pull

    public AchievementSystem(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        // TODO: Pull event from player and check if "Dragon" was killed

    }
}

// ConcreteObserver
class QuestSystem implements Observer {
    private final Player player;

    public QuestSystem(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        // TODO: Pull event and log monster kills or item pickups

    }
}

// ConcreteObserver
class UISystem implements Observer {
    private final Player player;

    public UISystem(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        // TODO: Only update UI log on level up events

    }
}

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        // Register observers
        player.addObserver(new AchievementSystem(player));
        player.addObserver(new QuestSystem(player));
        player.addObserver(new UISystem(player));

        // Game progression
        player.killMonster("Orc");
        player.killMonster("Dragon");
        player.pickItem("Gold Coin");
        player.levelUp();
    }
}
