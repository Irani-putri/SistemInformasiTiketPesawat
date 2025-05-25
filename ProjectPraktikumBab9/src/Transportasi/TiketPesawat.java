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
public interface TiketPesawat {
    String getInfoTiket();
    String getInfoTiket(boolean ringkas);

    // Tambahan getter agar bisa dipakai di GUI
    String getKodeTiket();
    String getNamaPenumpang();
    String getTujuan();
    String getTanggalKeberangkatan();
    double getHarga();
}

