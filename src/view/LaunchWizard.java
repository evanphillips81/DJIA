
package view;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Evan Phillips
 */
public class LaunchWizard extends Scene {
    
    static final double W = 400;
    static final double H = 550;
    static final double SP = 25;
    static final Label info = new Label("Historical Stock Prices Verson 2.0 2016\n"
            + "\t\t    By Evan Phillips");
    private Stage stage;
    private VBox vb;
    private Button openBtn;
    private Button exitBtn;
    private StockFileChooser fc;
    private MainWindowFacade mainWindowFacade;
    
    public LaunchWizard(Stage stage) throws FileNotFoundException {
        super(new VBox(SP), W, H);
        this.stage = stage;
        makeNewObjects();
        show();
    }
    
    private void makeNewObjects() throws FileNotFoundException {
        vb = (VBox)getRoot();
        stage = new Stage();
        openBtn = new Button();
        exitBtn = new Button();       
    }
    
    private void actions() {
        openBtn.setOnAction(e -> {
            try {
                fc = new StockFileChooser();
                loadMainWindowFacade(stage);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LaunchWizard.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        exitBtn.setOnAction(e -> {
            stage.close();
        });
    }
    
    private void show() {
        actions();
        this.getStylesheets().add("superCool.css");
        openBtn.setGraphic(new ImageView(new Image("/openPlain.png")));
        exitBtn.setGraphic(new ImageView(new Image("/exitPlain.png")));
        openBtn.setTooltip(new Tooltip("Open a file for historic data"));
        exitBtn.setTooltip(new Tooltip("Exit"));
        stage.setTitle("Welcome");
        stage.setScene(this);
        stage.getIcons().add(new Image("stocks.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(new Label("Open"),openBtn, new Label("Exit"), exitBtn, info);
        stage.show();
    }
    
    private void loadMainWindowFacade(Stage stage) throws FileNotFoundException {
        mainWindowFacade = new MainWindowFacade(stage, fc.getFile());
    }
}
