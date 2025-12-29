package prac4.after;

// Logging Proxy

// Subject
interface OrderService {
    void createOrder(String item, int quantity);
    String getOrder(int orderId);
    void cancelOrder(int orderId);
}

// Real Subject
class RealOrderService implements OrderService{
    @Override
    public void createOrder(String item, int quantity) {
        System.out.println("Order created: " + item + " x" + quantity);
    }

    @Override
    public String getOrder(int orderId) {
        String result = "Order_" + orderId;
        return result;
    }

    @Override
    public void cancelOrder(int orderId) {
        System.out.println("Order cancelled: " + orderId);
    }
}

// Proxy
class LoggingProxy implements OrderService {
    private RealOrderService realOrderService;

    public LoggingProxy() {
        realOrderService = new RealOrderService();
    }

    @Override
    public void createOrder(String item, int quantity) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] createOrder() start - item: " + item + ", quantity: " + quantity);

        realOrderService.createOrder(item,quantity);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] createOrder() end - elapsed: " + elapsed + "ms");
    }

    @Override
    public String getOrder(int orderId) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] getOrder() start - orderId: " + orderId);

        String result = realOrderService.getOrder(orderId);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] getOrder() end - return: " + result + ", elapsed: " + elapsed + "ms");
        return result;
    }

    @Override
    public void cancelOrder(int orderId) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] cancelOrder() start - orderId: " + orderId);

        realOrderService.cancelOrder(orderId);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] cancelOrder() end - elapsed: " + elapsed + "ms");
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Order Service ===\n");

        OrderService service = new LoggingProxy();

        service.createOrder("MacBook Pro", 1);
        System.out.println();

        String order = service.getOrder(1001);
        System.out.println("Query result: " + order);
        System.out.println();

        service.cancelOrder(1001);
    }
}
