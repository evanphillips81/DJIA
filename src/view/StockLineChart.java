package view;

import controller.StockController;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.*;

/**
 *
 * @author Evan Phillips
 */
public class StockLineChart implements IObserver {

    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<String,Number> lineChart;
    private XYChart.Series series;
    
    public StockLineChart() {
        
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Value");
        lineChart = new LineChart(xAxis,yAxis);    
        lineChart.setTitle("Dow Jones Industrial Average");
        series = new XYChart.Series();
        series.setName("DJIA");
        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
    }
    
    public LineChart<String,Number> getLineChart() {
        return lineChart;
    }
    
    public void setChartValues(ArrayList<StockRecord> list) {
        lineChart.getData().removeAll();
        for (int i = 0; i < list.size(); i++) {
            series.getData().add(new XYChart.Data(list.get(i).getDate().toString(),
                    list.get(i).getValue()));
        }
    }
    
    public void clear() {
        lineChart.getData().removeAll();
    }

    @Override
    public void update(ArrayList<StockRecord> newList) {
        setChartValues(newList);
    }

}
