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

    public MainWindowFacade(Stage stage) {
        controller = new StockController();
        controller.sort();
        showMainScene(stage);
    }

    private VBox getTopPane() {
        topPane = new VBox();
        DateRangePane datePane = new DateRangePane();
        topPane.setPadding(new Insets(SP_SM, SP_SM, SP_SM, SP_SM));
        topPane.getChildren().addAll(datePane.getPane());

        return topPane;
    }

    private VBox getCenterPane() {
        centerPane = new VBox();
        StockLineChart chart = new StockLineChart();
        StockListView listView = new StockListView();
        chart.setChartValues(controller.sort());
        listView.setListView(controller.sort());
        centerPane.getChildren().addAll(chart.getLineChart(), listView.getListView());

        return centerPane;
    }

    private VBox getBottomPane() {
        bottomPane = new VBox(SP_SM);
        ManualEntryPane entryPane = new ManualEntryPane();
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

        return layout;
    }

    private void showMainScene(Stage stage) {
        this.stage = stage;
        stage.setTitle(TITLE);
        stage.setScene(new Scene(getLayout(), W, H));
        stage.show();
    }

    
}
