package prac3.after;

import java.util.HashMap;
import java.util.Map;

// Caching Proxy

// Subject
interface ProductService {
    String getProduct(int productId);
}

// Real Subject
class RealProductService implements ProductService {
    @Override
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

// Proxy
class ProxyProductService implements ProductService{
    private RealProductService realProductService;
    private Map<Integer,Integer> cache;

    public ProxyProductService(){
        realProductService = new RealProductService();
        cache = new HashMap<Integer, Integer>();
    }

    private boolean isInCache(int id){
        if(cache.containsKey(id)) return true;
        return false;
    }

    @Override
    public String getProduct(int productId) {
        if(isInCache(productId)){
            return "Product_" + productId + " (Price: $" + cache.get(productId) + ")";
        }
        else{
            cache.put(productId,productId * 10);
             return realProductService.getProduct(productId);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Shopping Mall Product Query ===\n");

        ProductService service = new ProxyProductService();

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

    }
}
