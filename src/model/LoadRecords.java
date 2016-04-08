package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Evan Phillips
 */
public class LoadRecords {

    static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private StockRecordMap<LocalDate, StockRecord> map;

    public LoadRecords(File file) {
        readFile(file);
    }

    private void readFile(File file) {
        try {
            String l;
            LocalDate date;
            double value;

            map = new StockRecordMap<>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while ((l = br.readLine()) != null) {
                String data[] = l.split(",");
                date = LocalDate.parse(data[0], DTF);
                if (!data[1].contentEquals(".")) {
                    value = Double.parseDouble(data[1]);
                    map.put(date, new StockRecord(date, value));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public StockRecordMap getMap() {
        return map;
    }
}
