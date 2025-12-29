package prac4.before;

class OrderService {
    public void createOrder(String item, int quantity) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] createOrder() start - item: " + item + ", quantity: " + quantity);

        System.out.println("Order created: " + item + " x" + quantity);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] createOrder() end - elapsed: " + elapsed + "ms");
    }

    public String getOrder(int orderId) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] getOrder() start - orderId: " + orderId);

        String result = "Order_" + orderId;

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] getOrder() end - return: " + result + ", elapsed: " + elapsed + "ms");
        return result;
    }

    public void cancelOrder(int orderId) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] cancelOrder() start - orderId: " + orderId);

        System.out.println("Order cancelled: " + orderId);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] cancelOrder() end - elapsed: " + elapsed + "ms");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Order Service ===\n");

        OrderService service = new OrderService();

        service.createOrder("MacBook Pro", 1);
        System.out.println();

        String order = service.getOrder(1001);
        System.out.println("Query result: " + order);
        System.out.println();

        service.cancelOrder(1001);

        System.out.println("\n=== Problems ===");
        System.out.println("Logging code duplicated in every method");
        System.out.println("Business logic mixed with logging - poor readability");
        System.out.println("Changing log format requires modifying all methods");
    }
}
