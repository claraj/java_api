package async_weather_gui;

import kong.unirest.Unirest;

import javax.swing.*;
import java.awt.*;

/**
 * Created by clara on 2019-09-19.
 *
 *
 * UPDATE This program does not work
 * Dark Sky no longer accepts new API key sign ups
 * This will be replaced with a different API in the future
 *
 */

public class WeatherGUI extends JFrame {
    private JButton getWeatherButton;
    private JPanel mainPanel;
    private JLabel currentConditions;
    
    private static final String DARK_SKY_KEY = System.getenv("DARK_SKY_KEY");   // Make sure this is set
    private static final String DARK_SKY_URL = "https://api.darksky.net/forecast/{api_key}/{lat},{lng}";
    
    WeatherGUI() {
        super("Minneapolis Weather");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(250, 150));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        getWeatherButton.addActionListener(ev -> {

            JOptionPane.showMessageDialog(WeatherGUI.this, "This app will not work, awaiting updates to new API service");

            double lat = 45;
            double lng = -91.3;    // Location of Minneapolis

            currentConditions.setText("Fetching...");
            getWeatherButton.setEnabled(false);

            WeatherWorker worker = new WeatherWorker(lat, lng);
            worker.execute();
            
        });
    }
    
    private void updateWeather(String weather) {
        currentConditions.setText(weather);
        getWeatherButton.setEnabled(true);
    }
    
    class WeatherWorker extends SwingWorker<String, Void> {
    
        double lat;
        double lng;
        
        WeatherWorker(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }
        
        @Override
        protected String doInBackground() throws Exception {
            Weather forecast = Unirest.get(DARK_SKY_URL)
                    .routeParam("api_key", DARK_SKY_KEY)
                    .routeParam("lat", Double.toString(lat))
                    .routeParam("lng", Double.toString(lng))
                    .asObject(Weather.class)
                    .getBody();
            
            Unirest.shutDown();
            return forecast.minutely.summary;
        }
        
        @Override
        protected void done() {
            try {
                String weather = get();
                updateWeather(weather);
            } catch (Exception e) {
                System.out.println("Error because " + e);
            }
        }
    }
    
}
