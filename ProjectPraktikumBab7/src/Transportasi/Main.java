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
        TiketPesawat[] daftarTiket = {
            new TiketEkonomi("TK001", "Andi Saputra", "Jakarta - Bali", "10 Maret 2025", 1500000, true),
            new TiketBisnis("TK002", "Siti Aminah", "Jakarta - Surabaya", "12 Maret 2025", 2500000, "Lounge VIP dan Makanan Gratis")
        };

        System.out.println("=== Informasi Tiket Lengkap (Polimorfisme) ===");
        for (TiketPesawat tiket : daftarTiket) {
            System.out.println(tiket.getInfoTiket());
        }

        System.out.println("=== Informasi Tiket Ringkas (Overloading) ===");
        for (TiketPesawat tiket : daftarTiket) {
            System.out.println(tiket.getInfoTiket(true));
        }
    }
}


