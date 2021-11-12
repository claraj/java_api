package yelp;

/**
 * Classes for mapping the JSON response to Java objects.
 */

public class YelpResponse {
    public Business[] businesses;
}

class Business {
    public double rating;
    public String name;
    public Location location;

}

class Location {
    public String city;
    public String address1;

}
