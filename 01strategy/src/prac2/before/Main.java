package prac2.before;

class GameCharacter {
    private final String name;
    private String weaponType;

    public GameCharacter(String name, String weaponType) {
        this.name = name;
        this.weaponType = weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public void attack() {
        System.out.print(name + ": ");

        // Problematic part - violates OCP!
        if (weaponType.equals("SWORD")) {
            System.out.println("Swinging a sword directly!");
        } else if (weaponType.equals("BOW")) {
            System.out.println("Shooting an arrow from distance!");
        } else if (weaponType.equals("MAGIC")) {
            System.out.println("Casting a fireball spell!");
        } else {
            System.out.println("Punching with bare hands!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GameCharacter character = new GameCharacter("Conan", "SWORD");
        character.attack();

        character.setWeaponType("BOW");
        character.attack();

        character.setWeaponType("MAGIC");
        character.attack();
    }
}
