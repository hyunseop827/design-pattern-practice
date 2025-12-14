package example.ex1.before;

import java.util.Random;

enum Theme {
    FOREST, VOLCANO
}

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

// Concrete monsters

class Slime implements Enemy {
    private int hp = 20;
    private final Random r = new Random();

    public String name() { return "Slime"; }
    public int attack() { return 3 + r.nextInt(3); }
    public int hp() { return hp; }
    public void takeDamage(int dmg) { hp = Math.max(0, hp - dmg); }
    public boolean isDead() { return hp <= 0; }
}

class Goblin implements Enemy {
    private int hp = 35;
    private final Random r = new Random();

    public String name() { return "Goblin"; }
    public int attack() { return 5 + r.nextInt(5); }
    public int hp() { return hp; }
    public void takeDamage(int dmg) { hp = Math.max(0, hp - dmg); }
    public boolean isDead() { return hp <= 0; }
}

class FireElemental implements Enemy {
    private int hp = 50;
    private final Random r = new Random();

    public String name() { return "Fire Elemental"; }
    public int attack() { return 7 + r.nextInt(7); }
    public int hp() { return hp; }
    public void takeDamage(int dmg) { hp = Math.max(0, hp - dmg); }
    public boolean isDead() { return hp <= 0; }
}

// Concrete items

class HealingPotion implements Item {
    public String name() { return "Healing Potion"; }

    public void useOn(Enemy enemy) {
        System.out.println("Potion has no effect on " + enemy.name());
    }
}

class FireSword implements Item {
    private final Random r = new Random();

    public String name() { return "Fire Sword"; }

    public void useOn(Enemy enemy) {
        int dmg = 10 + r.nextInt(6);
        enemy.takeDamage(dmg);
        System.out.println("Fire Sword hits " + enemy.name() + " for " + dmg + " (HP " + enemy.hp() + ")");
    }
}

// Game stage before applying Abstract Factory pattern

class GameStageBeforeAF {
    private final Theme theme;
    private final Random r = new Random();

    GameStageBeforeAF(Theme theme) {
        this.theme = theme;
    }

    private Enemy createEnemy() {
        // Directly determine monster based on theme
        if (theme == Theme.FOREST) {
            if (r.nextBoolean()) return new Slime();
            return new Goblin();
        } else {
            return new FireElemental();
        }
    }

    private Item createItem() {
        // Directly determine item based on theme
        if (theme == Theme.FOREST) {
            return new HealingPotion();
        } else {
            return new FireSword();
        }
    }

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

            // Occasionally use item
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

// Main entry point

public class Main {
    public static void main(String[] args) {
        Theme theme = (args.length > 0 && "volcano".equalsIgnoreCase(args[0]))
                ? Theme.VOLCANO
                : Theme.FOREST;

        GameStageBeforeAF stage = new GameStageBeforeAF(theme);
        stage.playOnce();
    }
}
