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

// 구체 몬스터들

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

// 구체 아이템들

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

// 추상 팩토리 적용 전 게임 스테이지

class GameStageBeforeAF {
    private final Theme theme;
    private final Random r = new Random();

    GameStageBeforeAF(Theme theme) {
        this.theme = theme;
    }

    private Enemy createEnemy() {
        // 테마에 따라 몬스터를 직접 결정
        if (theme == Theme.FOREST) {
            if (r.nextBoolean()) return new Slime();
            return new Goblin();
        } else {
            return new FireElemental();
        }
    }

    private Item createItem() {
        // 테마에 따라 아이템을 직접 결정
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

// 실행용 main

public class Main {
    public static void main(String[] args) {
        Theme theme = (args.length > 0 && "volcano".equalsIgnoreCase(args[0]))
                ? Theme.VOLCANO
                : Theme.FOREST;

        GameStageBeforeAF stage = new GameStageBeforeAF(theme);
        stage.playOnce();
    }
}
