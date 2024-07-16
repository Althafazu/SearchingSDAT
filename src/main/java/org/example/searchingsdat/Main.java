package org.example.searchingsdat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        boolean loop = true;
        while(loop) {
            String garis = "----------------------------------------------";
            System.out.println(garis);
            System.out.println("SiSTEM SEARCHING DATABASE KAPAL LAUT");
            System.out.println(garis);
            System.out.println("1. Searching data kapal laut");
            System.out.println("2. Credit");
            System.out.println("3. Keluar");
            System.out.println(garis);

            System.out.print("Masukkan pilihan: ");
            Scanner scanner = new Scanner(System.in);
            int pilihan = scanner.nextInt();

//            Memilih pilihan
            switch(pilihan) {
            case 1:
                new ShipDataSearch().run();
                break;
            case 2:
                new showCredit().run();
                break;
            case 3:
                System.out.println("Keluar dari Program....");
                loop =false;
                break;
            default:
                System.out.println("Pilihan hanya 1 - 3");
                break;
            }
        }
    }
}
