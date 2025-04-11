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
        // Membuat objek dari class TiketEkonomi dan TiketBisnis
        TiketEkonomi tiket1 = new TiketEkonomi("TK001", "Andi Saputra", "Jakarta - Bali", "10 Maret 2025", 1500000, true);
        TiketBisnis tiket2 = new TiketBisnis("TK002", "Siti Aminah", "Jakarta - Surabaya", "12 Maret 2025", 2500000, "Lounge VIP dan Makanan Gratis");

        // Menampilkan informasi tiket
        System.out.println("=== Informasi Tiket Pesawat Ekonomi ===");
        tiket1.tampilkanInfo();
        System.out.println();

        System.out.println("=== Informasi Tiket Pesawat Bisnis ===");
        tiket2.tampilkanInfo();
    }
}
