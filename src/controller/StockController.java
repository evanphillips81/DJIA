
package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Evan Phillips
 */
public class StockController {
    
    private StockRecordMap map;
    private LoadRecords load;
    
    public StockController() {
        load = new LoadRecords("lib/djia.csv");
        map = load.getMap();
    }
    
    public StockRecordMap getMap() {
        return map;
    }
    
    public ArrayList<StockRecord> getSortedArrayList() {
        ArrayList<StockRecord> list = new ArrayList();
        LocalDate end;
        
        return list;
    }
}
