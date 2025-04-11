/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transportasi;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        // Membuat objek dari class TiketPesawat
        TiketPesawat tiket1 = new TiketPesawat();
        TiketPesawat tiket2 = new TiketPesawat();

        // Mengisi data tiket 1
        tiket1.setKodeTiket("TK001");
        tiket1.setNamaPenumpang("Andi Saputra");
        tiket1.setTujuan("Jakarta - Bali");
        tiket1.setTanggalKeberangkatan("10 Maret 2025");
        tiket1.setHarga(1500000);

        // Mengisi data tiket 2
        tiket2.setKodeTiket("TK002");
        tiket2.setNamaPenumpang("Siti Aminah");
        tiket2.setTujuan("Jakarta - Surabaya");
        tiket2.setTanggalKeberangkatan("12 Maret 2025");
        tiket2.setHarga(1200000);

        // Menampilkan informasi tiket
        System.out.println("=== Informasi Tiket Pesawat ===");
        tiket1.tampilkanInfo();
        System.out.println();
        tiket2.tampilkanInfo();
    }
}

