
package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
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
    
    public ArrayList<StockRecord> sort() {
        ArrayList<StockRecord> list = new ArrayList();
        LocalDate start;
        LocalDate finish;
        for (Object value : map.values()) {
            list.add((StockRecord)value);
        }
        
        Collections.sort(list, new DateComparator());
        return list;
    }
    
    private class DateComparator implements Comparator<StockRecord> {

        @Override
        public int compare(StockRecord s1, StockRecord s2) {
            if (s1.getDate().isBefore(s2.getDate())) {
                return -1;
            } else if (s1.getDate().isAfter(s2.getDate())) {
                return 1;
            }            
            return 0;
        }
    }
}
