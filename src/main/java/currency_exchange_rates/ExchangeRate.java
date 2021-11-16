package currency_exchange_rates;

import kong.unirest.Unirest;

public class ExchangeRate {
    
    public static void main(String[] args) {
    
        // An example URL, to request the rate of EUR (Euros) against USD (US Dollars),
        // "https://exchange-rates-1150.herokuapp.com/latest?base=USD&symbols=EUR"
        // Note that this server will take ~30 seconds to respond the first time you run the program.
        // Subsequent requests will be much faster.

        String exchangeRateURL = "https://exchange-rates-1150.herokuapp.com/latest?base=USD&symbols=EUR";

        RateData response = Unirest.get(exchangeRateURL)
                .asObject(RateData.class)    // Turn the response into a RateData object
                .getBody();
    
        System.out.println("The conversion rate between USD and Euro is " + response.rates.EUR);
    }
}


