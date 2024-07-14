package org.example.searchingsdat;

public class Main {
    public static void main(String[] args) {
        System.out.println("-----SiSTEM SEARCHING DATABASE KAPAL LAUT-----");
        System.out.println("1. Searching data kapal laut");
        System.out.println("2. View data kapal laut");
        System.out.println("3. Lihat data paling dicari");
        System.out.println("4. Credit");
        System.out.println("5. Keluar");

        int pilihan = 0;

        boolean loop = true;
        while(loop) {
            switch(pilihan) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                loop = false;
                break;
            default:
                System.out.println("Pilihan hanya 1 -5");
                break;
            }
        }
    }
}
