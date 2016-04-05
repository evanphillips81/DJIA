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

    static final double W = 650;
    static final double H = 650;
    static final double SP_SM = 5;
    static final double SP_LG = 15;
    static final String PATTERN = "yyyy-MM-dd";
    static final String TITLE = "Dow Jones Industrial Average";
    static  StringConverter sc;
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
        StockLineChart chart = new StockLineChart();
        StockListView listView = new StockListView();
        chart.setChartValues(controller.sort());
        listView.setListView(controller.sort());
        topPane.getChildren().addAll(chart.getLineChart(), listView.getListView());

        return topPane;
    }

    private VBox getCenterPane() {
        centerPane = new VBox();

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
    
    static StringConverter getConverter() {
        sc = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter
                    = DateTimeFormatter.ofPattern(PATTERN);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        
        return sc;
    }
}
