package prac2.after;

import java.util.ArrayList;
import java.util.List;

// Component - declares the interface for objects in the composition
abstract class MenuComponent {
    protected String name;

    public MenuComponent(String name) {
        this.name = name;
    }

    public abstract double getPrice();
    public abstract void print();
}

// Leaf - represents leaf objects in the composition (has no children)
class MenuItem extends MenuComponent {
    private final double price;

    public MenuItem(String name, double price) {
        super(name);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void print() {
        System.out.println("  Item: " + name + " ($" + price + ")");
    }
}

// Composite - defines behavior for components having children
class Menu extends MenuComponent {
    // Unified list for children
    private final List<MenuComponent> menuComponents = new ArrayList<>();

    public Menu(String name) {
        super(name);
    }

    // Unified method (addMenuItem + addSubMenu)
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public double getPrice() {
        double total = 0;
        // Recursively sum prices
        for (MenuComponent component : menuComponents) {
            total += component.getPrice();
        }
        return total;
    }

    @Override
    public void print() {
        // Print self (menu name)
        System.out.println("\nMenu: " + name);
        System.out.println("---------------------");

        // Recursively delegate printing
        for (MenuComponent component : menuComponents) {
            component.print();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Menu mainMenu = new Menu("Main Menu");
        Menu dinnerMenu = new Menu("Dinner Menu");
        Menu dessertMenu = new Menu("Dessert Menu");

        MenuItem burger = new MenuItem("Burger", 10.0);
        MenuItem pizza = new MenuItem("Pizza", 15.0);
        MenuItem cake = new MenuItem("Cake", 5.0);
        MenuItem iceCream = new MenuItem("Ice Cream", 3.0);

        // Build tree structure
        mainMenu.add(burger);
        mainMenu.add(pizza);

        dinnerMenu.add(dessertMenu); // Menu inside menu
        dessertMenu.add(cake);       // Item inside menu
        dessertMenu.add(iceCream);

        mainMenu.add(dinnerMenu);

        // Print entire structure
        mainMenu.print();
        System.out.println("\nTotal Price: $" + mainMenu.getPrice());
    }
}
