package weather_async_gui;


public class WeatherResponse {

    Weather[] weather;    // weather object is an array with one thing in. The object contains a string describing current conditions
    Temperature main;    // main object contains the current temperature. min and max temps, and humidity
    Wind wind;        // contains what you think it would

}

class Weather {
    String description;
}

class Temperature {
    double temp;
}

class Wind {
    double speed;
}