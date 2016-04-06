
package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventObject;
import model.StockRecord;

/**
 *
 * @author Evan Phillips
 */
public class GPButtonEventObject extends EventObject{
    
    private LocalDate start;
    private LocalDate end;
    private ArrayList<Object> list;
        
    public GPButtonEventObject(Object source) {
        super(source);
    }
    
    public GPButtonEventObject(Object source, LocalDate start, LocalDate end) {
        super(source);
        this.start = start;
        this.end = end;
        
    }
    
    public ArrayList<Object> getList() {
        
        
        return list;
    }
}
