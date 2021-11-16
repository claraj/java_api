package metro_transit;

import kong.unirest.Unirest;

/**
 * Created by clara on 2019-09-18.
 */
public class MetroTransit {
    
    /*
    *  Use the Metro Transit API to get the next bus departures for the stop outside
    * Minneapolis College.
    *
    * The response is a JSON array of objects, so when deserializing, the data,
    * it is converted into an array of BusStatus objects.
    *
    * */
    
    public static void main(String[] args) {

        // This URL is a request for the bus departures from stop number 17940, which
        // is the stop on Hennepin Avenue for buses traveling north.
        String metroTransitURL = "http://svc.metrotransit.org/NexTrip/17940?format=json";

        BusStatus[] busStatuses = Unirest.get(metroTransitURL).asObject(BusStatus[].class).getBody();

        String busTableTemplate = "%-10s%-40s%-20s\n";

        // Table header
        System.out.printf(busTableTemplate, "Route", "Description", "Arrival Time");
        System.out.println("=".repeat(70));

        // Read information about each bus, display in table form
        for (BusStatus bus: busStatuses) {
            System.out.printf(busTableTemplate, bus.Route, bus.Description, bus.DepartureText);
        }
    }
}

class BusStatus {
    public String VehicleLongitude;
    public String DepartureText;
    public String DepartureTime;
    public String RouteDirection;
    public String Description;
    public String VehicleLatitude;
    public String Actual;
    public String Gate;
    public String BlockNumber;
    public String Terminal;
    public String Route;
    public String VehicleHeading;
    
    @Override
    public String toString()
    {
        return "BusStatus [VehicleLongitude = "+VehicleLongitude+", DepartureText = "+DepartureText+", DepartureTime = "+DepartureTime+", RouteDirection = "+RouteDirection+", Description = "+Description+", VehicleLatitude = "+VehicleLatitude+", Actual = "+Actual+", Gate = "+Gate+", BlockNumber = "+BlockNumber+", Terminal = "+Terminal+", Route = "+Route+", VehicleHeading = "+VehicleHeading+"]";
    }
}