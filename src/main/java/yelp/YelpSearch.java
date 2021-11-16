package yelp;
import kong.unirest.Unirest;

public class YelpSearch {
    
    public static void main(String[] args) {

        String yelpUrl = "https://api.yelp.com/v3/businesses/search";

        final String YELP_API_KEY = System.getenv("YELP_API_KEY");

        if (YELP_API_KEY == null) {
            System.out.println("No API key set. Set an environment variable with the name YELP_API_KEY and the value of your Yelp key ");
            System.exit(0);  // close program - developer needs to fix the environment variable
        }

        String query = "pizza";  // or the search term of your choice, or ask the user to enter a search term
    
        YelpResponse response = Unirest.get(yelpUrl)
                .header("Authorization", "Bearer " + YELP_API_KEY)
                .queryString("term", query)
                .queryString("categories", "restaurants") // see Yelp documentation for list of categories
                .queryString("location", "Minneapolis,MN")
                .queryString("radius", 10000)    // 10000 meters = 10 kilometers = ~ 6 miles
                .queryString("price", 1)    // Lowest price bracket
                .queryString("limit", 10)   // Number of results to return, up to 50
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






