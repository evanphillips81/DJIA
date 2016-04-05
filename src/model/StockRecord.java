
package model;

import java.time.LocalDate;

/**
 *
 * @author Evan Phillips
 */
public class StockRecord {
    
    private double value;
    private LocalDate date;
    
    public StockRecord(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
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
