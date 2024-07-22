package org.example.searchingsdat;
import java.io.*;
import java.util.*;

public class ShipDataSearch {

    List<ShipIngfo> data = new ArrayList<>();
    String path = "src/data/ListWarShips.csv";
    String SUGGESTIONS_FILE_PATH = "src/data/searchSuggestions.txt";
    private int currentPage = 1;
    private static final int DATA_PAGE = 100;

    Scanner scanner = new Scanner(System.in);


    public void run() {
        data = readShip(path);
        loadSearchSuggestionsFromFile();

        System.out.println("Searching berdasarkan apa?");
        System.out.println("1. Searching berdasarkan id.");
        System.out.println("2. Searching berdasarkan nama.");
        System.out.println("3. Searching berdasarkan country.");
        System.out.println("4. Searching berdasarkan tahun produksi.");

        showSuggestions();

        System.out.print("Pilihan : ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        switch(pilihan) {
            case 1:
                System.out.print("Masukkan ID Kapal: ");
                String id = scanner.nextLine();
                updateSearchSuggestions(id);
                searchID(id);
                break;
            case 2:
                System.out.print("Masukkan Nama Kapal: ");
                String nama = scanner.nextLine();
                if(!nama.isEmpty()) {
                    updateSearchSuggestions(nama);
                    searchName(nama);
                } else {
                    System.out.println("input kosong");
                }
                break;
            case 3:
                System.out.print("Masukkan Nama Negara: ");
                String country = scanner.nextLine();

                if(!country.isEmpty()) {
                    updateSearchSuggestions(country);
                    searchCountry(country);
                } else {
                    System.out.println("Input kosong");
                }
                break;
            case 4:
                System.out.print("Masukkan tahun produksi: ");
                String tahun = scanner.nextLine();
                if(!tahun.isEmpty()) {
                    updateSearchSuggestions(tahun);
                    searchTahun(tahun);
                } else {
                    System.out.println("Input kosong");
                }
                break;
            default:
                System.out.println("Pilihan tidak tersedia");
                break;
        }
        safeSearchSuggestionsToFile();
    }

    private Map<String, Integer> searchSuggestions = new HashMap<>();
    private void updateSearchSuggestions(String searchCriteria) {
        searchSuggestions.put(searchCriteria, searchSuggestions.getOrDefault(searchCriteria, 0) +1);
    }

    private void safeSearchSuggestionsToFile() {
        List<Map.Entry<String, Integer>> sortedSuggestions = new ArrayList<>(searchSuggestions.entrySet());
        sortedSuggestions.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        try(PrintWriter writer = new PrintWriter(SUGGESTIONS_FILE_PATH)) {
            for(Map.Entry<String, Integer> entry : sortedSuggestions) {
                writer.println(entry.getKey() +";" +entry.getValue());
            }
        } catch (Exception exception) {
            System.out.println("Error when save suggestions to file: " +exception);
        }
    }

