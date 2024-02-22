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
        NumberAxis xaxis = new NumberAxis(1975, 2022, 1);
        xaxis.setLabel("Years");
        NumberAxis yaxis = new NumberAxis();
        yaxis.setLabel("CO2 Emissions (mmt)");
        ScatterChart scatterChart = new ScatterChart(xaxis, yaxis);
        XYChart.Series dataSeries = new XYChart.Series();
        ArrayList<CountryEm> countryyears = CountryEm.makeCountrySpec(Runner.country, Runner.allEntries);
        for (CountryEm countryT : countryyears) {
            dataSeries.getData().add(new XYChart.Data(countryT.getYear(), countryT.getTotal()));
        }
        scatterChart.getData().add(dataSeries);
        VBox vbox = new VBox(scatterChart);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(500);
        primaryStage.show();
    }
    public static void main(String[] args) {launch(args);}
}
