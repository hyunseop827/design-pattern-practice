package prac1.before;

import java.util.ArrayList;
import java.util.List;

class Product {
    private final String name;
    private final double price;

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

class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double checkout(String membershipType) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }

        // Problematic part - violates OCP!
        if (membershipType.equals("REGULAR")) {
            return total;
        } else if (membershipType.equals("STUDENT")) {
            return total * 0.95;
        } else if (membershipType.equals("PREMIUM")) {
            return total * 0.9;
        } else if (membershipType.equals("VIP")) {
            if (total >= 100000) {
                return total * 0.8;
            } else {
                return total * 0.85;
            }
        } else {
            return total;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Keyboard", 150000));
        cart.addProduct(new Product("Mouse", 50000));

        System.out.println("Regular Cost: " + cart.checkout("REGULAR"));
        System.out.println("Student Cost: " + cart.checkout("STUDENT"));
        System.out.println("Premium Cost: " + cart.checkout("PREMIUM"));
        System.out.println("VIP Cost: " + cart.checkout("VIP"));
    }
}
