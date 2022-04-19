/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainForm;

import Connection.konek;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 */
public final class DataSparepart extends javax.swing.JFrame {
    private DefaultTableModel tabmode;
    private DefaultTableModel tabel;
    Connection cn = konek.koneksiDb();
    ResultSet rs;
     //String sql="";
     //String tanggal1;

    /**
     * Creates new form DataSparepart
     */
     
    
    
    
     private void tabel() {
       String[] judul = {"Nama Spaarepart","Merk","QTY"};
       tabel = new DefaultTableModel(judul,0);
       tbbeli.setModel(tabel);
       String sql = "select * from tb_beli order by id_beli asc";
       //menampilkan data di db ke table 
       try {
           cn = konek.koneksiDb();
           rs = cn.createStatement().executeQuery(sql);
           while(rs.next()) {
               String kodebarang = rs.getString("nms");
               String merkk = rs.getString("merk");
               String namabarang = rs.getString("qty");
               
               
               
               String[] data = {kodebarang,merkk,namabarang};
               tabel.addRow(data);
           }
           
           
       } catch (Exception e) {
           
       }
    }
    public DataSparepart() {
        initComponents();
        ImageIcon ico = new ImageIcon("src/Images/s2mspeed.png");
        setIconImage(ico.getImage());
        this.setLocationRelativeTo(null);
        non_aktif();
        tabel();
        //waktu();
        autokdsparepart();
        tblsparepart();
        tblsparepart.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblsparepart.getTableHeader().setOpaque(false);
        tblsparepart.getTableHeader().setBackground(new Color(0,153,153));
        tblsparepart.getTableHeader().setForeground(new Color(255,255,255));
        tblsparepart.setRowHeight(25);
    }
    
