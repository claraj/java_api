package api_query_currency;

import java.util.Map;
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
    
        System.out.println("The conversion rate between USD and Euro is " + response.getRates().getEUR());
    }
}


class RateData {
    private Rates rates;
    private String base;
    private String date;
    
    public Rates getRates() {
        return rates;
    }
    
    public void setRates(Rates ratesOb) {
        this.rates = ratesOb;
    }
    
    public String getBase() {
        return base;
    }
    
    public void setBase(String base) {
        this.base = base;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "RateData{" +
                "rates=" + rates+
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

class Rates {
    private String EUR;
    
    public String getEUR() {
        return EUR;
    }
    
    public void setEUR(String EUR) {
        this.EUR = EUR;
    }
    
    @Override
    public String toString() {
        return "rates{" +
                "EUR='" + EUR + '\'' +
                '}';
    }
}