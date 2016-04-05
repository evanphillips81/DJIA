
package model;

/**
 *
 * @author Evan Phillips
 */
public class StockPricesDemo {

    public static void main(String[] args) {
       LoadRecords load = new LoadRecords("lib/DJIA.csv");
             
       StockRecordMap map = load.getMap();
       
       System.out.println("the map of dates: \n" + map);
    }
    
}
