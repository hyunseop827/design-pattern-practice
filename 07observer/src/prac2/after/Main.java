package prac2.after;

import java.util.ArrayList;
import java.util.List;

// 1. 이벤트 타입 정의
enum EventType {
    MONSTER_KILLED,
    ITEM_PICKED,
    LEVEL_UP
}

// 3. Observer 인터페이스 (Pull 방식이므로 인자 없음)
interface Observer {
    void update();
}

// 2. 이벤트 데이터 캡슐화 (State)
class GameEvent {
    private EventType type;
    private String data; // 몬스터 이름, 아이템 이름 등

    public GameEvent(EventType type, String data) {
        this.type = type;
        this.data = data;
    }

    public EventType getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}

// 4. Subject (공통 기능)
abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// 5. Concrete Subject (Player)
class Player extends Subject {
    private int level = 1;
    // [핵심] 가장 최근에 발생한 이벤트를 상태로 저장
    private GameEvent latestEvent;

    public GameEvent getLatestEvent() {
        return latestEvent;
    }

    public int getLevel() {
        return level;
    }

    public void killMonster(String name) {
        // 상태 갱신
        this.latestEvent = new GameEvent(EventType.MONSTER_KILLED, name);
        System.out.println("Action: Killed " + name);
        // 알림 전송 (데이터 없이 신호만 보냄)
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

// --- 아래 Observer 클래스들을 Pull 방식으로 완성해 보세요 ---

class AchievementSystem implements Observer {
    private Player player; // Pull을 위해 Subject를 알고 있어야 함

    public AchievementSystem(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        GameEvent event = player.getLatestEvent();
        if (event.getType().equals(EventType.MONSTER_KILLED)) {
            if(event.getData().equals("Dragon")){
                System.out.println("[Achievement] Dragon slayer");
            }
        }

    }
}

class QuestSystem implements Observer {
    private Player player;

    public QuestSystem(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        // 빈칸: 이벤트를 가져와서 몬스터 처치나 아이템 획득 로그 출력
        if(!player.getLatestEvent().getType().equals(EventType.LEVEL_UP)){
            System.out.println("[QuestSystem]" + player.getLatestEvent().getData());
        }

    }
}

class UISystem implements Observer {
    private Player player;

    public UISystem(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        // 빈칸: 레벨업 이벤트일 때만 UI 갱신 로그 출력
        if(player.getLatestEvent().getType().equals(EventType.LEVEL_UP)){
            System.out.println("[UISystem]" + player.getLatestEvent().getData());
        }

    }
}

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        // 구독 설정
        player.addObserver(new AchievementSystem(player));
        player.addObserver(new QuestSystem(player));
        player.addObserver(new UISystem(player));

        // 게임 진행
        player.killMonster("Orc");
        player.killMonster("Dragon");
        player.pickItem("Gold Coin");
        player.levelUp();
    }
}