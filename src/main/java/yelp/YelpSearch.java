package yelp;

import com.google.gson.Gson;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

/**
 * Created by clara on 2019-09-25.
 */
public class YelpSearch {
    
    public static void main(String[] args) {
    
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
    
    
        String YELP_URL = "https://api.yelp.com/v3/businesses/search";
    
        String YELP_API_KEY = System.getenv("YELP_API_KEY");  // make sure this is set
    
        String query = "pizza";
    
        YelpResponse response = Unirest.get(YELP_URL)
                .header("Authorization", "Bearer " + YELP_API_KEY)
                .queryString("term", query)
                .queryString("categories", "restaurants")
                .queryString("location", "Minneapolis,MN")
                .queryString("radius", 10000)    // 10000 meters = 10 kilometers = ~ 6 miles
                .queryString("price", 1)    // Lowest price bracket
                .queryString("limit", 50)   // max number API permits in one call
                .asObject(YelpResponse.class)
                .getBody();
    
        Business[] businesses = response.businesses;

        if (businesses != null) {
            for (Business b : businesses) {
                System.out.println(b.name + ": " + b.location.address1 + ", " + b.location.city);
            }
        }
        else {
            System.out.println("No results returned. Check API key and query parameters");
        }
    }
}
