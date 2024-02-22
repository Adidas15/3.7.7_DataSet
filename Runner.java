import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static ArrayList<CountryEm> allEntries;
    public static String country;
    public static int predictYear;
    public static ArrayList<PredictedCountry> predictedForYear;
    public static void main(String[] args) {
        DataSource ds =DataSource.connectAs("CSV", "emissiondata.csv");
        ds.load();
        //ds.printUsageString();

        allEntries = ds.fetchList("CountryEm", "Country", "ISO 3166-1 alpha-3", "Year", "Total", "Coal","Oil","Gas","Cement","Flaring","Other","Per Capita");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the country you want: ");
        country = sc.nextLine();

        ArrayList<CountryEm> specToCountry = CountryEm.makeCountrySpec(country, allEntries);
        
        
        System.out.println("What year do you want to predict?");
        predictYear = sc.nextInt();

       

        predictedForYear = CountryEm.predictedEmission(specToCountry, predictYear);

        System.out.println(predictedForYear.get(predictedForYear.size()-1) + " million metric tons of CO2 is predicted to be released in the year " + predictYear + " by " + country);

        Graph.main(args);

    }
}


/*for (CountryEm cE : allEntries) {
            if (cE.rightCountry(country) && cE.getTotal() > 0 && cE.getYear()>1975) {
                specToCountry.add(cE);
            }
        }*/

        /*
        ArrayList<Double> change = new ArrayList<Double>();
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

        System.out.println("The average change is " + avgChange + " and the average increase in the change is " + avgIncreaseInChange);
*/


 /*
        double predictedForYear = specToCountry.get(specToCountry.size()-1).getTotal();

        for (int y=0; y<predictYear-2020; y++) {
            predictedForYear += avgChange + (avgIncreaseInChange*y);
        }*/