    public void tblsparepart(){
        DefaultTableModel tbl=new DefaultTableModel();
        tbl.addColumn("Kode Sparepart");
        tbl.addColumn("Nama Sparepart");
        tbl.addColumn("Merk");
        tbl.addColumn("Harga");
        tbl.addColumn("Stock");
        tblsparepart.setModel(tbl);
        try{
            Statement Statement=(Statement)konek.koneksiDb().createStatement();
            ResultSet res = Statement.executeQuery("select * from tb_sparepart");
            while (res.next())
            {
                tbl.addRow(new Object[]{
                    res.getString("kd_sparepart"),
                    res.getString("nm_sparepart"),
                    res.getString("merk"),
                    res.getString("harga"),
                    res.getString("stk")
                });
             tblsparepart.setModel(tbl);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
    }
     
      private void non_aktif() {
        txkode.setEnabled(false);
        txnama.setEnabled(false);
        txharga.setEnabled(false);
        txstk.setEnabled(false);
        txmerk.setEnabled(false);
   }
    
    private void aktif() {
        txmerk.setEnabled(true);
        txharga.setEnabled(true);
        txnama.setEnabled(true);
        txstk.setEnabled(true);
        txkode.requestFocus();
    }
    
     private void kosong() {
        //ftanggal.setDateFormatString("");
        txmerk.setText("");
        txharga.setText("");
        txnama.setText("");
        txstk.setText("");
        tambah.setEnabled(true);
        update.setEnabled(true);
        hapus.setEnabled(true);
    }
     
    /*public static java.sql.Date getTanggalFromTable(javax.swing.JTable table, int kolom){
        javax.swing.JTable tblsparepart = table;
        String str_tgl = String.valueOf(tblsparepart.getValueAt(tblsparepart.getSelectedRow(), kolom));
        java.sql.Date tanggal = null;
        try {
            tanggal = (java.sql.Date) new SimpleDateFormat("dd-MM-yyyy").parse(str_tgl);
        } catch (Exception ex) {
            Logger.getLogger(DataSparepart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }*/
     
    private void autokdsparepart() {
        try {
            Connection c = konek.koneksiDb();
            Statement s = c.createStatement();

           String sql = "SELECT * FROM tb_sparepart ORDER by kd_sparepart desc";
            try (ResultSet r = s.executeQuery(sql)) {
                if (r.next()){
                    String kd = r.getString("kd_sparepart").substring(3);
                    String AN = ""+ (Integer.parseInt(kd) + 1);
                    String Nol = "";
                    
                    switch (AN.length()) {
                        case 1:
                            Nol = "000";
                            break;
                        case 2:
                            Nol = "00";
                            break;
                        case 3:
                            Nol = "0";
                            break;
                        case 4:
                            Nol = "";
                            break;
                        default:
                            break;
                    }
                    txkode.setText("PRT" + Nol + AN);
                } else {
                    txkode.setText("PRT0001");  
                }
            }
        } catch (SQLException | NumberFormatException e) {
        }    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txstk = new javax.swing.JTextField();
        txnama = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txkode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txharga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txmerk = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblsparepart = new javax.swing.JTable();
        tambah = new javax.swing.JButton();
        update = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbeli = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Sparepart");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1381, 776));
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(1381, 100));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/s2mspeed.png"))); // NOI18N

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setFont(new java.awt.Font("Californian FB", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DATA SPAREPART");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(324, 324, 324)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator3)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 51, 153));
        jPanel4.setPreferredSize(new java.awt.Dimension(1381, 776));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("MERK");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KODE SPAREPART");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NAMA SPAREPART");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("HARGA");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("STOCK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txmerk, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txstk, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txnama, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txkode, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txmerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txstk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        tblsparepart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblsparepart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsparepartMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblsparepart);

        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gtk-save-as.png"))); // NOI18N
        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gtk-edit.png"))); // NOI18N
        update.setText("EDIT");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/document_delete.png"))); // NOI18N
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit_20px.png"))); // NOI18N
        keluar.setText("KELUAR");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cancel.png"))); // NOI18N
        batal.setText("BATAL");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_20px.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        tbbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbeliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbbeli);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tambah)
                                .addGap(18, 18, 18)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(hapus))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(batal)
                        .addGap(47, 47, 47)
                        .addComponent(keluar)))
                .addGap(84, 84, 84)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCari))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCari))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambah)
                            .addComponent(update)
                            .addComponent(hapus))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblsparepartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsparepartMouseClicked
        update.setEnabled(true);
        update.setText("EDIT");
        hapus.setEnabled(true);
        
        int i = tblsparepart.getSelectedRow();
        
        if (i == -1){
            return;

        }
        String kd_sparepart = (String) tblsparepart.getValueAt(i, 0);
        txkode.setText(kd_sparepart);
        String nm_sparepart = (String) tblsparepart.getValueAt(i, 1);
        txnama.setText(nm_sparepart);
        String merk = (String) tblsparepart.getValueAt(i, 2);
        txmerk.setText(merk);
        String harga = (String) tblsparepart.getValueAt(i, 3);
        txharga.setText(harga);
        String stk = (String) tblsparepart.getValueAt(i, 4);
        txstk.setText(stk);
    }//GEN-LAST:event_tblsparepartMouseClicked

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        
        String tombol = tambah.getText();
        String kd_sparepart = txkode.getText();
        String nm_sparepart  = txnama.getText();
        String merk = txmerk.getText();
        String harga = txharga.getText();
        String stk = txstk.getText();
        
        if (tombol.equals("TAMBAH")) {
            aktif();
            kosong();
            tambah.setText("SIMPAN");
            update.setEnabled(false);
            hapus.setEnabled(false);
            }else {
            JOptionPane.showConfirmDialog(null, "Apakah Data anda sudah benar?", "INFORMASI",JOptionPane.YES_NO_OPTION);
                        try {
                            Connection c = konek.koneksiDb();
                            String sql = "INSERT INTO tb_sparepart VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement p = c.prepareStatement(sql)) {
                    p.setString(1, kd_sparepart);
                    p.setString(2, nm_sparepart);
                    p.setString(3, merk);
                    p.setString(4, harga);
                    p.setString(5, stk);
                    p.executeUpdate();
                }
                            txkode.requestFocus();
                            } catch (SQLException e) {
                            System.out.println("Terjadi Error");
                            }finally{
                                String   msg="<html>Kode Sparepart         = " +txkode.getText()+" <br>"
                                + "Nama Sparepart        = " +txnama.getText()+"<br>"
                                + "Merk              = " +txmerk.getText()+"<br>"
                                + "Harga              = Rp. " +txharga.getText()+"<br>"
                                + "Stock             = " +txstk.getText()+"<html>";
                                JOptionPane optionPane=new JOptionPane();
                                optionPane.setMessage(msg);
                                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                                JDialog dialog=optionPane.createDialog(null, "DATA DISIMPAN");
                                dialog.setVisible(true);
                            }
                            new DataSparepart().setVisible(true);
                            this.dispose();
                            autokdsparepart();
                            tblsparepart();
                            tambah.setText("TAMBAH");
                            non_aktif();
            }
    }//GEN-LAST:event_tambahActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        
        String kd_sparepart = txkode.getText();
        String nm_sparepart  = txnama.getText();
        String merk = txmerk.getText();
        String harga = txharga.getText();
        String stk = txstk.getText();
        
        if ("EDIT".equals(update.getText())) {
            aktif();
            update.setText("UPDATE");
            hapus.setEnabled(false);
            tambah.setEnabled(false);
        } else {
            int ok = JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Ubah Data Ini?", "Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
            try (Statement Statement = (Statement)konek.koneksiDb().createStatement()) {
                Statement.executeUpdate("Update tb_sparepart SET kd_sparepart='"+ kd_sparepart +"',"
                + "nm_sparepart ='"+ nm_sparepart +"',merk='"+ merk +"',"
                + "harga ='"+ harga +"',stk ='"+ stk +"' where kd_sparepart='"+kd_sparepart+"'");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Sparepart Gagal Di Update" + e);
            }
            kosong();
            autokdsparepart();
            JOptionPane.showMessageDialog(null, "Data Sparepart berhasil Di Update");
            update.setText("EDIT");
            tblsparepart();
        }
    }//GEN-LAST:event_updateActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed

        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin ingin Menghapus Data Ini?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from tb_sparepart where kd_sparepart='"+txkode.getText() + "'";
            try {
                PreparedStatement stat = konek.koneksiDb().prepareStatement(sql);
                stat.executeUpdate();
                kosong();
                JOptionPane.showMessageDialog(null, "Data Sparepart Berhasil Dihapus");
                autokdsparepart();
                txkode.requestFocus();
                tblsparepart();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Sparepart Gagal Di Hapus" + e);
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
       dispose();
        FormUtamaPartman fup = new FormUtamaPartman();
        fup.show();
    }//GEN-LAST:event_keluarActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        kosong();
        tambah.setText("TAMBAH");
        non_aktif();
    }//GEN-LAST:event_batalActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        String tombol = btnCari.getText();
        if (tombol.equals("Cari")){
            Object[] Baris = {"Kode Sparepart","Nama Sparepart", "Merk", "Harga", "Stock"};
            tabmode = new DefaultTableModel(null, Baris);
            tblsparepart.setModel(tabmode);
            String sql = "Select * from tb_sparepart where kd_sparepart like '%" + txtCari.getText() + "%'" +
            "or nm_sparepart like '%" + txtCari.getText() + "%'";
            try {
                Statement stat = konek.koneksiDb().createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()) {
                    String kd_sparepart = hasil.getString("kd_sparepart");
                    String nm_sparepart = hasil.getString("nm_sparepart");
                    String merk = hasil.getString("merk");
                    String harga = hasil.getString("harga");
                    String stk = hasil.getString("stk");
                    String[] data = {kd_sparepart,nm_sparepart,merk,harga,stk};
                    tabmode.addRow(data);
                }
            } catch (Exception e) {
            }
            btnCari.setText("Batal");
        }else{
            tblsparepart();
            btnCari.setText("Cari");
            txtCari.setText("");
            tambah.setEnabled(true);
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void tbbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbeliMouseClicked
        int i = tbbeli.getSelectedRow(); 
        String a = tabel.getValueAt(i, 0).toString();
        String b = tabel.getValueAt(i, 1).toString();
        String c = tabel.getValueAt(i, 2).toString();
        txnama.setText(a);
        txmerk.setText(b);
        txstk.setText(c);

    }//GEN-LAST:event_tbbeliMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DataSparepart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton keluar;
    private javax.swing.JButton tambah;
    private javax.swing.JTable tbbeli;
    private javax.swing.JTable tblsparepart;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txkode;
    private javax.swing.JTextField txmerk;
    private javax.swing.JTextField txnama;
    private javax.swing.JTextField txstk;
    private javax.swing.JTextField txtCari;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
