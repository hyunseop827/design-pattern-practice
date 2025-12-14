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

// Strategy
interface MembershipType {
    // 오타 수정: caclulate -> calculate
    double calculate(double total);
}

// Concrete Strategy
class Student implements MembershipType {
    @Override
    public double calculate(double total) {
        return total * 0.95;
    }
}

class Regular implements MembershipType {
    @Override
    public double calculate(double total) {
        return total;
    }
}

class Premium implements MembershipType {
    @Override
    public double calculate(double total) {
        return total * 0.9;
    }
}

class Vip implements MembershipType {
    @Override
    public double calculate(double total) {
        // [수정 포인트] 원본 코드의 VIP 로직 복구
        // 전략 패턴 내부에서도 비즈니스 로직(조건문)이 들어갈 수 있습니다.
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

    // Context: 전략을 주입받아 실행
    public double checkout(MembershipType membershipType) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        // 위임(Delegation)
        return membershipType.calculate(total);
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Keyboard", 150000));
        cart.addProduct(new Product("Mouse", 50000));

        // [수정 포인트] 문자열 대신 구체적인 전략 객체(Concrete Strategy)를 생성해서 넘겨야 합니다.
        System.out.println("Regular Cost: " + cart.checkout(new Regular()));
        System.out.println("Student Cost: " + cart.checkout(new Student()));
        System.out.println("Premium Cost: " + cart.checkout(new Premium()));
        System.out.println("VIP Cost: " + cart.checkout(new Vip()));
    }
}