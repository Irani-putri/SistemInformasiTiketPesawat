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

public class TiketEkonomi extends TiketPesawat {
    private boolean bagasiTermasuk;

    public TiketEkonomi(String kodeTiket, String namaPenumpang, String tujuan, String tanggalKeberangkatan, double harga, boolean bagasiTermasuk) {
        super(kodeTiket, namaPenumpang, tujuan, tanggalKeberangkatan, harga);
        this.bagasiTermasuk = bagasiTermasuk;
    }

    @Override
    public String getInfoTiket() {
        return "Kode Tiket             : " + getKodeTiket() + "\n" +
               "Nama Penumpang         : " + getNamaPenumpang() + "\n" +
               "Tujuan                 : " + getTujuan() + "\n" +
               "Tanggal Keberangkatan  : " + getTanggalKeberangkatan() + "\n" +
               "Harga Tiket            : Rp " + getHarga() + "\n" +
               "Bagasi Termasuk        : " + (bagasiTermasuk ? "Ya" : "Tidak") + "\n";
    }

    @Override
    public String getInfoTiket(boolean ringkas) {
        if (ringkas) {
            return getKodeTiket() + " - " + getNamaPenumpang() + " [Ekonomi]";
        } else {
            return getInfoTiket();
        }
    }
}


