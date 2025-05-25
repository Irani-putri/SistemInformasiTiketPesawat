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
public class TiketEkonomi implements TiketPesawat {
    private String kode, nama, tujuan, tanggal;
    private double harga;
    private boolean bagasi;

    public TiketEkonomi(String kode, String nama, String tujuan, String tanggal, double harga, boolean bagasi) {
        if (harga <= 0) throw new IllegalArgumentException("Harga tidak boleh kurang dari atau sama dengan 0.");
        this.kode = kode;
        this.nama = nama;
        this.tujuan = tujuan;
        this.tanggal = tanggal;
        this.harga = harga;
        this.bagasi = bagasi;
    }

    @Override
    public String getInfoTiket() {
        return String.format(
            "Kode: %\nNama: %s\nTujuan: %s\nTanggal: %s\nHarga: %.2f\nBagasi: %s\n",
            kode, nama, tujuan, tanggal, harga, bagasi ? "Ya" : "Tidak"
        );
    }

    @Override
    public String getInfoTiket(boolean ringkas) {
        return ringkas ? kode + " - " + nama + " [Ekonomi]" : getInfoTiket();
    }

    // Getter untuk GUI
    @Override
    public String getKodeTiket() {
        return kode;
    }

    @Override
    public String getNamaPenumpang() {
        return nama;
    }

    @Override
    public String getTujuan() {
        return tujuan;
    }

    @Override
    public String getTanggalKeberangkatan() {
        return tanggal;
    }

    @Override
    public double getHarga() {
        return harga;
    }

    public boolean isBagasi() {
        return bagasi;
    }
}

