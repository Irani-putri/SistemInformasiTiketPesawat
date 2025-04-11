/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enkapsulasi1;

/**
 *
 * @author Irani Putri
 */
public class TiketPesawat {
    private String kodeTiket;
    private String namaPenumpang;
    private String tujuan;
    private String tanggalKeberangkatan;
    private double harga;

    // Setter
    public void setKodeTiket(String kodeTiket) {
        this.kodeTiket = kodeTiket;
    }

    public void setNamaPenumpang(String namaPenumpang) {
        this.namaPenumpang = namaPenumpang;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public void setTanggalKeberangkatan(String tanggalKeberangkatan) {
        this.tanggalKeberangkatan = tanggalKeberangkatan;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    // Getter
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

    // Format informasi tiket
    public String getInfoTiket() {
        return "=== INFORMASI TIKET PESAWAT ===\n" +
               "Kode Tiket         : " + kodeTiket + "\n" +
               "Nama Penumpang     : " + namaPenumpang + "\n" +
               "Tujuan             : " + tujuan + "\n" +
               "Tanggal Keberangkatan : " + tanggalKeberangkatan + "\n" +
               "Harga Tiket        : Rp " + harga + "\n";
    }
}
