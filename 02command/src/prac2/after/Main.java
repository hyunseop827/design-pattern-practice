package prac2.after;

import java.util.ArrayList;
import java.util.List;

// Command - declares an interface for executing an operation
interface Command {
    void execute();
}

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

// ConcreteCommand - binds a Receiver object to an action
class BuyStock implements Command {
    private final Stock stock; // Holds reference to Receiver

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy(); // Delegates action to Receiver
    }
}

// ConcreteCommand
class SellStock implements Command {
    private final Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}

// Invoker - asks the command to carry out the request
class Broker {
    private final List<Command> orderList = new ArrayList<>();

    public void takeOrder(Command order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Command order : orderList) {
            order.execute();
        }
        // Clear the order list after processing
        orderList.clear();
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. Create Receivers (stock items)
        Stock abcStock = new Stock("ABC", 10);
        Stock googleStock = new Stock("Google", 100);

        // 2. Create ConcreteCommands (encapsulate orders)
        BuyStock buyABC = new BuyStock(abcStock);
        SellStock sellABC = new SellStock(abcStock);
        BuyStock buyGoogle = new BuyStock(googleStock);
        SellStock sellGoogle = new SellStock(googleStock);

        // 3. Create Invoker (broker)
        Broker broker = new Broker();

        // 4. Queue orders (not executed yet)
        broker.takeOrder(buyABC);
        broker.takeOrder(sellGoogle);
        broker.takeOrder(buyGoogle);
        broker.takeOrder(sellABC);

        // 5. Execute all orders
        System.out.println("--- Executing Orders ---");
        broker.placeOrders();
    }
}
