
package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.StockRecordMap;

/**
 *
 * @author Evan Phillips
 */
public class StockLineChart implements Observer {
    
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<String,Number> lineChart;
    private XYChart.Series series;
    
    public StockLineChart() {
        makeChart();
    }
    
    private void makeChart() {
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Dollar Value");
        lineChart = new LineChart(xAxis,yAxis);    
        lineChart.setTitle("Dow Jones Industrial Average");
        series = new XYChart.Series();
        series.setName("DJIA");
        lineChart.getData().add(series); 
    }
    
    public LineChart<String,Number> getChart() {
        return lineChart;
    }
    
    public void setChartValues(StockRecordMap map) {
        lineChart.getData().removeAll();
        for (int i = 0; i < map.getSize(); i++) {
            series.getData().add(new XYChart.Data(map.get(i),
                    map.get(i)));
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        
    }
    
}
