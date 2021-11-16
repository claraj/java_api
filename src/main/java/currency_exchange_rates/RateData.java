package currency_exchange_rates;

class RateData {
    public Rates rates;
    public String base;
    public String date;

    @Override
    public String toString() {
        return "For the base currency " + base + " on date " + date + ", rate information: " + rates;
    }
}
