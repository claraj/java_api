package api_query_bored_data;

import com.google.gson.Gson;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

import java.util.Map;

/**
 * Created by clara on 2019-09-18.
 */
public class BoredData {
    
    public static void main(String[] args) {

        /*
        * https://www.boredapi.com/documentation#endpoints-type
        *
        */
        
        
        String boredURL = "http://www.boredapi.com/api/activity";
        
        // Configure Unirest to use Gson to do the JSON -> Java object conversions
        // Only need to do this one time.
        Unirest.config().setObjectMapper(new ObjectMapper() {
            private Gson gson = new Gson();
            @Override
            public <T> T readValue(String s, Class<T> aClass) {
                return gson.fromJson(s, aClass);
            }
            
            @Override
            public String writeValue(Object o) {
                return gson.toJson(o);
            }
        });
        
        // Create a map of query parameter names and value
        // Here, want to search for free, recreational activities
        
        Map<String, Object> params = Map.of("price", 0.0, "type", "recreational");
        
        Activity response = Unirest.get(boredURL)
                .queryString(params)
                .asObject(Activity.class)    // Turn the response into a RateData object
                .getBody();
        
        System.out.println("An activity suggestion is " + response.activity);
        System.out.println("You will need this many participants " + response.participants);
    }
}


class Activity {
    String activity;

    // Accessibility On a 0-1 scale.  0 is very accessible with minimal resources, for example
    // time/supplies/physical ability
    String accessibility;

    String type;
    String participants;
    float price;

    @Override
    public String toString() {
        return "Activity{" +
                "activity='" + activity + '\'' +
                ", accessibility='" + accessibility + '\'' +
                ", type='" + type + '\'' +
                ", participants='" + participants + '\'' +
                ", price=" + price +
                '}';
    }
}