package prac5.before;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Exchange Rate Service ===\n");

        try {
            System.out.println("1. USD rate query");
            String serverUrl = "http://api.exchange.com/rate/USD";
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
                System.out.println("USD rate: " + response);
                reader.close();
            }
            conn.disconnect();

            System.out.println("\n2. EUR rate query");
            serverUrl = "http://api.exchange.com/rate/EUR";
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
                System.out.println("EUR rate: " + response);
                reader.close();
            }
            conn.disconnect();

            System.out.println("\n3. JPY rate query");
            serverUrl = "http://api.exchange.com/rate/JPY";
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
                System.out.println("JPY rate: " + response);
                reader.close();
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Problems ===");
        System.out.println("HTTP connection code duplicated every time");
        System.out.println("Network logic mixed with business logic");
        System.out.println("Remote API call exposed in code");
    }
}
