package org.example.searchingsdat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

public class readShip {
    List<ShipIngfo> data = new ArrayList<>();
    String path = "src/data/ListWarShips.csv";
    private int currentPage = 1;
    private static final int DATA_PAGE = 100;

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

    public void show100Data() {
//        int count = 0;
        int totalPages = (int) Math.ceil((double) data.size() / DATA_PAGE);

        char input;
        Scanner scanner = new Scanner(System.in);

        do {
            displayHeader();

            int startIndex = (currentPage - 1) * DATA_PAGE;
            int endIndex = Math.min(currentPage * DATA_PAGE, data.size());

            displayDataRows(startIndex, endIndex);

            System.out.println("Halaman " + currentPage + " / " + totalPages);
            System.out.println("Tekan n untuk ke next page, p untuk ke previous page, esc untuk keluar ke halaman utama");
            input = scanner.next().charAt(0);

            if (Character.toLowerCase(input) == 'n' && currentPage < totalPages) {
                currentPage++;
            } else if (Character.toLowerCase(input) == 'p' && currentPage > 1) {
                currentPage--;
            } else if (input == 27) {
                break;
            }
        } while (input == 'n' || input == 'p' || input == 27);

        scanner.close();
    }

    private void displayHeader() {
        System.out.println("------------------------------------DATA KAPAL LAUT ERA WW2-----------------------------------------\n");
        System.out.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", "ID KAPAL", "NAMA KAPAL", "NEGARA", "TANGGAL PRODUKSI", "NASIB TERKINI");
    }

    private void displayDataRows(int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            ShipIngfo shipIngfo = data.get(i);
            System.out.format("| %-20d | %-20s | %-20s | %-20s | %-20s | %-20s |\n", shipIngfo.getId(), shipIngfo.getName(), shipIngfo.getCountry(), shipIngfo.getType(), shipIngfo.getProduksi().toString(), shipIngfo.getFate());
            System.out.println("---------------------------------------------------------------------------------------\n");
        }
    }
}