    private void loadSearchSuggestionsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SUGGESTIONS_FILE_PATH))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if(parts.length == 2) {
                    searchSuggestions.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (Exception exception) {
            System.out.println("Error loading data from .txt: " + exception);
            exception.printStackTrace();
        }
    }

    private void showSuggestions() {
        System.out.println("Top 10 Search Suggestions");
        List<Map.Entry<String, Integer>> sortedSuggestions = new ArrayList<>(searchSuggestions.entrySet());
        sortedSuggestions.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int count = 0;
        for(Map.Entry<String, Integer> entry : searchSuggestions.entrySet()) {
            if(count < 10) {
                System.out.println(entry.getKey() +";" +entry.getValue());
                count++;
            } else {
                break;
            }
        }
    }

    public List<ShipIngfo> readShip(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
                    String produksi = values[4]; // Anda perlu mengonversi String ke Date
                    String fate = values[5];

                    ShipIngfo ships = new ShipIngfo(id, name, country, type, produksi, fate);
                    data.add(ships);
                }
            }
        } catch (Exception exception) {
            // TODO: handle exception
            exception.printStackTrace();
            System.out.println("Error import data kedalam arraylist: " + exception);
        }
        return data;
    }

    public void searchCountry(String country) {
        List<ShipIngfo> result = new ArrayList<>();

//        Mencari data dari kapal dengan negara yg memiliki kata kunci sesuai
        for(ShipIngfo ship : data) {
            if(ship.getCountry().toLowerCase().contains(country.toLowerCase())) {
                result.add(ship);
            }
        }

//        Jika data yang dicari tidak kosong
        if(!result.isEmpty()) {
            show100Data(result);
        } else {
            System.out.println("Data Kapal dengan asal negara " +country +" tidak ditemukan...");
            return;
        }
    }

    public void searchName(String name) {
        List<ShipIngfo> result = new ArrayList<>();

        for(ShipIngfo ship : data) {
            if(ship.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(ship);
            }
        }

        if(!result.isEmpty()) {
            show100Data(result);
        } else {
            System.out.println("Data Kapal dengan nama " +name +" tidak ditemukan...");
            return;
        }
    }

    public void searchTahun(String tahun) {
        List<ShipIngfo> result = new ArrayList<>();

        for(ShipIngfo ship : data) {
            if(ship.getProduksi().contains(tahun)) {
                result.add(ship);
            }
        }

        if(!result.isEmpty()) {
            show100Data(result);
        } else {
            System.out.println("Data kapal dengan tahun produksi  " +tahun +" tidak ditemukan...");
            return;
        }
    }

    public void searchID(String input) {
        List<ShipIngfo> result = new ArrayList<>();

        int id = Integer.parseInt(input);
        for(ShipIngfo ship : data) {
            if(ship.getId() == id) {
                result.add(ship);
            }
        }

        if(!result.isEmpty()) {
            show100Data(result);
        } else {
            System.out.println("Data Kapal dengan ID: " +id +" tidak ditemukan...");
        }
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
            input = scanner.nextLine().charAt(0);

            if (Character.toLowerCase(input) == 'n' && currentPage < totalPages) {
                currentPage++;
            } else if (Character.toLowerCase(input) == 'p' && currentPage > 1) {
                currentPage--;
            } else if (input == 'x') {
                scanner.nextLine();
                break;
            }
        } while (input == 'n' || input == 'p' || input == 'x');

        scanner.close();
    }

    public void show100Data(List <ShipIngfo> searchData) {
//        int count = 0;
        data = searchData;
        int totalPages = (int) Math.ceil((double) data.size() / DATA_PAGE);

        char input;
        Scanner scanner = new Scanner(System.in);

        do {
            displayHeader();

            int startIndex = (currentPage - 1) * DATA_PAGE;
            int endIndex = Math.min(currentPage * DATA_PAGE, data.size());

            displayDataRows(startIndex, endIndex);

            System.out.println("Halaman " + currentPage + " / " + totalPages);
            System.out.println("Tekan 'n' untuk ke next page, 'p' untuk ke previous page, 'x' untuk keluar ke halaman utama");
            input = scanner.nextLine().charAt(0);

            if (Character.toLowerCase(input) == 'n' && currentPage < totalPages) {
                currentPage++;
            } else if (Character.toLowerCase(input) == 'p' && currentPage > 1) {
                currentPage--;
            } else if (Character.toLowerCase(input) =='x') {
                break;
            }
        } while (input == 'n' || input == 'p' || input == 'x');

        scanner.close();
    }

    private void displayHeader() {
        System.out.println("------------------------------------DATA KAPAL LAUT ERA WW2-----------------------------------------\n");
        System.out.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", "ID KAPAL", "NAMA KAPAL", "TIPE KAPAL", "NEGARA", "TAHUN PRODUKSI", "NASIB TERKINI");
    }

    private void displayDataRows(int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            ShipIngfo shipIngfo = data.get(i);
            System.out.format("| %-20d | %-20s | %-20s | %-20s | %-20s | %-20s |\n", shipIngfo.getId(), shipIngfo.getName(), shipIngfo.getCountry(), shipIngfo.getType(), shipIngfo.getProduksi().toString(), shipIngfo.getFate());
            System.out.println("---------------------------------------------------------------------------------------\n");
        }
    }
}
