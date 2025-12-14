package example.ex1.after;

import java.util.Random;

enum Theme {
    FOREST, VOLCANO
}

// Abstract Product
interface Enemy {
    String name();

    int attack();

    int hp();

    void takeDamage(int dmg);

    boolean isDead();
}

interface Item {
    String name();

    void useOn(Enemy enemy);
}

// Concrete Product
class Slime implements Enemy {
    private final Random r = new Random();
    private int hp = 20;

    public String name() {
        return "Slime";
    }

    public int attack() {
        return 3 + r.nextInt(3);
    }

    public int hp() {
        return hp;
    }

    public void takeDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    public boolean isDead() {
        return hp <= 0;
    }
}

class Goblin implements Enemy {
    private final Random r = new Random();
    private int hp = 35;

    public String name() {
        return "Goblin";
    }

    public int attack() {
        return 5 + r.nextInt(5);
    }

    public int hp() {
        return hp;
    }

    public void takeDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    public boolean isDead() {
        return hp <= 0;
    }
}

class FireElemental implements Enemy {
    private final Random r = new Random();
    private int hp = 50;

    public String name() {
        return "Fire Elemental";
    }

    public int attack() {
        return 7 + r.nextInt(7);
    }

    public int hp() {
        return hp;
    }

    public void takeDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    public boolean isDead() {
        return hp <= 0;
    }
}

class HealingPotion implements Item {
    public String name() {
        return "Healing Potion";
    }

    public void useOn(Enemy enemy) {
        System.out.println("Potion has no effect on " + enemy.name());
    }
}

class FireSword implements Item {
    private final Random r = new Random();

    public String name() {
        return "Fire Sword";
    }

    public void useOn(Enemy enemy) {
        int dmg = 10 + r.nextInt(6);
        enemy.takeDamage(dmg);
        System.out.println("Fire Sword hits " + enemy.name() + " for " + dmg + " (HP " + enemy.hp() + ")");
    }
}

// Abstract Factory
abstract class GameStage {
    private final Theme theme;
    protected final Random r = new Random();

    GameStage(Theme theme) {
        this.theme = theme;
    }

    public abstract Enemy createEnemy();

    public abstract Item createItem();

    public void playOnce() {
        Enemy enemy = createEnemy();
        Item item = createItem();

        int heroHp = 40;
        System.out.println("Theme " + theme + " start");
        System.out.println("Encounter: " + enemy.name() + " (HP " + enemy.hp() + ")");
        System.out.println("Hero picks up " + item.name());

        while (heroHp > 0 && !enemy.isDead()) {
            int heroAtk = 6 + r.nextInt(7);
            enemy.takeDamage(heroAtk);
            System.out.println("Hero hits " + enemy.name() + " for " + heroAtk + " (HP " + enemy.hp() + ")");
            if (enemy.isDead()) break;

            // 가끔 아이템 사용
            if (r.nextInt(3) == 0) {
                item.useOn(enemy);
                if (enemy.isDead()) break;
            }

            int enemyAtk = enemy.attack();
            heroHp = Math.max(0, heroHp - enemyAtk);
            System.out.println(enemy.name() + " hits Hero for " + enemyAtk + " (Hero HP " + heroHp + ")");
        }

        System.out.println((heroHp > 0) ? "Hero wins\n" : enemy.name() + " wins\n");
    }
}

// Concrete Factory
class ForestGameStage extends GameStage {
    public ForestGameStage(Theme theme) {
        super(theme);
    }

    @Override
    public Enemy createEnemy() {
        if (r.nextBoolean()) return new Slime();
        return new Goblin();
    }

    @Override
    public Item createItem() {
        return new HealingPotion();
    }
}

class VolcanoGameStage extends GameStage {
    public VolcanoGameStage(Theme theme) {
        super(theme);
    }

    @Override
    public Enemy createEnemy() {
        return new FireElemental();
    }

    @Override
    public Item createItem() {
        return new FireSword();
    }
}

// 실행용 main

public class Main {
    public static void main(String[] args) {
        Theme theme = (args.length > 0 && "volcano".equalsIgnoreCase(args[0]))
                ? Theme.VOLCANO
                : Theme.FOREST;
        GameStage gameStage = null;
        if(theme.equals(Theme.FOREST)) {
            gameStage = new ForestGameStage(theme);
        }
        else{
            gameStage = new VolcanoGameStage(theme);
        }

        gameStage.playOnce();
    }
}
