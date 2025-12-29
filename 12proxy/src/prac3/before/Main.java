package prac3.before;

class ProductService {
    public String getProduct(int productId) {
        System.out.println("[DB Query] Product " + productId + " loading... (1 sec)");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Product_" + productId + " (Price: $" + (productId * 10) + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Shopping Mall Product Query ===\n");

        ProductService service = new ProductService();

        System.out.println("1. Product 101 query");
        System.out.println("Result: " + service.getProduct(101));

        System.out.println("\n2. Product 102 query");
        System.out.println("Result: " + service.getProduct(102));

        System.out.println("\n3. Product 101 again");
        System.out.println("Result: " + service.getProduct(101));

        System.out.println("\n4. Product 101 again");
        System.out.println("Result: " + service.getProduct(101));

        System.out.println("\n5. Product 101 again");
        System.out.println("Result: " + service.getProduct(101));

        System.out.println("\n=== Problems ===");
        System.out.println("Product 101 queried 4 times = 4 DB calls = 4 sec wasted");
        System.out.println("Popular products queried often but DB hit every time");
    }
}
