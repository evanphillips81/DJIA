package view;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Evan Phillips
 */
public class StockFileChooser {

    private Stage stage;
    private FileChooser fc;
    private File file;

    public StockFileChooser() throws FileNotFoundException {
        makeNewObjects();
    }

    private void makeNewObjects() {
        this.stage = stage;
        fc = new FileChooser();
    }

    public File getFile() throws FileNotFoundException {
        fc.setTitle("Choose a file");
        fc.getExtensionFilters().add(
                new ExtensionFilter("Comma-separated value file", "*.csv"));
        File selected = fc.showOpenDialog(stage);
        if (selected == null) {
            throw new FileNotFoundException();
        } else {
            return selected;
        }
    }
  
}
