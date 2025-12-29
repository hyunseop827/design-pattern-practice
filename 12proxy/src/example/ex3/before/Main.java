package example.ex3.before;

class WeatherService {
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

public class Main {
    public static void main(String[] args) {
        System.out.println("=== No Caching - API Called Every Time ===\n");

        WeatherService service = new WeatherService();

        System.out.println("1. Seoul weather");
        System.out.println("Result: " + service.getWeather("Seoul"));

        System.out.println("\n2. Busan weather");
        System.out.println("Result: " + service.getWeather("Busan"));

        System.out.println("\n3. Seoul weather (again!)");
        System.out.println("Result: " + service.getWeather("Seoul"));

        System.out.println("\n4. Seoul weather (again!!)");
        System.out.println("Result: " + service.getWeather("Seoul"));

        System.out.println("\n=== Problems ===");
        System.out.println("Seoul queried 3 times = 3 API calls = 6 sec wasted");
        System.out.println("Same data but network cost every time");
    }
}
