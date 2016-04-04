
package dowJonesHistory;

/**
 *
 * @author Evan Phillips
 */
public class StockPricesDemo {

    
    public static void main(String[] args) {
        LoadRecords reader = new LoadRecords("lib/DJIA.csv");
        System.out.println(reader.getMap());
        System.out.println(reader.getMap().getSize());
    }
    
}
