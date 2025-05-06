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

public class TiketBisnis extends TiketPesawat {
    private String fasilitasTambahan;

    public TiketBisnis(String kodeTiket, String namaPenumpang, String tujuan, String tanggalKeberangkatan, double harga, String fasilitasTambahan) {
        super(kodeTiket, namaPenumpang, tujuan, tanggalKeberangkatan, harga);
        this.fasilitasTambahan = fasilitasTambahan;
    }

    public String getFasilitasTambahan() {
        return fasilitasTambahan;
    }

    @Override
    public String getInfoTiket() {
        return "Kode Tiket             : " + getKodeTiket() + "\n" +
               "Nama Penumpang         : " + getNamaPenumpang() + "\n" +
               "Tujuan                 : " + getTujuan() + "\n" +
               "Tanggal Keberangkatan  : " + getTanggalKeberangkatan() + "\n" +
               "Harga Tiket            : Rp " + getHarga() + "\n" +
               "Fasilitas Tambahan     : " + fasilitasTambahan + "\n";
    }

    @Override
    public String getInfoTiket(boolean ringkas) {
        if (ringkas) {
            return getKodeTiket() + " - " + getNamaPenumpang() + " [Bisnis]";
        } else {
            return getInfoTiket();
        }
    }
}


