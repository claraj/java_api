package yelp;

import java.util.Map;
import kong.unirest.Unirest;

/**
 * Created by clara on 2019-09-25.
 */
public class YelpSearchMapParameters {
    
    public static void main(String[] args) {

        String yelpUrl = "https://api.yelp.com/v3/businesses/search";

        String YELP_API_KEY = System.getenv("YELP_API_KEY");

        String query = "pizza";  // or the search term of your choice

        Map<String, Object> searchParameters = Map.of("term", query,
                "categories", "restaurants",  // see Yelp documentation for list of categories
                "location", "Minneapolis,MN",
                "radius", 10000,    // 10000 meters = 10 kilometers = ~ 6 miles
                "price", 1,    // Lowest price bracket
                "limit", 10 );   // Number of results to return, up to 50

        YelpResponse response = Unirest.get(yelpUrl)
                .header("Authorization", "Bearer " + YELP_API_KEY)
                .queryString(searchParameters)
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






