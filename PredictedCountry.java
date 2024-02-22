public class PredictedCountry {
    private int year;
    private double total;

    public PredictedCountry(int year, double total) {
        this.year = year;
        this.total = total;
    }

    public int getYear() {
        return year;
    }

    public double getTotal() {
        return total;
    }

    public String toString() {
        return "" + total;
    }

}
