package org.example.searchingsdat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import static org.apache.commons.lang3.time.DateUtils.parseDate;

public class readShip {
    List<ShipIngfo> data = new ArrayList<>();
    String path = "src/data/ListWarShips.csv";

    public List <ShipIngfo> readShip(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            // TODO: Implementasi load data kedalam ArrayList
            String line;
            while((line = br.readLine()) != null) {
//                Memisahkan setiap data per kolom ketika bertemu ;
                String[] values = line.split(";");
                if(values.length == 6) {
                    int id = Integer.parseInt(values[0]); // convert kolom 1 menjadi id
                    String name = values[1];
                    String country = values[2];
                    String type = values[3];
                    Date produksi = parseDate(values[4]); // Anda perlu mengonversi String ke Date
                    String fate = values[5];

                    ShipIngfo ships = new ShipIngfo(id, name, country, type, produksi, fate);
                    data.add(ships);
                }
            }
        } catch (Exception exception) {
            // TODO: handle exception
            exception.printStackTrace();
            System.out.println("Error import data kedalam arraylist: " +exception);
        }
        return data;
    }

    public void showData() {
        if (data != null && !data.isEmpty()) {
            System.out.println("------------------------------------DATA KAPAL LAUT ERA WW2-----------------------------------------");
            System.out.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", "ID KAPAL", "NAMA KAPAL", "NEGARA", "TANGGAL PRODUKSI", "NASIB TERKINI");
            for (ShipIngfo shipIngfo : data) {
                System.out.format
                ( 
                    "| %-20d | %-20s | %-20s | %-20s | %-20s | %-20s |\n", 
                    shipIngfo.getId(), shipIngfo.getName(), shipIngfo.getCountry(), shipIngfo.getType(), shipIngfo.getProduksi().toString(), shipIngfo.getFate() 
                );
                System.out.println("---------------------------------------------------------------------------------------");
                // System.out.println(shipIngfo);
            }
        } else {
            System.out.println("Tidak ada data tersedia");
        }
    }
}
