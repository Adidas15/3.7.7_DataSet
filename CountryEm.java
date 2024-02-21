public class CountryEm {

    private String name;
    private String abr;
    private int year;
    private double total;
    private double coal;
    private double oil;
    private double gas;
    private double cement;
    private double flaring;
    private double other;
    private double perCapita;
    
    public CountryEm(String name, String abr, int year, double total, double coal, double oil, double gas, double cement, double flaring, double other, double perCapita) {
        this.name = name;
        this.abr = abr;
        this.year = year;
        this.total = total;
        this.coal = coal;
        this.oil = oil;
        this.gas = gas;
        this.cement = cement;
        this.flaring = flaring;
        this.other = other;
        this.perCapita = perCapita;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public double getTotal() {
        return total;
    }

    public boolean rightCountry(String c) {
        return (name.equals(c) || abr.equals(c));
    }
}
