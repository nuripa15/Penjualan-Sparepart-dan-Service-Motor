/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainForm;

import Connection.konek;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public final class TransaksiPenjualanSparepart extends javax.swing.JFrame {

    public long total;
    public long bayar;
    public long kembali;
    public Statement st;
    Connection cn = konek.koneksiDb();
    DecimalFormat kursIndonesia;
    DecimalFormatSymbols formatrupiah;
    Double nilai;
    
    private DefaultTableModel model;


    /**
     * Creates new form TransaksiPenjualanSparepart
     */
    

    
    public TransaksiPenjualanSparepart() {
        initComponents();
        ImageIcon ico = new ImageIcon("src/Images/s2mspeed.png");
        setIconImage(ico.getImage());
        this.setLocationRelativeTo(null);
        kursIndonesia =(DecimalFormat) DecimalFormat.getCurrencyInstance();
        formatrupiah = new DecimalFormatSymbols();
        formatrupiah.setCurrencySymbol("Rp");
        formatrupiah.setMonetaryDecimalSeparator(',');
        formatrupiah.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatrupiah);
        non_aktif();
        barangkeluar();
        nofaktur();
       
        tblpenjualan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblpenjualan.getTableHeader().setOpaque(false);
        tblpenjualan.getTableHeader().setBackground(new Color(0,153,153));
        tblpenjualan.getTableHeader().setForeground(new Color(255,255,255));
        tblpenjualan.setRowHeight(25);
        Object[] Baris = {"id", "kode ","Nama","Harga","Jumlah","Total"};
        model = new DefaultTableModel(null, Baris);
        tblpenjualan.setModel(model);
    }
    
        public void hitungg(){
        int total =0;
        for (int i=0; i<tblpenjualan.getRowCount(); i++){
            int amount = Integer.valueOf(tblpenjualan.getValueAt(i, 5).toString());
            total+=amount;
        }
        hargatotal.setText(Integer.toString(total));

    }
    
     private void non_aktif() {
        kdbarangkeluar.setEnabled(false);
        stokawalbarangkeluar.setEnabled(false);
        hargabarangkeluartxt.setEnabled(false);
        jumlahpesanankeluar.setEnabled(true);
        hitung.setEnabled(false);
        hargatotal.setEnabled(false);
        idtransaksi.setEnabled(false);
    }
    
    private void aktif() {
        kdbarangkeluar.setEnabled(false);
        barangkeluar.setEnabled(true);
        stokawalbarangkeluar.setEnabled(false);
        hargabarangkeluartxt.setEnabled(false);
        jumlahpesanankeluar.setEnabled(true);
        hitung.setEnabled(true);
        kdbarangkeluar.requestFocus();
    }
    
     private void kosong() {
        kdbarangkeluar.setText("");
        barangkeluar.setSelectedItem("");
        stokawalbarangkeluar.setText("");
        hargabarangkeluartxt.setText("");
        jumlahpesanankeluar.setText("");
        hitung.setText("");
        tambahpenjualan.setEnabled(true);
        pembayaran.setEnabled(true);
        cetakpembayaran.setEnabled(true);
    }
    
    
    private void nofaktur() {
        try {
            Connection c = konek.koneksiDb();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tb_sparepartkeluar ORDER by id_brgklr desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("id_brgklr").substring(3);
                String AN = "" + (Integer.parseInt(nofak) + 1);
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

                idtransaksi.setText("G" + Nol + AN);
            } else {
                idtransaksi.setText("G0001");
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

 
    private void barangkeluar() {
        try {
            Connection c = konek.koneksiDb();
            Statement s = c.createStatement();

            String sql = "SELECT nm_sparepart FROM tb_sparepart WHERE stk !=''";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                barangkeluar.addItem(r.getString("nm_sparepart"));
            }

            r.last();
            int jumlahdata = r.getRow();
            r.first();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void FilterAngka(KeyEvent a){
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
            JOptionPane.showMessageDialog(null, "Masukan Angka!", "WARNING", JOptionPane.WARNING_MESSAGE);
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
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        barangkeluar = new javax.swing.JComboBox<>();
        kdbarangkeluar = new javax.swing.JTextField();
        stokawalbarangkeluar = new javax.swing.JTextField();
        hargabarangkeluartxt = new javax.swing.JTextField();
        jumlahpesanankeluar = new javax.swing.JTextField();
        hitung = new javax.swing.JTextField();
        idtransaksi = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        hargatotal = new javax.swing.JTextField();
        bayartxt = new javax.swing.JTextField();
        isikembalitxt = new javax.swing.JTextField();
        pembayaran = new javax.swing.JButton();
        cetakpembayaran = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        tambahpenjualan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpenjualan = new javax.swing.JTable();
        isitotal = new javax.swing.JLabel();
        sumtotal = new javax.swing.JTextField();
        kembalitxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transaksi Penjualan Sparepart");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(1381, 100));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 40, 100));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TRANSAKSI PENJUALAN SPAREPART");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/s2mspeed.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 120));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        barangkeluar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Nama Sparepart-" }));
        barangkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barangkeluarActionPerformed(evt);
            }
        });

        kdbarangkeluar.setEditable(false);
        kdbarangkeluar.setBackground(new java.awt.Color(255, 255, 255));
        kdbarangkeluar.setForeground(new java.awt.Color(162, 162, 162));
        kdbarangkeluar.setText("Kode Sparepart");
        kdbarangkeluar.setBorder(null);
        kdbarangkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdbarangkeluarActionPerformed(evt);
            }
        });

        stokawalbarangkeluar.setEditable(false);
        stokawalbarangkeluar.setBackground(new java.awt.Color(255, 255, 255));
        stokawalbarangkeluar.setForeground(new java.awt.Color(162, 162, 162));
        stokawalbarangkeluar.setText("Stok Sparepart");
        stokawalbarangkeluar.setBorder(null);
        stokawalbarangkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stokawalbarangkeluarActionPerformed(evt);
            }
        });

        hargabarangkeluartxt.setEditable(false);
        hargabarangkeluartxt.setBackground(new java.awt.Color(255, 255, 255));
        hargabarangkeluartxt.setForeground(new java.awt.Color(162, 162, 162));
        hargabarangkeluartxt.setText("Harga Sparepart");
        hargabarangkeluartxt.setBorder(null);
        hargabarangkeluartxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargabarangkeluartxtActionPerformed(evt);
            }
        });

        jumlahpesanankeluar.setForeground(new java.awt.Color(162, 162, 162));
        jumlahpesanankeluar.setText("Jumlah Pesanan");
        jumlahpesanankeluar.setBorder(null);
        jumlahpesanankeluar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jumlahpesanankeluarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jumlahpesanankeluarFocusLost(evt);
            }
        });
        jumlahpesanankeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahpesanankeluarActionPerformed(evt);
            }
        });
        jumlahpesanankeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahpesanankeluarKeyTyped(evt);
            }
        });

        hitung.setForeground(new java.awt.Color(162, 162, 162));
        hitung.setText("Total");
        hitung.setBorder(null);
        hitung.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hitungFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hitungFocusLost(evt);
            }
        });
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });
        hitung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hitungKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hitungKeyTyped(evt);
            }
        });

        idtransaksi.setEditable(false);
        idtransaksi.setBackground(new java.awt.Color(255, 255, 255));
        idtransaksi.setForeground(new java.awt.Color(162, 162, 162));
        idtransaksi.setText("Nomor Pesanan");
        idtransaksi.setBorder(null);
        idtransaksi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idtransaksiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                idtransaksiFocusLost(evt);
            }
        });
        idtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtransaksiActionPerformed(evt);
            }
        });
        idtransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idtransaksiKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idtransaksiKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jumlahpesanankeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargabarangkeluartxt, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(hitung)
                        .addComponent(barangkeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kdbarangkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stokawalbarangkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idtransaksi))
                    .addGap(223, 223, 223)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(hargabarangkeluartxt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jumlahpesanankeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barangkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(kdbarangkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(stokawalbarangkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(140, 140, 140)
                    .addComponent(hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(idtransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        hargatotal.setFont(new java.awt.Font("Century Gothic", 1, 60)); // NOI18N
        hargatotal.setText("Rp.");
        hargatotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargatotalActionPerformed(evt);
            }
        });

        bayartxt.setForeground(new java.awt.Color(162, 162, 162));
        bayartxt.setText("UANG PEMBAYARAN");
        bayartxt.setBorder(null);
        bayartxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bayartxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                bayartxtFocusLost(evt);
            }
        });
        bayartxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayartxtActionPerformed(evt);
            }
        });
        bayartxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayartxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bayartxtKeyTyped(evt);
            }
        });

        isikembalitxt.setEditable(false);
        isikembalitxt.setBackground(new java.awt.Color(255, 255, 255));
        isikembalitxt.setText("KEMBALIAN");
        isikembalitxt.setBorder(null);
        isikembalitxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        isikembalitxt.setPreferredSize(new java.awt.Dimension(98, 14));
        isikembalitxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                isikembalitxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                isikembalitxtFocusLost(evt);
            }
        });
        isikembalitxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isikembalitxtActionPerformed(evt);
            }
        });

        pembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/money_bag_40px.png"))); // NOI18N
        pembayaran.setText("PEMBAYARAN");
        pembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pembayaranActionPerformed(evt);
            }
        });

        cetakpembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cash_receipt_40px.png"))); // NOI18N
        cetakpembayaran.setText("CETAK PEMBAYARAN");
        cetakpembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakpembayaranMouseClicked(evt);
            }
        });
        cetakpembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakpembayaranActionPerformed(evt);
            }
        });

        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit_20px.png"))); // NOI18N
        keluar.setText("KELUAR");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hargatotal)
            .addComponent(bayartxt)
            .addComponent(isikembalitxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cetakpembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addComponent(keluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(hargatotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bayartxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(isikembalitxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pembayaran)
                .addGap(26, 26, 26)
                .addComponent(cetakpembayaran)
                .addGap(18, 18, 18)
                .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tambahpenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gtk-save-as.png"))); // NOI18N
        tambahpenjualan.setText("TAMBAH TABEL PENJUALAN SPAREPART");
        tambahpenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahpenjualanActionPerformed(evt);
            }
        });

        tblpenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblpenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpenjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblpenjualan);

        kembalitxt.setText("KEMBALIAN");
        kembalitxt.setBorder(null);
        kembalitxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kembalitxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kembalitxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                kembalitxtFocusLost(evt);
            }
        });
        kembalitxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalitxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tambahpenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(isitotal))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(687, 687, 687)
                    .addComponent(sumtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(688, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(kembalitxt, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(tambahpenjualan))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(isitotal)
                .addContainerGap(258, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(456, 456, 456)
                    .addComponent(sumtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(474, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(kembalitxt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1381, 930));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void barangkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barangkeluarActionPerformed
        if (barangkeluar.getSelectedItem().equals("- Nama Sparepart -")){
            kdbarangkeluar.setText("");
        }else{
            try {
                Connection c = konek.koneksiDb();
                Statement s = c.createStatement();

                String sql = "SELECT kd_sparepart, stk, harga FROM tb_sparepart WHERE nm_sparepart ='" + barangkeluar.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);

                while (r.next()) {
                    kdbarangkeluar.setText(r.getString("kd_sparepart"));
                    kdbarangkeluar.setForeground(Color.BLACK);
                    stokawalbarangkeluar.setText(r.getString("stk"));
                    stokawalbarangkeluar.setForeground(Color.BLACK);
                    hargabarangkeluartxt.setText(r.getString("harga"));
                    hargabarangkeluartxt.setForeground(Color.BLACK);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_barangkeluarActionPerformed

    private void hargabarangkeluartxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargabarangkeluartxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargabarangkeluartxtActionPerformed

    private void jumlahpesanankeluarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jumlahpesanankeluarFocusGained
        if(jumlahpesanankeluar.getText().trim().toLowerCase().equals("jumlah pesanan")){
            jumlahpesanankeluar.setText("");
            jumlahpesanankeluar.setForeground(Color.BLACK);
        } // TODO add your handling code here:
    }//GEN-LAST:event_jumlahpesanankeluarFocusGained

    private void jumlahpesanankeluarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jumlahpesanankeluarFocusLost
        if(jumlahpesanankeluar.getText().trim().equals("")|| jumlahpesanankeluar.getText().trim().toLowerCase().equals("jumlah pesanan")){
            jumlahpesanankeluar.setText("Jumlah Pesanan");
            jumlahpesanankeluar.setForeground(new Color(162,162,162));
        } // TODO add your handling code here:
    }//GEN-LAST:event_jumlahpesanankeluarFocusLost

    private void jumlahpesanankeluarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahpesanankeluarKeyTyped
        FilterAngka(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahpesanankeluarKeyTyped

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
        // TODO add your handling code here:
        if(hitung.getText().trim().toLowerCase().equals("hitung harga tekan enter")){
            hitung.setText("");
            hitung.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_hitungActionPerformed

    private void hitungFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hitungFocusGained
        if(hitung.getText().trim().toLowerCase().equals("hitung harga tekan enter")){
            hitung.setText("");
            hitung.setForeground(Color.BLACK);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_hitungFocusGained

    private void hitungFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hitungFocusLost
        if(hitung.getText().trim().equals("")|| hitung.getText().trim().toLowerCase().equals("hitung harga tekan enter")){
            hitung.setText("Hitung Harga Tekan ENTER");
            hitung.setForeground(new Color(162,162,162));
        }// TODO add your handling code here:
    }//GEN-LAST:event_hitungFocusLost

    private void hitungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hitungKeyPressed
                // TODO add your handling code here:
    }//GEN-LAST:event_hitungKeyPressed

    private void hitungKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hitungKeyTyped
        // TODO add your handling code here:
        if(hitung.getText().trim().equals("")|| hitung.getText().trim().toLowerCase().equals("hitung harga tekan enter")){
            hitung.setText("");
            hitung.setForeground(new Color(162,162,162));
        }
    }//GEN-LAST:event_hitungKeyTyped

    private void idtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiActionPerformed

    private void idtransaksiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idtransaksiFocusGained
        if(idtransaksi.getText().trim().toLowerCase().equals("nomor pesanan")){
            idtransaksi.setText("");
            idtransaksi.setForeground(Color.BLACK);
        }     // TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiFocusGained

    private void idtransaksiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idtransaksiFocusLost
        if(idtransaksi.getText().trim().equals("")|| idtransaksi.getText().trim().toLowerCase().equals("nomor pesanan")){
            idtransaksi.setText("Nomor Pesanan");
            idtransaksi.setForeground(new Color(162,162,162));
        }// TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiFocusLost

    private void idtransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idtransaksiKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {

            if(barangkeluar.getSelectedItem().equals("- Pilih Nama Sparepart -") ||kdbarangkeluar.getText().equals("Kode Sparepart") || hargabarangkeluartxt.getText().equals("Harga Barang")|| jumlahpesanankeluar.getText().equals("Jumlah Pesanan")){
                JOptionPane.showMessageDialog(this, "Harap Lengkapi Data ","Data Belum Lengkap",JOptionPane.WARNING_MESSAGE);

            }else{
                String a = jumlahpesanankeluar.getText();
                int aa = Integer.parseInt(a);

                String b = stokawalbarangkeluar.getText();
                int bb = Integer.parseInt(b);
                if(aa > bb){
                    JOptionPane.showMessageDialog(null, "jumlah melebihi stok", "S2m Speed", JOptionPane.INFORMATION_MESSAGE);
                    jumlahpesanankeluar.setText("");
                }else{

                    if(jumlahpesanankeluar.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "ISI JUMLAH BELI !");
                    }else{
                        @SuppressWarnings("LocalVariableHidesMemberVariable")
                        int jumlah,harga,total;
                        
                        jumlah = Integer.parseInt(jumlahpesanankeluar.getText());
                        harga = Integer.parseInt(hargabarangkeluartxt.getText());
                        total = jumlah * harga;

                        idtransaksi.setText(Integer.toString(total));

                    }
                }
            }
        } // TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiKeyPressed

    private void idtransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idtransaksiKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiKeyTyped

    private void hargatotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargatotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargatotalActionPerformed

    private void tambahpenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahpenjualanActionPerformed
    try{
            String id = idtransaksi.getText();
            String kode = kdbarangkeluar.getText();
            Object namaa =  barangkeluar.getSelectedItem();
            int hargaj = Integer.parseInt(hargabarangkeluartxt.getText());
            int qty = Integer.parseInt(jumlahpesanankeluar.getText());
            int total = Integer.parseInt(hitung.getText());
                       
            
            model.addRow(new Object[]{id,kode,namaa,hargaj,qty,total});
            tblpenjualan.setModel(model);
        } catch(Exception e)
        {System.out.println("Error : "+e);
        }
    
                    String sawal=stokawalbarangkeluar.getText();
                    int awal = Integer.parseInt(sawal);
                    String sjumlah=jumlahpesanankeluar.getText();
                    int jml = Integer.parseInt(sjumlah);
                    int sisa=awal-jml;
                    String sisaa = String.valueOf(sisa);
                    
                    String sql = "update tb_sparepart set stk=? where kd_sparepart='"+kdbarangkeluar.getText()+"'";
                    try {
                    PreparedStatement stat = cn.prepareStatement(sql);
                    stat.setString(1, sisaa);

                    stat.executeUpdate(); 
                    } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data Pelanggan Gagal Di Update" + e);
                }
    
        kdbarangkeluar.setText("");
        barangkeluar.setSelectedItem("");
        stokawalbarangkeluar.setText("");
        hargabarangkeluartxt.setText("");
        jumlahpesanankeluar.setText("");
        hitung.setText("");
        hitungg();
    }//GEN-LAST:event_tambahpenjualanActionPerformed
    
    private void bayartxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayartxtActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_bayartxtActionPerformed

    private void bayartxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bayartxtFocusGained
        if(bayartxt.getText().trim().toLowerCase().equals("uang pembayaran")){
            bayartxt.setText("");
            bayartxt.setForeground(Color.BLACK);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_bayartxtFocusGained

    private void bayartxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bayartxtFocusLost
        if(bayartxt.getText().trim().equals("")|| bayartxt.getText().trim().toLowerCase().equals("uang pembayaran")){
            bayartxt.setText("UANG PEMBAYARAN");
            bayartxt.setForeground(new Color(162,162,162));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_bayartxtFocusLost

    private void bayartxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayartxtKeyReleased
        if (bayartxt.getText().equals("") || hargatotal.getText().equals("")) {
        isikembalitxt.setText("");
        } else {
        int bayar = Integer.parseInt(bayartxt.getText());
        int hrgttl = Integer.parseInt(hargatotal.getText());
        int kembali = bayar-hrgttl;
       
        isikembalitxt.setText(String.valueOf(kembali));
        } 
        
        // TODO add your handling code here:
    }//GEN-LAST:event_bayartxtKeyReleased

    private void bayartxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayartxtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_bayartxtKeyTyped

    private void isikembalitxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isikembalitxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isikembalitxtActionPerformed

    private void isikembalitxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_isikembalitxtFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_isikembalitxtFocusGained

    private void isikembalitxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_isikembalitxtFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_isikembalitxtFocusLost

    private void pembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pembayaranActionPerformed
        // TODO add your handling code here:

    String sql = "insert into tb_sparepartkeluar values(?,?,?,?,?,?,?,?,?)";
    try{
                int t = tblpenjualan.getRowCount();
        for(int i=0; i<t; i++)
        {
            String xid = tblpenjualan.getValueAt(i,0).toString();
            String xkode = tblpenjualan.getValueAt(i,1).toString();
            String xnamaa = tblpenjualan.getValueAt(i,2).toString();
            String xhargaj = tblpenjualan.getValueAt(i,3).toString();
            String xqty = tblpenjualan.getValueAt(i,4).toString();
            String xtotal = tblpenjualan.getValueAt(i,5).toString();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        PreparedStatement stat = cn.prepareStatement(sql);
        stat.setString(1, xid);
        stat.setString(2, xkode);
        stat.setString(3, xnamaa);
        stat.setString(4, xhargaj);
        stat.setString(5, xqty);
        stat.setString(6, xtotal);
        stat.setString(7, bayartxt.getText());
        stat.setString(8,isikembalitxt.getText());
        stat.setString(9, tgl);
        stat.executeUpdate();
        
        }
        JOptionPane.showMessageDialog(null, "data berhasil disimpan");
    }
    catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "data gagal disimpan");
    }
        
        kosong();
        aktif();
        nofaktur();
        
        
        
    }//GEN-LAST:event_pembayaranActionPerformed

    private void cetakpembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakpembayaranActionPerformed
        // TODO add your handling code here:
        new CetakPembayaranPenjualan().setVisible(true);
        hargatotal.setText("");
        bayartxt.setText("");
        isikembalitxt.setText("Kembalian");
    }//GEN-LAST:event_cetakpembayaranActionPerformed

    private void tblpenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpenjualanMouseClicked

        int jb;
        { 
            try{

                int i = tblpenjualan.getSelectedRow();
                tblpenjualan.setModel(model);
                hitungg();
                
                int t = tblpenjualan.getRowCount();
                for(int j=0; j<t; j++) {
                    
                String xxkd = (String) tblpenjualan.getValueAt(i, 2);
            
                String sqls = "select*from tb_sparepart where  kd_sparepart=('"+xxkd+"');";
 
                    try {
                    Statement stat = cn.createStatement();
                    ResultSet hasil = stat.executeQuery(sqls);
                    while (hasil.next()) {
                        String stkk = hasil.getString("stk");
                        String[] data = {stkk};
                    model.addRow(data);
                    int stkkk = Integer.parseInt(stkk);
                    String xxjmlh = (String) tblpenjualan.getValueAt(i, 4);
                    int xxxjml = Integer.parseInt(xxjmlh);
                    int sis = stkkk+xxxjml;
                    String sis1 = String.valueOf(sis);
                    
                    String sql = "update tb_sparepart set stk=? where kd_sparepart=('"+xxkd+"');";
                    try {
                    PreparedStatement statt = cn.prepareStatement(sql);
                    statt.setString(1, sis1);

                    statt.executeUpdate(); 
                    } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data Pelanggan Gagal Di Update" + e);
                    }        
                    
                    }                    
                    } catch (Exception e) {
                    }                

               
                String id;
                
               
                nofaktur();
                
            }
                }catch(Exception e){
            }
                    
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tblpenjualanMouseClicked

    private void kembalitxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kembalitxtFocusGained
        if(kembalitxt.getText().trim().toLowerCase().equals("kembalian")){
            kembalitxt.setText("");
            kembalitxt.setForeground(Color.BLACK);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_kembalitxtFocusGained

    private void kembalitxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kembalitxtFocusLost
        if(kembalitxt.getText().trim().equals("")|| kembalitxt.getText().trim().toLowerCase().equals("kembalian")){
            kembalitxt.setText("KEMBALIAN");
            kembalitxt.setForeground(new Color(162,162,162));
        }    // TODO add your handling code here:
    }//GEN-LAST:event_kembalitxtFocusLost

    private void kembalitxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalitxtActionPerformed
        // TODO add your handling code here:
        if(kembalitxt.getText().trim().equals("")|| kembalitxt.getText().trim().toLowerCase().equals("kembalian")){
            kembalitxt.setText("KEMBALIAN");
            kembalitxt.setForeground(new Color(162,162,162));
        }
    }//GEN-LAST:event_kembalitxtActionPerformed

    private void cetakpembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakpembayaranMouseClicked
        // TODO add your handling code here:
        new CetakPembayaranPenjualan().setVisible(true);
    }//GEN-LAST:event_cetakpembayaranMouseClicked

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
       dispose();
       FormUtamaPartman fup = new FormUtamaPartman();
       fup.show();
    }//GEN-LAST:event_keluarActionPerformed

    private void jumlahpesanankeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahpesanankeluarActionPerformed
        // TODO add your handling code here:
        if(barangkeluar.getSelectedItem().equals("- Pilih Nama Sparepart -") ||kdbarangkeluar.getText().equals("Kode Sparepart") || hargabarangkeluartxt.getText().equals("Harga Barang")|| jumlahpesanankeluar.getText().equals("Jumlah Pesanan")){
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data ","Data Belum Lengkap",JOptionPane.WARNING_MESSAGE);

        }else{
            String a = jumlahpesanankeluar.getText();
            int aa = Integer.parseInt(a);

            String b = stokawalbarangkeluar.getText();
            int bb = Integer.parseInt(b);
            if(aa > bb){
                JOptionPane.showMessageDialog(null, "jumlah melebihi stok", "S2m Speed", JOptionPane.INFORMATION_MESSAGE);
                jumlahpesanankeluar.setText("");
            }else{

                if(jumlahpesanankeluar.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "ISI JUMLAH BELI !");
                }else{
                    @SuppressWarnings("LocalVariableHidesMemberVariable")
                    int jumlah, harga, total;

                    jumlah = Integer.parseInt(jumlahpesanankeluar.getText());
                    harga = Integer.parseInt(hargabarangkeluartxt.getText());
                    total = jumlah * harga;

                    hitung.setText(Integer.toString(total));
                    
                  
                }
            }
        }
    }//GEN-LAST:event_jumlahpesanankeluarActionPerformed

    private void stokawalbarangkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stokawalbarangkeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stokawalbarangkeluarActionPerformed

    private void kdbarangkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdbarangkeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbarangkeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiPenjualanSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TransaksiPenjualanSparepart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> barangkeluar;
    private javax.swing.JTextField bayartxt;
    private javax.swing.JButton cetakpembayaran;
    private javax.swing.JTextField hargabarangkeluartxt;
    private javax.swing.JTextField hargatotal;
    private javax.swing.JTextField hitung;
    private javax.swing.JTextField idtransaksi;
    private javax.swing.JTextField isikembalitxt;
    private javax.swing.JLabel isitotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jumlahpesanankeluar;
    private javax.swing.JTextField kdbarangkeluar;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField kembalitxt;
    private javax.swing.JButton pembayaran;
    private javax.swing.JTextField stokawalbarangkeluar;
    private javax.swing.JTextField sumtotal;
    private javax.swing.JButton tambahpenjualan;
    private javax.swing.JTable tblpenjualan;
    // End of variables declaration//GEN-END:variables
}
