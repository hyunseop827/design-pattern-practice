package prac2.before;

import java.util.ArrayList;
import java.util.List;

// Receiver - knows how to perform the operations
class Stock {
    private final String name;
    private final int quantity;

    public Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void buy() {
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
    }
}

// Invoker (Bad Design - violates OCP)
class Broker {
    private final List<Stock> buyOrders = new ArrayList<>();
    private final List<Stock> sellOrders = new ArrayList<>();

    // Problematic part - violates OCP!
    public void takeOrder(String type, Stock stock) {
        if (type.equals("BUY")) {
            buyOrders.add(stock);
        } else if (type.equals("SELL")) {
            sellOrders.add(stock);
        }
    }

    public void placeOrders() {
        for (Stock stock : buyOrders) {
            stock.buy();
        }
        for (Stock stock : sellOrders) {
            stock.sell();
        }
        buyOrders.clear();
        sellOrders.clear();
    }
}

public class Main {
    public static void main(String[] args) {
        Stock abcStock = new Stock("ABC", 10);
        Stock googleStock = new Stock("Google", 100);

        Broker broker = new Broker();

        broker.takeOrder("BUY", abcStock);
        broker.takeOrder("SELL", googleStock);
        broker.takeOrder("BUY", googleStock);

        System.out.println("--- Executing Orders ---");
        broker.placeOrders();
    }
}
