package view;

import controller.StockController;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.StockRecord;

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

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
