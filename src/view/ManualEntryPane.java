package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author Evan Phillips
 */
public class ManualEntryPane {

    private DatePicker date;
    private HBox pane;
    private Label valueLbl;
    private TextField valueTf;
    private Button submitBtn;

    public ManualEntryPane() {
        makePane();
    }

    private void makePane() {
        pane = new HBox(MainWindowFacade.SP_SM);
        date = new DatePicker();
        valueLbl = new Label("Value ");
        valueTf = new TextField();
        submitBtn = new Button("Submit");
        valueTf.setPromptText("Enter the value");
        date.setConverter(Converter.getConverter());
        pane.getChildren().addAll(date, valueLbl, valueTf, submitBtn);
        pane.setAlignment(Pos.CENTER);
    }
    
    public HBox getPane() {
        return pane;
    }
}
