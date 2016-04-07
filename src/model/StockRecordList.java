
package model;

import java.util.ArrayList;

/**
 *
 * @author Evan Phillips
 */
public class StockRecordList {
    
    private ArrayList<StockRecord> list;
    
    public StockRecordList(StockRecordMap map) {
        list = new ArrayList<StockRecord>();
        populate();
    }
    
    public void populate() {
        
    }
    
    public void add(StockRecord newRecord) {
        list.add(newRecord);
    }
    
    public void setList(ArrayList<StockRecord> list) {
        this.list = list;
    }
    
    public ArrayList getList() {
        return list;
    }
    
    public void clear() {
        list.clear();
    }
}
