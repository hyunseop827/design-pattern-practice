package prac1.after;

// Pull-based Observer Pattern

import java.util.ArrayList;
import java.util.List;

// Observer - defines an updating interface for objects that should be notified of changes in a Subject
interface Observer {
    void update();
}

// ConcreteObserver - maintains a reference to a ConcreteSubject and implements the Observer updating interface
class CurrentConditionsDisplay implements Observer {
    private final WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public void update() {
        System.out.println("Current conditions: " +
                weatherData.getTemperature() +
                "F degrees and " +
                weatherData.getHumidity() + "% humidity");
    }
}

// ConcreteObserver
class StatisticsDisplay implements Observer {
    private final WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public void update() {
        System.out.println("Avg/Max/Min temperature = " +
                weatherData.getTemperature());
    }
}

// ConcreteObserver
class ForecastDisplay implements Observer {

    @Override
    public void update() {
        System.out.println("Forecast: Watch out for cooler weather");
    }
}

// Subject - knows its observers and provides an interface for attaching Observer objects
abstract class SubjectData {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// ConcreteSubject - stores state of interest to ConcreteObserver objects
class WeatherData extends SubjectData {
    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.notifyObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Observer condition = new CurrentConditionsDisplay(weatherData);
        Observer statics = new StatisticsDisplay(weatherData);
        Observer forecast = new ForecastDisplay();

        weatherData.addObserver(condition);
        weatherData.addObserver(statics);
        weatherData.addObserver(forecast);

        System.out.println("--- First Update ---");
        weatherData.setMeasurements(80, 65, 30.4f);

        System.out.println("\n--- Second Update ---");
        weatherData.setMeasurements(82, 70, 29.2f);
    }
}
