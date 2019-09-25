import java.util.Arrays;

/**
 * Created by clara on 2019-09-20.
 */
public class YelpResponse {
    public Business[] businesses;
    
    @Override
    public String toString() {
        return "YelpResponse{" +
                "businesses=" + Arrays.toString(businesses) +
                '}';
    }
}

class Business {
    public double rating;
    public String name;
    public Location location;
    
    @Override
    public String toString() {
        return "Business{" +
                "rating=" + rating +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}

class Location {
    public String city;
    public String address1;
    
    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", address1='" + address1 + '\'' +
                '}';
    }
}
