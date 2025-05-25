/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transportasi;

/**
 *
 * @author Irani Putri
 */
public class Main {
    public static void main(String[] args) {
        TiketPesawat[] daftarTiket = new TiketPesawat[2];

        try {
            // Data hardcoded, dan contoh data valid
            daftarTiket[0] = new TiketEkonomi("EK123", "Ayu", "Jakarta", "2025-06-01", 850000, true);

            // Contoh data yang bisa menyebabkan exception
            daftarTiket[1] = new TiketBisnis("BS456", "Budi", "Surabaya", "2025-06-02", -1500000, "Lounge & Makanan Gratis");

        } catch (IllegalArgumentException e) {
            System.out.println("Terjadi kesalahan saat membuat tiket: " + e.getMessage());
            return; // Hentikan program karena data tidak valid
        }

        System.out.println("\n=== Info Tiket Lengkap ===");
        for (TiketPesawat tiket : daftarTiket) {
            System.out.println(tiket.getInfoTiket());
        }

        System.out.println("=== Info Tiket Ringkas ===");
        for (TiketPesawat tiket : daftarTiket) {
            System.out.println(tiket.getInfoTiket(true));
        }
    }
}