package prac2.before;

import java.util.ArrayList;
import java.util.List;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void print() {
        System.out.println("  Item: " + name + " ($" + price + ")");
    }
}

class Menu {
    private String name;
    private List<MenuItem> items = new ArrayList<>();
    private List<Menu> subMenus = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public void addMenuItem(MenuItem item) {
        items.add(item);
    }

    public void addSubMenu(Menu menu) {
        subMenus.add(menu);
    }

    public double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        for (Menu menu : subMenus) {
            total += menu.getTotalPrice();
        }
        return total;
    }

    public void print() {
        System.out.println("\nMenu: " + name);
        System.out.println("---------------------");

        for (Menu menu : subMenus) {
            menu.print();
        }
        for (MenuItem item : items) {
            item.print();
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

        mainMenu.addMenuItem(burger);
        mainMenu.addMenuItem(pizza);

        dinnerMenu.addSubMenu(dessertMenu);
        dessertMenu.addMenuItem(cake);
        dessertMenu.addMenuItem(iceCream);

        mainMenu.addSubMenu(dinnerMenu);

        mainMenu.print();
        System.out.println("\nTotal Price: $" + mainMenu.getTotalPrice());
    }
}
