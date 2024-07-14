package org.example.searchingsdat;

import java.util.Scanner;

public class showCredit  {
    public void run() {
        try {
            while(true) {
                Scanner scan = new Scanner(System.in);
                String garis = "----------------------------------------------";


                System.out.println(garis);
                System.out.println("CREDIT KELOMPOK 7 SDAT");
                System.out.println(garis);
                System.out.println("Anggota: ");
                System.out.println("Mohamad Daffa Althaf Naff Djati - 0320230115");
                System.out.println("Irgi Ahmad Fazilla - 03202300");
                System.out.println("Santos Perdana Setiawan - 03202300");
                System.out.println("Dwi Nuryanti - 03202300");

                System.out.println(garis);
                System.out.println("Tekan 'x' untuk kembali ke main menu <--");
                char input = scan.nextLine().charAt(0);
                if(Character.toLowerCase(input) =='x') {
                    break;
                }
            }
        } catch ( Exception exception ) {
            exception.printStackTrace();
        }
    }
}
