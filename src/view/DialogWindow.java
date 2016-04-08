
package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Evan Phillips
 */
public class DialogWindow extends Scene {
    
    static final double W = 250;
    static final double H = 150;
    private Stage stage;
    private VBox vb;
    private Label text;
    private Button button;
    private String title; 
    private String message;
    
    public DialogWindow(String title, String message) {
        super(new VBox(MainWindowFacade.SP_LG), W, H);
        this.title = title;
        this.message = message;
        makeNewObjects();
        handleAction();
        showDialog();
    }
    
    private void makeNewObjects() {
        stage = new Stage();
        text = new Label(message);
        button = new Button("OK");
        vb = (VBox)getRoot();
    }

    private void showDialog() {
        stage.setTitle(title);
        stage.setScene(this);
        stage.initModality(Modality.APPLICATION_MODAL);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(text, button);
        stage.show();
    }
    
    public Button getButton() {
        return button;
    }
 
    public void handleAction() {
        button.setOnAction(e -> {
            buttonClicked();
        });
    }
    
    public void buttonClicked() {
        stage.close();
    }
}
