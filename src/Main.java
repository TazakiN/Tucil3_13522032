import logic.AStar;
import logic.GreedyBestFirstSearch;
import logic.UCS;
import tools.Kamus;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    private static boolean validateInput(String kataAwal, String kataAkhir, Kamus kamus) {
        if (kataAwal.length() != kataAkhir.length()) {
            System.out.println("\u001B[31mPanjang kata awal dan kata akhir harus sama\u001B[0m");
            return false;
        } else {
            if (!kamus.contains(kataAwal)) {
                System.out.println("\u001B[31mKata awal tidak ditemukan di kamus\u001B[0m");
                return false;
            }

            if (!kamus.contains(kataAkhir)) {
                System.out.println("\u001B[31mKata akhir tidak ditemukan di kamus\u001B[0m");
                return false;
            }
        }
        return true;
    }

    public static void printAsciiArt() {
        try {
            File file = new File("asciiArt.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("\u001B[32m" + line + "\u001B[0m");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: asciiART.txt");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean searchAgain = true;

        printAsciiArt();

        while (searchAgain) {
            System.out.print("\u001B[34mMasukkan kata awal: \u001B[0m");
            String kataAwal = scanner.nextLine().toUpperCase();

            System.out.print("\u001B[34mMasukkan kata akhir: \u001B[0m");
            String kataAkhir = scanner.nextLine().toUpperCase();

            Kamus kamus = null;
            try {
                kamus = new Kamus(kataAwal.length());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (validateInput(kataAwal, kataAkhir, kamus)) {

                System.out.println("\n---- PILIHAN ALGORITMA ----");
                System.out.println("1. UCS");
                System.out.println("2. Greedy Best First Search");
                System.out.println("3. A*");
                System.out.print("\u001B[34mPilihan (masukkan angkanya saja): \u001B[0m");
                int pilihan = scanner.nextInt();

                System.out.println("Mencari kata dari " + kataAwal + " ke " + kataAkhir + "...");
                switch (pilihan) {
                    case 1:
                        UCS ucs = new UCS(kataAwal, kataAkhir, kamus);
                        ucs.driver();
                        break;
                    case 2:
                        GreedyBestFirstSearch gbfs = new GreedyBestFirstSearch(kataAwal, kataAkhir, kamus);
                        gbfs.driver();
                        break;
                    case 3:
                        AStar aStar = new AStar(kataAwal, kataAkhir, kamus);
                        aStar.driver();
                        break;
                    default:
                        System.out.println("Pilihan metode tidak valid");
                        break;
                }
            }

            System.out.print("Apakah Anda ingin mencari lagi? (Y/N): ");
            String searchAgainInput = scanner.next();
            searchAgain = searchAgainInput.equalsIgnoreCase("Y");
            scanner.nextLine();
        }

        scanner.close();
    }
}