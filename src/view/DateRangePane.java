
package view;

import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Evan Phillips
 */
public class DateRangePane {
    
    private HBox pane;
    private Label startDateLbl;
    private Label endDateLbl;
    private DatePicker startDate;
    private DatePicker endDate;
    private Button btn;
    
    public DateRangePane() {
        makePane();
    }
    
    private void makePane() {
        pane = new HBox(MainWindowFacade.SP_SM);
        startDateLbl = new Label("Start Date");
        endDateLbl = new Label("End Date");
        startDate = new DatePicker();
        endDate = new DatePicker();
        btn = new Button("Get Prices");
        startDate.setConverter(Converter.getConverter());
        endDate.setConverter(Converter.getConverter());        
        pane.getChildren().addAll(startDateLbl, startDate, endDateLbl, endDate, btn);
        pane.setAlignment(Pos.CENTER);
    }
    
    public HBox getPane() {
        return pane;
    }
    
    public DatePicker getStartDate() {
        return startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public Button getBtn() {
        return btn;
    }
    
    public void clear() {
        
    }
}
