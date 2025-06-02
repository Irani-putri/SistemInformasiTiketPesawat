/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transportasi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Irani Putri
 */
public class GUI_TiketPesawat extends javax.swing.JFrame {
String noidentitas1, nama1, tujuan1, tanggal1, harga1;
    /**
     * Creates new form GUI_TiketPesawat
     */
    public GUI_TiketPesawat() {
        initComponents();
        tampil();
    }
    
    public Connection conn;

    public void koneksi() throws SQLException {
        try {
            conn = null;
             Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/projectpraktikumbab10?serverTimezone=UTC",
        "root",
        "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_TiketPesawat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_TiketPesawat.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_TiketPesawat.class.getName()).log(Level.SEVERE, null, es);
        }
    }

  public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("no identitas");
        tabelhead.addColumn("Nama Penumpang");
        tabelhead.addColumn("Tujuan");
        tabelhead.addColumn("Tanggal Keberangkatan");
        tabelhead.addColumn("Harga Tiket");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_tiketpesawat";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5),});
            }
            tabeltiket.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
  
  public void refresh() {
        new GUI_TiketPesawat().setVisible(true);
        this.setVisible(false);
    }
  
  public void insert() {
        String No_Identitas = txtidentitas.getText();
        String Nama = txtNama.getText();
        String Tujuan = txtTujuan.getText();
        String Tanggal = txtTgl.getText();
        String Harga = txtHarga.getText();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_tiketpesawat(no_identitas, nama, tujuan, tgl_berangkat, harga)" + "VALUES('" + No_Identitas + "','" + Nama + "','" + Tujuan + "','" + Tanggal + "','" + Harga + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Tiket Pesawat!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }

  public void update() {
        String NoIdentitas = txtidentitas.getText();
        String Nama = txtNama.getText();
        String Tujuan = txtTujuan.getText();
        String Tanggal = txtTgl.getText();
        String Harga = txtHarga.getText();
        String Noidentitas = noidentitas1;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_tiketpesawat SET noidentitas='" + NoIdentitas + "'," + "nama='" + Nama + "',"
                    + "tujuan='" + Tujuan + "'" + ",tanggal='" + Tanggal + "harga='" + Harga + "'" + "'WHERE kode_tiket= '" + Noidentitas + "'");

            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Tiket Pesawat!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }

  public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_tiketpesawat WHERE kode_tiket='" + txtidentitas.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }
  
  public void cari() {
    try {
        koneksi(); // pastikan koneksi aktif

        // Bersihkan field hasil sebelumnya
        txtidentitas.setText("");
        txtNama.setText("");
        txtTujuan.setText("");
        txtTgl.setText("");
        txtHarga.setText("");

        String kodeCari = txtCari.getText(); 
        String sql = "SELECT * FROM tb_tiketpesawat WHERE LOWER(kode_tiket) LIKE LOWER('%" + kodeCari + "%')";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            // Set hasil ke form
            txtidentitas.setText(rs.getString(2));
            txtNama.setText(rs.getString(3));
            txtTujuan.setText(rs.getString(4));
            txtTgl.setText(rs.getString(5));
            txtHarga.setText(rs.getString(6));
        } else {
            JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
        }

        rs.close();
        statement.close();

    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}

  public void itempilih() {
        txtidentitas.setText(noidentitas1);
        txtNama.setText(nama1);
        txtTujuan.setText(tujuan1);
        txtTgl.setText(tanggal1);
        txtHarga.setText(harga1);
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtidentitas = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtTujuan = new javax.swing.JTextField();
        txtTgl = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeltiket = new javax.swing.JTable();
        btnbatal = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btncari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("INFORMASI TIKET PESAWAT");

        jLabel2.setText("No. Identitas");

        jLabel3.setText("Nama Penumpang");

        jLabel4.setText("Tujuan");

        jLabel5.setText("Tanggal Keberangkatan");

        jLabel6.setText("Harga Tiket");

        txtTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglActionPerformed(evt);
            }
        });

        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        tabeltiket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Identitas", "Nama", "Tujuan", "Tgl Berangkat", "Harga"
            }
        ));
        tabeltiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeltiketMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabeltiket);

        btnbatal.setText("Batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnclose.setText("Close");
        btnclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncloseActionPerformed(evt);
            }
        });

        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btncari.setText("Search 🔍");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtHarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                        .addComponent(txtTgl, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTujuan, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtidentitas, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsimpan)
                        .addGap(75, 75, 75)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncari)))
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(btnbatal)
                .addGap(43, 43, 43)
                .addComponent(btnhapus)
                .addGap(45, 45, 45)
                .addComponent(btnclose)
                .addGap(39, 39, 39)
                .addComponent(btnupdate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncari)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtidentitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(btnsimpan)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbatal)
                    .addComponent(btnhapus)
                    .addComponent(btnclose)
                    .addComponent(btnupdate))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void tabeltiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeltiketMouseClicked
        // TODO add your handling code here:
        int row = tabeltiket.getSelectedRow();
        noidentitas1 = tabeltiket.getValueAt(row, 0).toString(); // Simpan kode lama untuk update

        // Set langsung ke text field
        txtidentitas.setText(noidentitas1);
        txtNama.setText(tabeltiket.getValueAt(row, 1).toString());
        txtTujuan.setText(tabeltiket.getValueAt(row, 2).toString());
        txtTgl.setText(tabeltiket.getValueAt(row, 3).toString());
        txtHarga.setText(tabeltiket.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tabeltiketMouseClicked

    private void btncloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btncloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_TiketPesawat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_TiketPesawat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_TiketPesawat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_TiketPesawat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_TiketPesawat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnclose;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabeltiket;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTgl;
    private javax.swing.JTextField txtTujuan;
    private javax.swing.JTextField txtidentitas;
    // End of variables declaration//GEN-END:variables

    private void batal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
