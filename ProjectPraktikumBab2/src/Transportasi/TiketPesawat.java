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
public class TiketPesawat {
    // Attributes
    private String kodeTiket;
    private String namaPenumpang;
    private String tujuan;
    private String tanggalKeberangkatan;
    private double harga;

    // Constructor
    public TiketPesawat(String kodeTiket, String namaPenumpang, String tujuan, String tanggalKeberangkatan, double harga) {
        this.kodeTiket = kodeTiket;
        this.namaPenumpang = namaPenumpang;
        this.tujuan = tujuan;
        this.tanggalKeberangkatan = tanggalKeberangkatan;
        this.harga = harga;
    }

    // Methods (Getter)
    public String getKodeTiket() {
        return kodeTiket;
    }

    public String getNamaPenumpang() {
        return namaPenumpang;
    }

    public String getTujuan() {
        return tujuan;
    }

    public String getTanggalKeberangkatan() {
        return tanggalKeberangkatan;
    }

    public double getHarga() {
        return harga;
    }

    // Method untuk menampilkan informasi tiket
    public void tampilkanInfo() {
        System.out.println("Kode Tiket         : " + kodeTiket);
        System.out.println("Nama Penumpang     : " + namaPenumpang);
        System.out.println("Tujuan             : " + tujuan);
        System.out.println("Tanggal Keberangkatan : " + tanggalKeberangkatan);
        System.out.println("Harga Tiket        : Rp " + harga);
    }
}
