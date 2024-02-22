import java.util.ArrayList;

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

    public static boolean doesCountryExist(String country, ArrayList<CountryEm> allEntries) {
        for (CountryEm c : allEntries) {
            if (c.name.equals(country) || c.abr.equals(country)) {
                return true;
            }
        }
        return false;
    }
    

    public static ArrayList<CountryEm> makeCountrySpec(String country, ArrayList<CountryEm> allEntries) {
        if (doesCountryExist(country, allEntries)) {
            ArrayList<CountryEm> specToCountry = new ArrayList<CountryEm>();
            for (CountryEm cE : allEntries) {
                if (cE.rightCountry(country) && cE.getTotal() > 0 && cE.getYear()>1975) {
                    specToCountry.add(cE);
                }
            }
            return specToCountry;
        }
        return null;
    }


    public static ArrayList<PredictedCountry> predictedEmission(ArrayList<CountryEm> specToCountry, int year) {
        ArrayList<Double> change = new ArrayList<Double>();
        ArrayList<PredictedCountry> pred = new ArrayList<PredictedCountry>();
        for (int i=0; i<specToCountry.size()-1;i++) {
            //System.out.println(CE.getYear() + ": " + CE.getTotal());
            change.add(specToCountry.get(i+1).getTotal() - specToCountry.get(i).getTotal());
        }

        double avgChange = 0;
        double avgIncreaseInChange = 0;
        ArrayList<Double> changeOfChange = new ArrayList<Double>();
        
        for (int i=0; i<change.size()-1;i++) {
            avgChange += change.get(i);
            changeOfChange.add(change.get(i+1) - change.get(i));
        }
        avgChange += change.get(change.size()-1);
        avgChange /= change.size();

        for (double ch : changeOfChange) {
            avgIncreaseInChange += ch;
        }

        avgIncreaseInChange /= changeOfChange.size();


        avgChange /= change.size();

        System.out.println("The average change per year is " + avgChange + " (mmt) and the average increase in the change is " + avgIncreaseInChange + " (mmt)");

        //System.out.println("What year do you want to predict?");
        //int predictYear = sc.nextInt();

        double predictedForYear = specToCountry.get(specToCountry.size()-1).getTotal();

        for (int y=0; y<year-2021; y++) {
            predictedForYear += avgChange + (avgIncreaseInChange*y);
            pred.add(new PredictedCountry(y+2022, predictedForYear));

        }

        return pred;
    }

}



