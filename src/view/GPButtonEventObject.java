
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
        
    public GPButtonEventObject(Object source) {
        super(source);
    }
    
    public GPButtonEventObject(Object source, LocalDate start, LocalDate end) {
        super(source);
        this.start = start;
        this.end = end;
        
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
    
    
    
}
