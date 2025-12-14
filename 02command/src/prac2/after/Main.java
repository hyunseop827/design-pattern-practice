package prac2.after;

import java.util.ArrayList;
import java.util.List;

// Command
interface Command {
    void execute();
}

// Receiver
class Stock {
    private String name;
    private int quantity;

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

// Concrete Command - Buy
class BuyStock implements Command {
    private Stock stock; // Receiver를 참조로 가짐

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy(); // 가지고 있는 Receiver에게 행동 위임
    }
}

// Concrete Command - Sell
class SellStock implements Command {
    private Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}

// Invoker
class Broker {
    private List<Command> orderList = new ArrayList<>();

    public void takeOrder(Command order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Command order : orderList) {
            order.execute();
        }
        // [중요] 주문 처리가 끝났으면 목록을 비워줍니다.
        orderList.clear();
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. Receiver 생성 (주식 종목)
        Stock abcStock = new Stock("ABC", 10);
        Stock googleStock = new Stock("Google", 100);

        // 2. Concrete Command 생성 (주문을 캡슐화)
        // "ABC 주식을 산다", "구글 주식을 판다"는 행위를 객체로 만듦
        BuyStock buyABC = new BuyStock(abcStock);
        SellStock sellABC = new SellStock(abcStock);
        BuyStock buyGoogle = new BuyStock(googleStock);
        SellStock sellGoogle = new SellStock(googleStock);

        // 3. Invoker (중개인)
        Broker broker = new Broker();

        // 4. 주문 접수 (실행되지 않고 큐에 쌓임)
        broker.takeOrder(buyABC);
        broker.takeOrder(sellGoogle);
        broker.takeOrder(buyGoogle);
        broker.takeOrder(sellABC);

        // 5. 일괄 실행
        System.out.println("--- Executing Orders ---");
        broker.placeOrders();
    }
}