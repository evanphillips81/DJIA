package controller;

import java.io.File;
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
    private LoadRecords records;
    private ArrayList<StockRecord> list;
    private File file;
    
    public StockController(File file) {
        records = new LoadRecords(file);
        map = records.getMap();
        list = getArrayList();
        
    }
        
    public StockRecordMap getMap() {
        return map;
    }
    
    public void add(LocalDate date, double value) {
        StockRecord newRecord = new StockRecord(date, value);
        map.put(date, newRecord);
    }
    
    public ArrayList<StockRecord> getArrayList() {
        list = new ArrayList();
        for (Object value: map.values()) {
            list.add((StockRecord)value);
        }
        
        Collections.sort(list, new DateComparator());
        return list;
    }
    
    public ArrayList<StockRecord> getDateRange(LocalDate start, LocalDate end) {
        ArrayList<StockRecord> datedList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDate().isAfter(start) && list.get(i).getDate().isBefore(end)) {
                datedList.add(list.get(i));
            }
        }
        return datedList;
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