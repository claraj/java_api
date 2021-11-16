package weather_async_gui;

import kong.unirest.Unirest;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Open Weather Map GUI Program
 * Sign up for an account and create a key at https://home.openweathermap.org/api_keys
 * Create an environment variable with the name = OPEN_WEATHER_MAP_KEY and value = your API Key
 *
 * Documentation: https://openweathermap.org/current
 */

public class WeatherGUI extends JFrame {
    private JButton getWeatherButton;
    private JPanel mainPanel;
    private JTextArea currentConditions;

    private static final String WEATHER_KEY = System.getenv("OPEN_WEATHER_MAP_KEY");   // Make sure this is set
    private static final String WEATHER_FORECAST_URL = "https://api.openweathermap.org/data/2.5/weather";


    WeatherGUI() {
        super("Minneapolis Weather");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(350, 200));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        getWeatherButton.addActionListener(ev -> {

            currentConditions.setText("Fetching...");
            getWeatherButton.setEnabled(false);

            WeatherWorker worker = new WeatherWorker("Minneapolis,MN,US");
            worker.execute();
            
        });
    }
    
    private void updateWeather(WeatherResponse weather) {
        String weatherText = String.format("The current conditions are %s. " +
                        "The temperature is %.2f Celsius and the wind speed is %.2f km/hour.",
                weather.weather[0].description, weather.main.temp, weather.wind.speed);
        currentConditions.setText(weatherText);
        getWeatherButton.setEnabled(true);
    }
    
    class WeatherWorker extends SwingWorker<WeatherResponse, Void> {
    
        String location;
        
        WeatherWorker(String location) {
            this.location = location;
        }
        
        @Override
        protected WeatherResponse doInBackground(){

            Map<String, Object> params = Map.of(
                    "q", location,
                    "units", "metric",   // Otherwise we get Kelvin for temperature which is not so readable for most people.
                    "appid", WEATHER_KEY);

            WeatherResponse currentConditions = Unirest.get(WEATHER_FORECAST_URL)
                    .queryString(params)
                    .asObject(WeatherResponse.class)
                    .getBody();
            
            Unirest.shutDown();
            return currentConditions;
        }
        
        @Override
        protected void done() {
            try {
                WeatherResponse weather = get();
                updateWeather(weather);
            } catch (Exception e) {
                System.out.println("Error because " + e);
            }
        }
    }
    
}
