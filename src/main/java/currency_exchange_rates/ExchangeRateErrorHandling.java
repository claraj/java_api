package currency_exchange_rates;

import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.util.Map;

public class ExchangeRateErrorHandling {

    public static void main(String[] args) {

        // Additions to the basic API request to include error handling for various situations,
        // and suggestions on how to cause errors - it's good to see errors and then you'll
        // be able to troubleshoot this type of error when you see them in other code.
        // and you are very likely to see them - there are many things that can go wrong when making API requests.

        // An example URL, to request the rate of EUR (Euros) against USD (US Dollars),
        // "https://1150-exchange-rates.azurewebsites.net/latest?base=USD&symbols=EUR"
        // Note that this server will take ~30-60 seconds to respond the first time you run the program.
        // or it may time out. If that happens, try again and it should work.
        // Subsequent requests will be much faster.

        String exchangeRateURL = "https://1150-exchange-rates.azurewebsites.net/latest";

        // Try using this line instead - there's a typo in the URL.
        // So the API server returns a "Not Found" message.
        //String exchangeRateURL = "https://1150-exchange-rates.azurewebsites.net/latestPizza";

        // Create a map of query parameter names and value
        Map<String, Object> params = Map.of("base", "USD", "symbols", "EUR");

        // Try replacing the Map above with this one - the symbols parameter is not a valid currency code
        // so the API server returns a "Bad Request" message.
        // Map<String, Object> params = Map.of("base", "USD", "symbols", "PIZZA");


        try {
            Unirest.get(exchangeRateURL)
                    .queryString(params)
                    .asObject(RateData.class)
                    .ifFailure(errorResponse -> {
                        // This is to handle errors when a connection is made to the API server, but the API
                        // server can't or won't process the response. Reasons for these errors include
                        // missing or invalid API key, key not included,
                        // invalid data for keys and values the query parameters,
                        // URL path has a typo in,
                        // the JSON is not in the expected format, or the RateData class doesn't have the right types
                        // for example, the base or date field was accidentally used the int type instead of a String.

                        // Note that if the names don't match the JSON, they will be ignored, so if you expect
                        // to have data in a field but it is null or 0, then check your field names match the JSON properties.
                        // Other JSON processing libraries will error if the types and fields don't match.

                        // Status codes can be helpful figuring out what went wrong.
                        // 400 = Bad request, for example, bad query parameters.
                        //      Some API requests require certain query parameters and will return 400 Bad Request if missing.
                        // 404 = Not Found. Often an error in the URL,
                        // 401 = Unauthorized and 403 = Forbidden.  Usually missing, invalid API keys or
                        //      API keys not sent to the API server in the expected format.
                        System.out.println("Request failed with status code " + errorResponse.getStatus());

                        // Parsing errors happen when a response is received, but the JSON can't
                        // be converted into a Java object.
                        errorResponse.getParsingError().ifPresent(e -> {
                            // Try changing the Rate or Base to int or double to see this error
                            System.out.println("Parsing error encountered " + e);
                            System.out.println("The original response is " + e.getOriginalBody());
                        });
                    })
                    .ifSuccess(successResponse -> {
                        // No error - get the response as a Java object and call a method to use the data.
                        RateData rateData = successResponse.getBody();
                        displayRates(rateData);
                    });
        } catch (UnirestException e) {
            //These errors happen if there's no internet, a network or firewall issue, the API server is down,
            // when a connection can't be made to the API server.  Try disconnecting your internet and you'll see this message.
            System.out.println("Error connecting to the API server because " + e);
        }

    }

    public static void displayRates(RateData rateData) {
        System.out.println("The conversion rate between USD and Euro is " + rateData.rates.EUR);
    }
}


