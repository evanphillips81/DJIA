
package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.LaunchWizard;
import view.MainWindowFacade;

/**
 *
 * @author Evan Phillips
 */
public class StockPricesDemo extends Application {

    public static void main(String[] args) {
       launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LaunchWizard start = new LaunchWizard(stage);
    }
    
}
