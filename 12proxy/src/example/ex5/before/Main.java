package example.ex5.before;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Direct Remote Server Call ===\n");

        try {
            String serverUrl = "http://api.example.com/stock/AAPL";

            System.out.println("1. Stock price query");
            URL url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
                );
                String response = reader.readLine();
                System.out.println("Response: " + response);
                reader.close();
            }
            conn.disconnect();

            System.out.println("\n2. Another stock query");
            serverUrl = "http://api.example.com/stock/GOOGL";
            url = new URL(serverUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
                );
                String response = reader.readLine();
                System.out.println("Response: " + response);
                reader.close();
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Problems ===");
        System.out.println("HTTP connection code duplicated every time");
        System.out.println("Network logic mixed with business logic");
        System.out.println("Remote call explicitly visible in code");
    }
}
