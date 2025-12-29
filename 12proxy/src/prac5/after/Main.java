package prac5.after;

// Remote Proxy

// Subject
interface ExchangeRateService {
    String getRate(String currency);
}

// Real Subject
class RealExchangeRateService implements ExchangeRateService {
    @Override
    public String getRate(String currency) {
        return switch (currency) {
            case "USD" -> "1,320.50 KRW";
            case "EUR" -> "1,425.30 KRW";
            case "JPY" -> "8.92 KRW";
            default -> "Unknown currency";
        };
    }
}

// Proxy
class ExchangeRateProxy implements ExchangeRateService {
    private String serverHost;
    private int serverPort;

    public ExchangeRateProxy(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    @Override
    public String getRate(String currency) {
        System.out.println("[Proxy] Connecting to " + serverHost + ":" + serverPort + "...");
        simulateNetworkDelay();

        RealExchangeRateService remoteService = connectToRemoteServer();
        String rate = remoteService.getRate(currency);

        System.out.println("[Proxy] Response received");
        return rate;
    }

    private RealExchangeRateService connectToRemoteServer() {
        return new RealExchangeRateService();
    }

    private void simulateNetworkDelay() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Exchange Rate Service ===\n");

        ExchangeRateService service = new ExchangeRateProxy("api.exchange.com", 443);

        System.out.println("1. USD rate query");
        String usdRate = service.getRate("USD");
        System.out.println("USD rate: " + usdRate);

        System.out.println("\n2. EUR rate query");
        String eurRate = service.getRate("EUR");
        System.out.println("EUR rate: " + eurRate);

        System.out.println("\n3. JPY rate query");
        String jpyRate = service.getRate("JPY");
        System.out.println("JPY rate: " + jpyRate);

        System.out.println("\n=== Benefits ===");
        System.out.println("Client uses it like a local object");
        System.out.println("Network communication encapsulated in proxy");
        System.out.println("Easy to add retry logic, error handling later");
    }
}
