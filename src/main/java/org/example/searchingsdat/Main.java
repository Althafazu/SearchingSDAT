package org.example.searchingsdat;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String garis = "----------------------------------------------";
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        boolean loop = true;
        while(loop) {

            printMenu();
            System.out.print("Masukkan pilihan: ");
            int pilihan = Integer.parseInt(scanner.nextLine());


//            Memilih pilihan
            switch(pilihan) {
            case 1:
                new ShipDataSearch().run();
                break;
            case 2:
                new showCredit().run();
                break;
            case 3:
                if(confirmExit()) {
                    System.out.println("Keluar dari Program....");
                    loop =false;
                }
                break;
            default:
                System.out.println("Pilihan hanya 1 - 3");
                break;
            }
        }
    }

    private static void printMenu() {
        System.out.println(garis);
        System.out.println("SISTEM SEARCHING DATABASE KAPAL LAUT");
        System.out.println(garis);
        System.out.println("1. Searching data kapal laut");
        System.out.println("2. Credit");
        System.out.println("3. Keluar");
        System.out.println(garis);
    }

    private static boolean confirmExit() {
        System.out.print("Anda yakin ingin keluar? (y/n): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("y");
    }
}
