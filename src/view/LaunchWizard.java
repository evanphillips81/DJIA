
package view;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Evan Phillips
 */
public class LaunchWizard extends Scene {
    
    private Stage stage;
    private VBox vb;
    private Button loadBtn;
    private Button exitBtn;
    private StockFileChooser fc;
    private MainWindowFacade mainWindowFacade;
    
    public LaunchWizard(Stage stage) throws FileNotFoundException {
        super(new VBox(), 150, 300);
        this.stage = stage;
        makeNewObjects();
        show();
    }
    
    private void makeNewObjects() throws FileNotFoundException {
        vb = (VBox)getRoot();
        stage = new Stage();
        loadBtn = new Button("Load CSV");
        exitBtn = new Button("Exit app");       
    }
    
    private void actions() {
        loadBtn.setOnAction(e -> {
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
        stage.setTitle("Welcome");
        stage.setScene(this);
        stage.initModality(Modality.APPLICATION_MODAL);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(loadBtn, exitBtn);
        stage.show();
    }
    
    private void loadMainWindowFacade(Stage stage) throws FileNotFoundException {
        mainWindowFacade = new MainWindowFacade(stage, fc.getFile());
    }
}
