
package model;

import java.util.ArrayList;

/**
 *
 * @author Evan Phillips
 */
public interface IObserver {
    
    public void update(ArrayList<StockRecord> newList);
    
}
