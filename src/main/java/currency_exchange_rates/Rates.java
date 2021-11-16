package currency_exchange_rates;

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
