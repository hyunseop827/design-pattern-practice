package prac1.after;

import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Strategy - defines the interface for discount calculation algorithms
interface MembershipType {
    double calculate(double total);
}

// ConcreteStrategy - 5% discount for students
class Student implements MembershipType {
    @Override
    public double calculate(double total) {
        return total * 0.95;
    }
}

// ConcreteStrategy - no discount for regular members
class Regular implements MembershipType {
    @Override
    public double calculate(double total) {
        return total;
    }
}

// ConcreteStrategy - 10% discount for premium members
class Premium implements MembershipType {
    @Override
    public double calculate(double total) {
        return total * 0.9;
    }
}

// ConcreteStrategy - 15~20% discount for VIP (20% if total >= 100000)
class Vip implements MembershipType {
    @Override
    public double calculate(double total) {
        if (total >= 100000) {
            return total * 0.8;
        } else {
            return total * 0.85;
        }
    }
}

class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    // Context method - delegates discount calculation to the injected strategy
    public double checkout(MembershipType membershipType) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return membershipType.calculate(total);
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Keyboard", 150000));
        cart.addProduct(new Product("Mouse", 50000));

        // Client passes ConcreteStrategy objects to the Context
        System.out.println("Regular Cost: " + cart.checkout(new Regular()));
        System.out.println("Student Cost: " + cart.checkout(new Student()));
        System.out.println("Premium Cost: " + cart.checkout(new Premium()));
        System.out.println("VIP Cost: " + cart.checkout(new Vip()));
    }
}