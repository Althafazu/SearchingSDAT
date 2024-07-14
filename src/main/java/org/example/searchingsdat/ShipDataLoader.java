package org.example.searchingsdat;

import javafx.scene.control.Alert;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ShipDataLoader {
    public static HashMap<Integer, ShipIngfo> loadShipCSV(String filePath) {
        HashMap<Integer, ShipIngfo> shipsHashMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
//            Skip header lines
            for(int i = 0; i < 4; i++) br.readLine();
            while((line = br.readLine()) != null) {
                // memisahkan value ketika bertemu semicolon
                String[] values = line.split(";");
                if(values.length == 6) {
                    int id = Integer.parseInt(values[0]);
                    ShipIngfo ship = new ShipIngfo(id, values[1], values[2], values[3], values[4], values[5]);
                    shipsHashMap.put(id, ship);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Read CSV: " +exception);
        }
        return shipsHashMap;
    }
}
