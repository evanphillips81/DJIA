
package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.ListView;
import model.StockRecord;

/**
 *
 * @author Evan Phillips
 */
public class StockListView implements Observer {

    private ListView<String> list;
    
    public StockListView() {
        list = new ListView<>();
    }
    
    public void setListView(ArrayList<StockRecord> records) {
        list.getItems().clear();
        for (int i = 0; i < records.size(); i++) {
            String date = records.get(i).getDate().toString();
            String value = ("\t$"+records.get(i).getValue());
            list.getItems().add(date + ": " + value);
        }
        
    }
    
    public ListView getListView() {
        return list;
    }
    
    @Override
    public void update(Observable o, Object o1) {
        
    }
    
}
