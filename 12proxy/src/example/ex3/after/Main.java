package example.ex3.after;

import java.util.HashMap;
import java.util.Map;

// Caching Proxy

// Subject
interface WeatherService {
    String getWeather(String city);
}

// Real Subject
class RealWeatherService implements WeatherService {
    @Override
    public String getWeather(String city) {
        System.out.println("[API Call] Fetching weather for " + city + "... (2 sec)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return city + ": Sunny, 25°C";
    }
}

// Proxy
class CachingProxy implements WeatherService {
    private RealWeatherService realService;
    private Map<String, String> cache;
    private Map<String, Long> cacheTime;
    private static final long CACHE_DURATION = 5000;

    public CachingProxy() {
        this.realService = new RealWeatherService();
        this.cache = new HashMap<>();
        this.cacheTime = new HashMap<>();
    }

    @Override
    public String getWeather(String city) {
        if (isCacheValid(city)) {
            System.out.println("[Cache Hit] " + city + " returning cached data");
            return cache.get(city);
        }

        System.out.println("[Cache Miss] " + city);
        String result = realService.getWeather(city);
        cache.put(city, result);
        cacheTime.put(city, System.currentTimeMillis());
        return result;
    }

    private boolean isCacheValid(String city) {
        if (!cache.containsKey(city)) {
            return false;
        }
        long elapsed = System.currentTimeMillis() - cacheTime.get(city);
        return elapsed < CACHE_DURATION;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Caching Proxy for API Optimization ===\n");

        WeatherService service = new CachingProxy();

        System.out.println("1. Seoul weather (first)");
        System.out.println("Result: " + service.getWeather("Seoul"));

        System.out.println("\n2. Busan weather (first)");
        System.out.println("Result: " + service.getWeather("Busan"));

        System.out.println("\n3. Seoul weather (second - cached)");
        System.out.println("Result: " + service.getWeather("Seoul"));

        System.out.println("\n4. Seoul weather (third - cached)");
        System.out.println("Result: " + service.getWeather("Seoul"));

        System.out.println("\n=== Improvements ===");
        System.out.println("Seoul queried 3 times but API called only once");
        System.out.println("Response time: 2 sec -> instant (on cache hit)");
    }
}
