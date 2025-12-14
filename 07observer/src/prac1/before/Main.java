package prac1.before;

class CurrentConditionsDisplay {
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

class StatisticsDisplay {
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Avg/Max/Min temperature = " + temperature);
    }
}

class ForecastDisplay {
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Forecast: Watch out for cooler weather");
    }
}

class WeatherData {
    private CurrentConditionsDisplay currentDisplay;
    private StatisticsDisplay statisticsDisplay;
    private ForecastDisplay forecastDisplay;

    public WeatherData() {
        this.currentDisplay = new CurrentConditionsDisplay();
        this.statisticsDisplay = new StatisticsDisplay();
        this.forecastDisplay = new ForecastDisplay();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        currentDisplay.update(temperature, humidity, pressure);
        statisticsDisplay.update(temperature, humidity, pressure);
        forecastDisplay.update(temperature, humidity, pressure);
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        System.out.println("--- First Update ---");
        weatherData.setMeasurements(80, 65, 30.4f);

        System.out.println("\n--- Second Update ---");
        weatherData.setMeasurements(82, 70, 29.2f);
    }
}