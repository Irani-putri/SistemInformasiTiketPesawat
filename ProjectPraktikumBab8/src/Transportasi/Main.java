/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transportasi;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author Irani Putri
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            TiketPesawat[] daftarTiket = new TiketPesawat[2];
            
            for (int i = 0; i < daftarTiket.length; i++) {
                System.out.println("\nMasukkan data tiket ke-" + (i + 1));
                System.out.print("Jenis tiket (1. Ekonomi, 2. Bisnis): ");
                
                int jenis;
                try {
                    jenis = input.nextInt();
                    input.nextLine(); // Buang newline
                } catch (InputMismatchException e) {
                    JOptionPane.showMessageDialog(null, "Input tidak sesuai! Masukkan angka yang valid.",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                    input.nextLine(); // buang input error
                    i--;
                    continue;
                }

                try {
                    System.out.print("Kode Tiket: ");
                    String kode = input.nextLine();
                    System.out.print("Nama Penumpang: ");
                    String nama = input.nextLine();
                    System.out.print("Tujuan: ");
                    String tujuan = input.nextLine();
                    System.out.print("Tanggal Keberangkatan: ");
                    String tanggal = input.nextLine();
                    System.out.print("Harga Tiket: ");
                    double harga = input.nextDouble();
                    input.nextLine(); // Buang newline

                    if (harga < 0) throw new IllegalArgumentException("Harga tidak boleh negatif!");

                    if (jenis == 1) {
                        System.out.print("Apakah termasuk bagasi? (true/false): ");
                        boolean bagasi = input.nextBoolean();
                        daftarTiket[i] = new TiketEkonomi(kode, nama, tujuan, tanggal, harga, bagasi);
                    } else if (jenis == 2) {
                        input.nextLine(); // Buang newline sebelum fasilitas
                        System.out.print("Fasilitas Tambahan: ");
                        String fasilitas = input.nextLine();
                        daftarTiket[i] = new TiketBisnis(kode, nama, tujuan, tanggal, harga, fasilitas);
                    } else {
                        JOptionPane.showMessageDialog(null, "Jenis tiket tidak valid.",
                                "Peringatan", JOptionPane.WARNING_MESSAGE);
                        i--; // ulangi input
                    }

                } catch (InputMismatchException e) {
                    JOptionPane.showMessageDialog(null, "Input tidak sesuai! Masukkan angka yang valid.",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                    input.nextLine(); // buang input error
                    i--;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Kesalahan: " + e.getMessage(),
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                    i--;
                }
            }

            System.out.println("\n=== Informasi Tiket Lengkap ===");
            for (TiketPesawat tiket : daftarTiket) {
                System.out.println(tiket.getInfoTiket());
            }

            System.out.println("=== Informasi Tiket Ringkas ===");
            for (TiketPesawat tiket : daftarTiket) {
                System.out.println(tiket.getInfoTiket(true));
            }
        }
    }
}




