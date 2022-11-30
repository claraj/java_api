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
    * The response is a JSON object. One of the keys, "departures"
    * contains an array of objects, so when deserializing, the data,
    * it is converted into an array of BusStatus objects.
    *
    * {
        "stops": [
            {
                "stop_id": 17940,
                "latitude": 44.973,
                "longitude": -93.284325,
                "description": "Hennepin Ave & MCTC"
            }
        ],
        "alerts": [],
        "departures": [
            {
                "actual": true,
                "trip_id": "22197530-AUG22-MVS-BUS-Weekday-01",
                "stop_id": 17940,
                "departure_text": "4 Min",
                "departure_time": 1669847560,
                "description": "Downtown / Via Lyndale",
                "route_id": "4",
                "route_short_name": "4",
                "direction_id": 0,
                "direction_text": "NB",
                "schedule_relationship": "Scheduled"
            },
            {
                "actual": true,
                "trip_id": "22197531-AUG22-MVS-BUS-Weekday-01",
                "stop_id": 17940,
                "departure_text": "4 Min",
                "departure_time": 1669847571,
                "description": "Johnson St / Silver Lk Vlg / Via Penn",
                "route_id": "4",
                "route_short_name": "4",
                "direction_id": 0,
                "direction_text": "NB",
                "terminal": "B",
                "schedule_relationship": "Scheduled"
            },

            ... more buses ...
            ]
        }
    *
    * */
    
    public static void main(String[] args) {

        // This URL is a request for the bus departures from stop number 17940, which
        // is the stop on Hennepin Avenue for buses traveling north.
        String metroTransitURL = "http://svc.metrotransit.org/NexTrip/17940?format=json";

        MetroTransitResponse response = Unirest.get(metroTransitURL).asObject(MetroTransitResponse.class).getBody();

        BusStatus[] busStatuses = response.departures;

        String busTableTemplate = "%-10s%-40s%-20s\n";

        // Table header
        System.out.printf(busTableTemplate, "Route", "Description", "Arrival Time");
        System.out.println("=".repeat(70));

        // Read information about each bus, display in table form

        for (BusStatus bus: busStatuses) {
            System.out.printf(busTableTemplate, bus.route_id, bus.description, bus.departure_text);
        }
    }
}

class MetroTransitResponse {
    BusStatus[] departures;
}
class BusStatus {
    public String departure_text;
    public String description;
    public String route_id;
}