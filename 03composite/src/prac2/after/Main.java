package prac2.after;

import java.util.ArrayList;
import java.util.List;

// Component (이름 변경: Items -> MenuComponent)
abstract class MenuComponent {
    protected String name;

    public MenuComponent(String name) {
        this.name = name;
    }

    public abstract double getPrice();
    public abstract void print();
}

// Leaf
class MenuItem extends MenuComponent {
    private double price;

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

// Composite
class Menu extends MenuComponent {
    // 자식들을 담는 통합 리스트
    private List<MenuComponent> menuComponents = new ArrayList<>();

    public Menu(String name) {
        super(name);
    }

    // 메서드 통합 (addMenuItem + addSubMenu)
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public double getPrice() {
        double total = 0;
        // 재귀적으로 가격 합산
        for(MenuComponent component : menuComponents) {
            total += component.getPrice();
        }
        return total;
    }

    @Override
    public void print() {
        // [중요] 자기 자신(메뉴 이름) 출력
        System.out.println("\nMenu: " + name);
        System.out.println("---------------------");

        // 재귀적으로 출력 위임
        for(MenuComponent component : menuComponents) {
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

        // 트리 구조 조립
        mainMenu.add(burger);
        mainMenu.add(pizza);

        dinnerMenu.add(dessertMenu); // 메뉴 안에 메뉴 넣기
        dessertMenu.add(cake);       // 메뉴 안에 아이템 넣기
        dessertMenu.add(iceCream);

        mainMenu.add(dinnerMenu);

        // 전체 출력
        mainMenu.print();
        System.out.println("\nTotal Price: $" + mainMenu.getPrice());
    }
}