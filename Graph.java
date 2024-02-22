import java.util.ArrayList;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;


public class Graph extends Application{
    public void start(Stage primaryStage) {
        initUI(primaryStage);
    }
    private void initUI(Stage primaryStage) {

        NumberAxis xaxis = new NumberAxis(1975, Runner.predictYear+1, 1);
        xaxis.setLabel("Years");
        NumberAxis yaxis = new NumberAxis();
        yaxis.setLabel("CO2 Emissions (mmt)");
        ScatterChart scatterChart = new ScatterChart(xaxis, yaxis);
        scatterChart.setTitle(Runner.country);
        XYChart.Series dataSeries1 = new XYChart.Series();
        ArrayList<CountryEm> countryyears = CountryEm.makeCountrySpec(Runner.country, Runner.allEntries);
        for (CountryEm countryT : countryyears) {
            dataSeries1.getData().add(new XYChart.Data(countryT.getYear(), countryT.getTotal()));
        }
        scatterChart.getData().add(dataSeries1);
        
        XYChart.Series dataSeries2 = new XYChart.Series();
        for (PredictedCountry pc : Runner.predictedForYear) {
            dataSeries2.getData().add(new XYChart.Data(pc.getYear(), pc.getTotal()));
        }
        scatterChart.getData().add(dataSeries2);


        PredictedCountry target = Runner.predictedForYear.get(Runner.predictedForYear.size()-1);
        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.getData().add(new XYChart.Data(target.getYear(), target.getTotal()));
        scatterChart.getData().add(dataSeries3);
        

        VBox vbox = new VBox(scatterChart);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(500);
        primaryStage.show();
    }
    public static void main(String[] args) {launch(args);}
}
