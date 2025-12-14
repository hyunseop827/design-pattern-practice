package example.ex1.before;

import java.util.Random;

interface Enemy {
    String name();

    int attack();

    int hp();

    void takeDamage(int dmg);

    boolean isDead();
}

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

class Orc implements Enemy {
    private final Random r = new Random();
    private int hp = 60;

    public String name() {
        return "Orc";
    }

    public int attack() {
        return 8 + r.nextInt(6);
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

class GameStageBeforeFM {
    private final String mode;
    private final Random r = new Random();

    GameStageBeforeFM(String mode) {
        this.mode = mode;
    }

    public void playOnce() {
        Enemy e;
        if ("hard".equalsIgnoreCase(mode)) {
            e = new Orc();
        } else {
            if (r.nextBoolean()) e = new Slime();
            else e = new Goblin();
        }

        int heroHp = 40;
        System.out.println("Encounter: " + e.name() + " (HP " + e.hp() + ")");
        while (heroHp > 0 && !e.isDead()) {
            int heroAtk = 6 + r.nextInt(7);
            e.takeDamage(heroAtk);
            System.out.println("Hero hits " + e.name() + " for " + heroAtk + " (HP " + e.hp() + ")");
            if (e.isDead()) break;
            int enemyAtk = e.attack();
            heroHp = Math.max(0, heroHp - enemyAtk);
            System.out.println(e.name() + " hits Hero for " + enemyAtk + " (Hero HP " + heroHp + ")");
        }
        System.out.println((heroHp > 0) ? "Hero wins\n" : e.name() + " wins\n");
    }
}

public class Main {
    public static void main(String[] args) {
        String mode = (args.length > 0) ? args[0] : "easy";
        GameStageBeforeFM stage = new GameStageBeforeFM(mode);
        for (int i = 0; i < 3; i++) stage.playOnce();
    }
}
