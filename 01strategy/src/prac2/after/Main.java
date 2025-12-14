package prac2.after;

// Strategy
interface Weapon {
    void printAttack();
}

// Concrete Strategy
class Sword implements Weapon {
    @Override
    public void printAttack() {
        System.out.println("Swinging a sword directly!");
    }
}

class Bow implements Weapon {
    @Override
    public void printAttack() {
        System.out.println("Shooting an arrow from distance!");
    }
}

class Magic implements Weapon {
    @Override
    public void printAttack() {
        System.out.println("Casting a fireball spell!");
    }
}

class GameCharacter {
    private final String name;
    private Weapon weaponType;

    public GameCharacter(String name, Weapon weaponType) {
        this.name = name;
        this.weaponType = weaponType;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public void attack() {
        System.out.print(name + ": ");

        weaponType.printAttack();
    }
}

public class Main {
    public static void main(String[] args) {

        Weapon sword = new Sword();
        Weapon bow = new Bow();
        Weapon magic = new Magic();

        GameCharacter character = new GameCharacter("Conan", sword);
        character.attack();

        character.setWeaponType(bow);
        character.attack();

        character.setWeaponType(magic);
        character.attack();
    }
}
