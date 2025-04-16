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
    private String kodeTiket;
    private String namaPenumpang;
    private String tujuan;
    private String tanggalKeberangkatan;
    private double harga;

    public TiketPesawat(String kodeTiket, String namaPenumpang, String tujuan, String tanggalKeberangkatan, double harga) {
        this.kodeTiket = kodeTiket;
        this.namaPenumpang = namaPenumpang;
        this.tujuan = tujuan;
        this.tanggalKeberangkatan = tanggalKeberangkatan;
        this.harga = harga;
    }

    public String getKodeTiket() { return kodeTiket; }
    public String getNamaPenumpang() { return namaPenumpang; }
    public String getTujuan() { return tujuan; }
    public String getTanggalKeberangkatan() { return tanggalKeberangkatan; }
    public double getHarga() { return harga; }

    // Overridable method
    public String getInfoTiket() {
        return "Kode Tiket             : " + kodeTiket + "\n" +
               "Nama Penumpang         : " + namaPenumpang + "\n" +
               "Tujuan                 : " + tujuan + "\n" +
               "Tanggal Keberangkatan  : " + tanggalKeberangkatan + "\n" +
               "Harga Tiket            : Rp " + harga + "\n";
    }

    // Overloaded method
    public String getInfoTiket(boolean ringkas) {
        if (ringkas) {
            return kodeTiket + " - " + namaPenumpang + " (" + tujuan + ")";
        } else {
            return getInfoTiket();
        }
    }
}
