package yelp;

/**
 * Created by clara on 2019-09-20.
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
