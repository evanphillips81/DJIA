
package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private ArrayList<StockRecord> list;
    
    public StockController() {
        load = new LoadRecords("lib/djia.csv");
        map = load.getMap();
        
    }
    
    public StockRecordMap getMap() {
        return map;
    }
    
    public ArrayList<StockRecord> sort() {
        list = new ArrayList();
        for (Object value : map.values()) {
            list.add((StockRecord)value);
        }
        
        Collections.sort(list, new DateComparator());
        return list;
    }
    
    public ArrayList<StockRecord> getDateRange(LocalDate start, LocalDate end) {
        list = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDate().isAfter(start) && list.get(i).getDate().isBefore(end)) {
                list.add(list.get(i));
            }
        }
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
