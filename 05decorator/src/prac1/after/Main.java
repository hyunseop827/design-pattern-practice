package prac1.after;

// Component
interface Toast {
    int getPrice();

    String getRecipe();
}

// Concrete Component
class Bread implements Toast {

    private final int price;
    private final String recipe;

    public Bread() {
        this.price = 2000;
        this.recipe = "Bread";
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getRecipe() {
        return recipe;
    }
}

// Decorator
abstract class Topping implements Toast {
    protected int price;
    protected String recipe;
    private Toast toast;

    public Topping(Toast toast) {
        this.toast = toast;
    }

    @Override
    public int getPrice() {
        return toast.getPrice();
    }

    @Override
    public String getRecipe() {
        return toast.getRecipe();
    }
}

// Concrete Decorator
class Cheese extends Topping {
    public Cheese(Toast toast) {
        super(toast);
        this.price = 500;
        this.recipe = "Cheese";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + this.price;
    }

    @Override
    public String getRecipe() {
        return super.getRecipe() + this.recipe;
    }
}

class Ham extends Topping {
    public Ham(Toast toast) {
        super(toast);
        this.price = 1000;
        this.recipe = "Ham";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + this.price;
    }

    @Override
    public String getRecipe() {
        return super.getRecipe() + this.recipe;
    }
}

class Jelly extends Topping {
    public Jelly(Toast toast) {
        super(toast);
        this.price = 300;
        this.recipe = "Jelly";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + this.price;
    }

    @Override
    public String getRecipe() {
        return super.getRecipe() + this.recipe;
    }
}

public class Main {
    public static void main(String[] args) {
        // 치즈와 햄이 들어간 토스트 주문
        Toast myToast = new Cheese(new Ham(new Bread()));

        System.out.println("Menu: " + myToast.getRecipe());
        System.out.println("Price: " + myToast.getPrice());
    }
}