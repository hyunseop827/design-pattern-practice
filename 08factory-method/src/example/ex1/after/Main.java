package example.ex1.after;

import java.util.Random;

// Product - defines the interface of objects the factory method creates
interface Enemy {
    String name();

    int attack();

    int hp();

    void takeDamage(int dmg);

    boolean isDead();
}

// ConcreteProduct - implements the Product interface
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

// ConcreteProduct
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

// ConcreteProduct
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

// Creator - declares the factory method which returns an object of type Product
abstract class GameStage {
    private final String mode;
    protected final Random r = new Random();
    public GameStage(String mode) {
        this.mode = mode;
    }

    public void playOnce(){
        Enemy e = createEnemy();
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

    protected abstract Enemy createEnemy();
}

// ConcreteCreator - overrides the factory method to return an instance of a ConcreteProduct
class EasyGameStage extends GameStage {
    public EasyGameStage(String mode) {
        super(mode);
    }

    @Override
    protected Enemy createEnemy() {
        if (r.nextBoolean()) { return new Slime();}
        return new  Goblin();
    }
}

// ConcreteCreator
class HardGameStage extends GameStage {
    public HardGameStage(String mode) {
        super(mode);
    }

    @Override
    protected Enemy createEnemy() {
        return new Orc();
    }
}
public class Main {
    public static void main(String[] args) {
        String mode = (args.length > 0) ? args[0] : "easy";
        GameStage game;
        if(mode.equals("easy")) {
            game = new EasyGameStage(mode);
        }
        else{
            game = new HardGameStage(mode);
        }
        game.playOnce();;

    }
}
