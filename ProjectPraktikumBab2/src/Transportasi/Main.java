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
        // Membuat objek dari class TiketPesawat menggunakan constructor
        TiketPesawat tiket1 = new TiketPesawat("TK001", "Andi Saputra", "Jakarta - Bali", "10 Maret 2025", 1500000);
        TiketPesawat tiket2 = new TiketPesawat("TK002", "Siti Aminah", "Jakarta - Surabaya", "12 Maret 2025", 1200000);

        // Menampilkan informasi tiket
        System.out.println("=== Informasi Tiket Pesawat ===");
        tiket1.tampilkanInfo();
        System.out.println();
        tiket2.tampilkanInfo();
    }
}
