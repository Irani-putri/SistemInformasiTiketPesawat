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
public class TiketBisnis implements TiketPesawat {
    private String kode, nama, tujuan, tanggal, fasilitas;
    private double harga;

    public TiketBisnis(String kode, String nama, String tujuan, String tanggal, double harga, String fasilitas) {
        if (harga <= 0) throw new IllegalArgumentException("Harga tidak boleh kurang dari atau sama dengan 0.");
        this.kode = kode;
        this.nama = nama;
        this.tujuan = tujuan;
        this.tanggal = tanggal;
        this.harga = harga;
        this.fasilitas = fasilitas;
    }

    @Override
    public String getInfoTiket() {
        return String.format(
            "Kode: %s\nNama: %s\nTujuan: %s\nTanggal: %s\nHarga: %.2f\nFasilitas: %s\n",
            kode, nama, tujuan, tanggal, harga, fasilitas
        );
    }

    @Override
    public String getInfoTiket(boolean ringkas) {
        return ringkas ? kode + " - " + nama + " [Bisnis]" : getInfoTiket();
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

    public String getFasilitasTambahan() {
        return fasilitas;
    }
}

