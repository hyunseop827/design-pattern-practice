package prac1.after;

// Abstract Class
abstract class Beverage {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        add();
    }

    private void boilWater() {
        System.out.println("Boiling Water");
    }

    private void pourInCup() {
        System.out.println("Pour In Cup");
    }

    protected abstract void brew();
    protected abstract void add();
}


// Concrete Class
class Coffee  extends Beverage {
    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    protected void add() {
        System.out.println("Adding Sugar and Milk");
    }
}

class Tea  extends Beverage {
    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }
    @Override
    protected void add() {
        System.out.println("Adding Lemon");
    }
}

public class Main {
    public static void main(String[] args) {

        Beverage b1 = new Coffee();
        Beverage b2 = new Tea();

        b1.prepareRecipe();
        b2.prepareRecipe();
    }
}
