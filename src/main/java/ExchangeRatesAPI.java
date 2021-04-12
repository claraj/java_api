import com.google.gson.Gson;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

import java.util.Map;

import static input.InputUtils.doubleInput;

public class ExchangeRatesAPI {
    
    public static void main(String[] args) {

      String exchangeRateURL = "https://api.ratesapi.io/latest";
        
        // Configure Unirest to use Gson to do the JSON -> Java object conversions
        Unirest.config().setObjectMapper(new ObjectMapper() {
            private Gson gson = new Gson();
            @Override
            public <T> T readValue(String s, Class<T> aClass) { return gson.fromJson(s, aClass);
            }
            
            @Override
            public String writeValue(Object o) { return gson.toJson(o);
            }
        });
        
        // Create a map of query parameter names and value
        Map<String, Object> params = Map.of("base", "USD", "symbols", "EUR");
        
        RateData response = Unirest.get(exchangeRateURL)
                .queryString(params)
                .asObject(RateData.class)    // Turn the response into a RateData object
                .getBody();
        
        double dollars = doubleInput("How many dollars to convert to Euros?");
        double dollarsToEuros = response.rates.EUR;
        double euros = dollars * dollarsToEuros;
        System.out.println("The value in Euros is " + euros);
    }
}

class RateData {
    Rates rates;
    String base;
    String date;
}

class Rates {
    double EUR;
}


