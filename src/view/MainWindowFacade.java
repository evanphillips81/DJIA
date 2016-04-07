package view;

import controller.StockController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.StockRecord;

/**
 *
 * @author Evan Phillips
 */
public class MainWindowFacade {

    static final double W = 1200;
    static final double H = 800;
    static final double SP_SM = 10;
    static final double SP_LG = 20;
    static final String PATTERN = "yyyy-MM-dd";
    static final String TITLE = "Dow Jones Industrial Average";
    static StringConverter sc;
    private StockController controller;
    private Stage stage;
    private BorderPane layout;
    private VBox topPane;
    private VBox centerPane;
    private VBox bottomPane;
    private StockLineChart chart;
    private StockListView listView;
    private DateRangePane datePane;
    private ManualEntryPane entryPane;

    public MainWindowFacade(Stage stage) {
        controller = new StockController();
        controller.getArrayList();

        showMainScene(stage);
    }

    private VBox getTopPane() {
        topPane = new VBox();
        datePane = new DateRangePane();
        topPane.setPadding(new Insets(SP_SM, SP_SM, SP_SM, SP_SM));
        topPane.getChildren().addAll(datePane.getPane());

        return topPane;
    }

    private VBox getCenterPane() {
        centerPane = new VBox();
        chart = new StockLineChart();
        listView = new StockListView();
        centerPane.getChildren().addAll(chart.getLineChart(), listView.getListView());

        return centerPane;
    }

    private VBox getBottomPane() {
        bottomPane = new VBox(SP_SM);
        entryPane = new ManualEntryPane();
        bottomPane.setPadding(new Insets(SP_SM, SP_SM, SP_SM, SP_SM));
        bottomPane.getChildren().add(entryPane.getPane());

        return bottomPane;
    }

    private BorderPane getLayout() {
        layout = new BorderPane();
        layout.setPadding(new Insets(SP_SM, SP_SM, SP_SM, SP_SM));
        layout.setTop(getTopPane());
        layout.setCenter(getCenterPane());
        layout.setBottom(getBottomPane());
        actions();

        return layout;
    }

    private void showMainScene(Stage stage) {
        this.stage = stage;
        stage.setTitle(TITLE);
        stage.setScene(new Scene(getLayout(), W, H));
        stage.show();
    }

    private void refreshNodes() {
        chart.clear();
        listView.clear();
    }
    
    private void dateButtonClicked() {
        refreshNodes();
        LocalDate start = datePane.getStartDate().getValue();
        LocalDate end = datePane.getEndDate().getValue();
        chart.setChartValues(controller.getDateRange(start, end));
        listView.setListView(controller.getDateRange(start, end));
    }
    
    private void submitButtonClicked() {
        
        try {
            LocalDate date = entryPane.getDate().getValue();
            double value = Double.parseDouble(entryPane.getValueTf().getText());
            controller.add(date, value);
        } catch (NumberFormatException e) {
            
        }
        
    }
    
    private void actions() {
        datePane.getBtn().setOnAction(e -> {
            dateButtonClicked();
        });
        
        entryPane.getSubmitBtn().setOnAction(e -> {
            submitButtonClicked();
        });
    }

}
