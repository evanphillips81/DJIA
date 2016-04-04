
package dowJonesHistory;

/**
 *
 * @author Evan Phillips
 */
public class StockRecord {
    
    private double value;
    
    public StockRecord(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return ("Value: " + value);
    }
}
