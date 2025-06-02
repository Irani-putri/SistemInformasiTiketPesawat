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
public class GUI_Tiket extends javax.swing.JFrame {
String noidentitas1, jt1, nama1, tujuan1, tanggal1, harga1, fasilitas1;
    /**
     * Creates new form GUI_Tiket
     */
    public GUI_Tiket() {
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
            Logger.getLogger(GUI_Tiket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_Tiket.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_Tiket.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("no identitas");
        tabelhead.addColumn("Jenis Tiket");
        tabelhead.addColumn("Nama Penumpang");
        tabelhead.addColumn("Tujuan");
        tabelhead.addColumn("Tanggal Keberangkatan");
        tabelhead.addColumn("Harga Tiket");
        tabelhead.addColumn("Fasilitas Tambahan");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_tiket";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7),});
            }
            Tabel_Tiket.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    public void refresh() {
        new GUI_Tiket().setVisible(true);
        this.setVisible(false);
    }
    
    public void insert() {
        String No_Identitas = txtidentitas.getText();
        String jt;
        if (radiobtnEkonomi.isSelected()) {
            jt = "E";
        } else {
            jt = "B";
        }
        String Nama = txtNama.getText();
        String Tujuan = txtTujuan.getText();
        String Tanggal = txtTgl.getText();
        String Harga = txtHarga.getText();
        String FasilitasTambahan = txtFasilitas.getText();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_tiket (no_identitas, jt, nama, tujuan, tgl_berangkat, harga, ft) " + "VALUES('" + No_Identitas + "','" + jt + "','" + Nama + "','" + Tujuan + "','" + Tanggal + "','" + Harga + "','" + FasilitasTambahan + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Tiket!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }
    
    public void update() {
        String NoIdentitas = txtidentitas.getText();
        String jt;
        if (radiobtnEkonomi.isSelected()) {
            jt = "E";
        } else {
            jt = "B";
        }
        String Nama = txtNama.getText();
        String Tujuan = txtTujuan.getText();
        String Tanggal = txtTgl.getText();
        String Harga = txtHarga.getText();
        String FasilitasTambahan = txtFasilitas.getText();
        String Noidentitas = noidentitas1;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_tiket SET NoIdentitas='" + NoIdentitas + "'," + "jt='" + jt + "',"+ "nama='" + Nama + "'" + ",tujuan='" + Tujuan + "',tanggal='" + Tanggal + "',harga='"+ Harga + "',fasilitastambahan='" + FasilitasTambahan + "' WHERE kode_tiket = '"+ Noidentitas + "'");
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Tiket Berhasil!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
    
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_tiket WHERE kode_tiket='" + txtidentitas.getText() + "'";
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
            try ( Statement statement = conn.createStatement()) {
                String sql = "SELECT * FROM tb_tiket WHERE `kode_tiket`  LIKE '%" + txtCari.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    txtidentitas.setText(rs.getString(2));
                    String jt = rs.getString(3);
                    if (jt.equalsIgnoreCase("E")) {
                        radiobtnEkonomi.setSelected(true);
                    } else {
                        radiobtnBisnis.setSelected(true);
                    }
                    txtNama.setText(rs.getString(3));
                    txtTujuan.setText(rs.getString(4));
                    txtTgl.setText(rs.getString(5));
                    txtHarga.setText(rs.getString(6));
                    txtFasilitas.setText(rs.getString(7));
                } else {
                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }
    
    public void itempilih() {
        txtidentitas.setText(noidentitas1);
         if (jt1.equalsIgnoreCase("E")) {
            radiobtnEkonomi.setSelected(true);
        } else {
            radiobtnBisnis.setSelected(true);
        }
        txtNama.setText(nama1);
        txtTujuan.setText(tujuan1);
        txtTgl.setText(tanggal1);
        txtHarga.setText(harga1);
        txtFasilitas.setText(fasilitas1);
    }


    public void batal() {
        txtidentitas.setText("");
         if (jt1.equalsIgnoreCase("E")) {
            radiobtnEkonomi.setSelected(true);
        } else {
            radiobtnBisnis.setSelected(true);
        }
        txtNama.setText("");
        txtTujuan.setText("");
        txtTgl.setText("");
        txtHarga.setText("");
        txtFasilitas.setText("");
        buttonGroup1.clearSelection();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtidentitas = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtTujuan = new javax.swing.JTextField();
        txtTgl = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        radiobtnEkonomi = new javax.swing.JRadioButton();
        radiobtnBisnis = new javax.swing.JRadioButton();
        txtFasilitas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabel_Tiket = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        txtBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btncari = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("INFORMASI TIKET");

        jLabel2.setText("No. Identitas");

        jLabel3.setText("Jenis Tiket");

        jLabel4.setText("Nama Penumpang");

        jLabel5.setText("Tujuan");

        jLabel6.setText("Tanggal Keberangkatan");

        jLabel7.setText("Harga Tiket");

        jLabel8.setText("Fasilitas Tambahan");

        txtTujuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTujuanActionPerformed(evt);
            }
        });

        radiobtnEkonomi.setText("Ekonomi");

        radiobtnBisnis.setText("Bisnis");
        radiobtnBisnis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtnBisnisActionPerformed(evt);
            }
        });

        txtFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFasilitasActionPerformed(evt);
            }
        });

        Tabel_Tiket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Identitas", "JT", "Nama", "Tujuan", "Tgl Berangkat", "Harga", "FT"
            }
        ));
        Tabel_Tiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel_TiketMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabel_Tiket);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        txtBatal.setText("Batal");
        txtBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBatalActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btncari.setText("Search 🔍");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtidentitas, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radiobtnEkonomi)
                                    .addComponent(radiobtnBisnis)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFasilitas, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(37, 37, 37)
                        .addComponent(txtBatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnupdate)
                        .addGap(35, 35, 35)
                        .addComponent(btnHapus)
                        .addGap(34, 34, 34)
                        .addComponent(btnClose)
                        .addGap(74, 74, 74))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncari)
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncari)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtidentitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(radiobtnEkonomi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radiobtnBisnis)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtFasilitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(txtBatal)
                    .addComponent(btnHapus)
                    .addComponent(btnClose)
                    .addComponent(btnupdate))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radiobtnBisnisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtnBisnisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiobtnBisnisActionPerformed

    private void txtFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFasilitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFasilitasActionPerformed

    private void txtTujuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTujuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTujuanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBatalActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_txtBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void Tabel_TiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel_TiketMouseClicked
        // TODO add your handling code here:
        int tabel = Tabel_Tiket.getSelectedRow();
        noidentitas1 = Tabel_Tiket.getValueAt(tabel, 0).toString();
        jt1 = Tabel_Tiket.getValueAt(tabel, 1).toString();
        nama1 = Tabel_Tiket.getValueAt(tabel, 2).toString();
        tujuan1 = Tabel_Tiket.getValueAt(tabel, 3).toString();
        tanggal1 = Tabel_Tiket.getValueAt(tabel, 4).toString();
        harga1 = Tabel_Tiket.getValueAt(tabel, 5).toString();
        fasilitas1 = Tabel_Tiket.getValueAt(tabel, 6).toString();
        itempilih();
    }//GEN-LAST:event_Tabel_TiketMouseClicked

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
            java.util.logging.Logger.getLogger(GUI_Tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Tiket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel_Tiket;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radiobtnBisnis;
    private javax.swing.JRadioButton radiobtnEkonomi;
    private javax.swing.JButton txtBatal;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtFasilitas;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTgl;
    private javax.swing.JTextField txtTujuan;
    private javax.swing.JTextField txtidentitas;
    // End of variables declaration//GEN-END:variables
}
