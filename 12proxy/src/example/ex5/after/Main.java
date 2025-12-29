package example.ex5.after;

// Remote Proxy

// Subject
interface StockService {
    double getPrice(String symbol);
    void buy(String symbol, int quantity);
    void sell(String symbol, int quantity);
}

// Real Subject
class RealStockService implements StockService {
    @Override
    public double getPrice(String symbol) {
        return switch (symbol) {
            case "AAPL" -> 178.50;
            case "GOOGL" -> 141.80;
            case "TSLA" -> 248.30;
            default -> 0.0;
        };
    }

    @Override
    public void buy(String symbol, int quantity) {
        System.out.println("[Server] " + symbol + " " + quantity + " shares buy processed");
    }

    @Override
    public void sell(String symbol, int quantity) {
        System.out.println("[Server] " + symbol + " " + quantity + " shares sell processed");
    }
}

// Proxy
class RemoteProxy implements StockService {
    private String serverHost;
    private int serverPort;

    public RemoteProxy(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    @Override
    public double getPrice(String symbol) {
        System.out.println("[RemoteProxy] Connecting to " + serverHost + ":" + serverPort + "...");
        simulateNetworkDelay();

        RealStockService remoteService = connectToRemoteServer();
        double price = remoteService.getPrice(symbol);

        System.out.println("[RemoteProxy] Response received");
        return price;
    }

    @Override
    public void buy(String symbol, int quantity) {
        System.out.println("[RemoteProxy] Connecting to " + serverHost + ":" + serverPort + "...");
        simulateNetworkDelay();

        RealStockService remoteService = connectToRemoteServer();
        remoteService.buy(symbol, quantity);

        System.out.println("[RemoteProxy] Buy request complete");
    }

    @Override
    public void sell(String symbol, int quantity) {
        System.out.println("[RemoteProxy] Connecting to " + serverHost + ":" + serverPort + "...");
        simulateNetworkDelay();

        RealStockService remoteService = connectToRemoteServer();
        remoteService.sell(symbol, quantity);

        System.out.println("[RemoteProxy] Sell request complete");
    }

    private RealStockService connectToRemoteServer() {
        return new RealStockService();
    }

    private void simulateNetworkDelay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Remote Proxy for Server Abstraction ===\n");

        StockService stockService = new RemoteProxy("stock.api.com", 8080);

        System.out.println("1. AAPL price query");
        double applePrice = stockService.getPrice("AAPL");
        System.out.println("AAPL current price: $" + applePrice);

        System.out.println("\n2. GOOGL price query");
        double googlePrice = stockService.getPrice("GOOGL");
        System.out.println("GOOGL current price: $" + googlePrice);

        System.out.println("\n3. Buy 10 TSLA shares");
        stockService.buy("TSLA", 10);

        System.out.println("\n4. Sell 5 AAPL shares");
        stockService.sell("AAPL", 5);

        System.out.println("\n=== Improvements ===");
        System.out.println("Client uses it like a local object");
        System.out.println("Network communication encapsulated in proxy");
        System.out.println("Real examples: Java RMI, gRPC Stub, REST Client");
    }
}
