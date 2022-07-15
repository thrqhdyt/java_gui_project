
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thori
 */
public class frm_transaksi extends javax.swing.JFrame {

    Map<String, String> pelanggan = new HashMap<>();
    Map<String, String> cucian = new HashMap<>();
    String pattern = "dd-MM-yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    String selectedIdTransaksi;
    String selectedNamaPelanggan;
    String selectedJenisCucian;
    String selectedTglMsk;
    String selectedTglSls;
    String selectedBerat;

    /**
     * Creates new form frm_transaksi
     */
    public ResultSet res;
    public Statement smt;
    public Connection con;

    DefaultTableModel tb = new DefaultTableModel();

    public frm_transaksi() {
        initComponents();
        koneksi();
        setUpSelectOption();
        tampilDataTransaksi();
    }

    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/laundry_db", "root", "");
            smt = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
    }

    private void setUpSelectOption() {
        try {
            res = smt.executeQuery("select id_pelanggan, nama_pelanggan from t_pelanggan");
            int no = 1;
            while (res.next()) {
                pelanggan.put(res.getString("nama_pelanggan"), res.getString("id_pelanggan"));
                jComboBox1.addItem(res.getString("nama_pelanggan"));
                no++;
            }
            res = smt.executeQuery("select * from t_jenis_cucian");
            no = 1;
            while (res.next()) {
                cucian.put(res.getString("jenis_cucian") + " - " + "Rp. " + res.getString("harga"), res.getString("id_jenis_cucian"));
                jComboBox2.addItem(res.getString("jenis_cucian"
                        + "") + " - " + "Rp. " + res.getString("harga"));
                no++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
    }

    private void setUpSelectOption(String idJenisCucian) {
        try {
            res = smt.executeQuery("select id_pelanggan, nama_pelanggan from t_pelanggan");
            int no = 1;
            while (res.next()) {
                pelanggan.put(res.getString("nama_pelanggan"), res.getString("id_pelanggan"));
                jComboBox1.addItem(res.getString("nama_pelanggan"));
                no++;
            }
            res = smt.executeQuery(String.format("select * from t_jenis_cucian where id_jenis_cucian != '%s'", idJenisCucian));
            no = 1;
            while (res.next()) {
                cucian.put(res.getString("jenis_cucian") + " - " + "Rp. " + res.getString("harga"), res.getString("id_jenis_cucian"));
                jComboBox2.addItem(res.getString("jenis_cucian"
                        + "") + " - " + "Rp. " + res.getString("harga"));
                no++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
    }

    private void tampilDataTransaksi() {
        tb.addColumn("ID Transaksi");
        tb.addColumn("Nama Pelanggan");
        tb.addColumn("Jenis Cucian");
        tb.addColumn("Harga /kg");
        tb.addColumn("Berat");
        tb.addColumn("Tgl Masuk");
        tb.addColumn("Tgl Selesai");
        tb.addColumn("Total Harga");
        jTable1.setModel(tb);

        try {
            res = smt.executeQuery("select id_transaksi, nama_pelanggan, jenis_cucian, harga, berat, tgl_masuk, tgl_selesai, harga * berat AS total_harga from t_transaksi join t_jenis_cucian on(t_transaksi.id_jenis_cucian = t_jenis_cucian.id_jenis_cucian) join t_pelanggan on(t_transaksi.id_pelanggan = t_pelanggan.id_pelanggan)");
            while (res.next()) {
                tb.addRow(new Object[]{
                    res.getString("id_transaksi"),
                    res.getString("nama_pelanggan"),
                    res.getString("jenis_cucian"),
                    res.getString("harga"),
                    res.getString("berat"),
                    res.getString("tgl_masuk"),
                    res.getString("tgl_selesai"),
                    res.getString("total_harga")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
    }

    private void clear() {
        jTextField1.setText("");
        jTextField2.setText("");
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jDateIn = new com.toedter.calendar.JDateChooser();
        jDateDone = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 146, 228));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "Nama Pelanggan", "Jenis Cucian", "Harga /kg", "Berat", "Tgl Masuk", "Tgl Selesai", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.setBackground(new java.awt.Color(255, 204, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("ID Transaksi");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Nama Pelanggan");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("Jenis Cucian");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("Berat /kg");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Tgl Masuk");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("Tgl Selesai");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 190, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jDateIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateDone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jDateIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jDateDone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 102));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(255, 204, 102));
        jButton1.setText("TAMBAH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 204, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png"))); // NOI18N
        jButton2.setText("EDIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 204, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/false.png"))); // NOI18N
        jButton3.setText("HAPUS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DATA TRANSAKSI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(258, 258, 258))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(255, 255, 255))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        utama utm = new utama();
        utm.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void refreshTable() throws SQLException {
        if (tb.getRowCount() > 0) {
            for (int i = tb.getRowCount() - 1; i > -1; i--) {
                tb.removeRow(i);
            }
        }
        res = smt.executeQuery("select id_transaksi, nama_pelanggan, jenis_cucian, harga, berat, tgl_masuk, tgl_selesai, harga * berat AS total_harga from t_transaksi join t_jenis_cucian on(t_transaksi.id_jenis_cucian = t_jenis_cucian.id_jenis_cucian) join t_pelanggan on(t_transaksi.id_pelanggan = t_pelanggan.id_pelanggan)");
        while (res.next()) {
            tb.addRow(new Object[]{
                res.getString("id_transaksi"),
                res.getString("nama_pelanggan"),
                res.getString("jenis_cucian"),
                res.getString("harga"),
                res.getString("berat"),
                res.getString("tgl_masuk"),
                res.getString("tgl_selesai"),
                res.getString("total_harga")
            });
        }
    }

    private void refreshTable(String keyword) throws SQLException {
        if (tb.getRowCount() > 0) {
            for (int i = tb.getRowCount() - 1; i > -1; i--) {
                tb.removeRow(i);
            }
        }
        String sql = "select id_transaksi, nama_pelanggan, jenis_cucian, harga, berat, tgl_masuk, tgl_selesai, harga * berat AS total_harga from t_transaksi join t_jenis_cucian on(t_transaksi.id_jenis_cucian = t_jenis_cucian.id_jenis_cucian) join t_pelanggan on(t_transaksi.id_pelanggan = t_pelanggan.id_pelanggan) where nama_pelanggan like '%s' or jenis_cucian like '%s'";
        res = smt.executeQuery(String.format(sql, "%" + keyword + "%", "%" + keyword + "%"));
        while (res.next()) {
            tb.addRow(new Object[]{
                res.getString("id_transaksi"),
                res.getString("nama_pelanggan"),
                res.getString("jenis_cucian"),
                res.getString("harga"),
                res.getString("berat"),
                res.getString("tgl_masuk"),
                res.getString("tgl_selesai"),
                res.getString("total_harga")
            });
        }
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            jTextField1.enable();
            koneksi();
            smt = con.createStatement();

            if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() || jDateIn.getDate().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Periksa lagi data yang anda input!!");
            } else if ("TAMBAH".equals(jButton1.getText())) {
                String tglMsk = sdf.format(jDateIn.getDate());
                String tglKlr = sdf.format(jDateDone.getDate());

                String sql = "insert into t_transaksi(id_transaksi, id_pelanggan, id_jenis_cucian, tgl_masuk, tgl_selesai, berat)"
                        + " values('" + jTextField1.getText() + "',"
                        + "'" + pelanggan.get(jComboBox1.getSelectedItem().toString()) + "',"
                        + "'" + cucian.get(jComboBox2.getSelectedItem().toString()) + "',"
                        + "'" + tglMsk + "',"
                        + "'" + tglKlr + "',"
                        + Integer.parseInt(jTextField2.getText()) + ")";
                smt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah");
                clear();
            } else if ("SIMPAN".equals(jButton1.getText())) {
                String tglMsk = sdf.format(jDateIn.getDate());
                String tglKlr = sdf.format(jDateDone.getDate());

                System.out.print(selectedIdTransaksi);
                String sql = "UPDATE t_transaksi SET id_jenis_cucian = '%s', tgl_masuk = '%s', tgl_selesai = '%s', berat = '%d' WHERE id_transaksi = '%s'";
                smt.executeUpdate(String.format(sql, cucian.get(jComboBox2.getSelectedItem().toString()), tglMsk, tglKlr, Integer.parseInt(jTextField2.getText()), selectedIdTransaksi));
                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
                clear();
                jTextField1.enable();
                jComboBox1.enable();
                jComboBox1.removeAllItems();
                jComboBox2.removeAllItems();
                setUpSelectOption();
                jButton1.setText("TAMBAH");
            }

            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Data Gagal Perbarui");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            jButton1.setText("SIMPAN");
            int row = jTable1.getSelectedRow();
            selectedIdTransaksi = jTable1.getValueAt(row, 0).toString();
            selectedNamaPelanggan = jTable1.getValueAt(row, 1).toString();
            selectedJenisCucian = jTable1.getValueAt(row, 2).toString() + " - " + "Rp. " + jTable1.getValueAt(row, 3);
            selectedBerat = jTable1.getValueAt(row, 4).toString();
            selectedTglMsk = jTable1.getValueAt(row, 5).toString();
            selectedTglSls = jTable1.getValueAt(row, 6).toString();

            jTextField1.setText(selectedIdTransaksi);
            jComboBox1.removeAllItems();
            jComboBox2.removeAllItems();
            jComboBox1.addItem(selectedNamaPelanggan);
            jTextField1.disable();
            jComboBox1.disable();
            jComboBox2.addItem(selectedJenisCucian);
            setUpSelectOption(cucian.get(selectedJenisCucian));
            jTextField2.setText(selectedBerat);
            jDateIn.setDate(sdf.parse(selectedTglMsk));
            jDateDone.setDate(sdf.parse(selectedTglSls));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih data yang mau diedit");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = jTable1.getSelectedRow();
        selectedIdTransaksi = jTable1.getValueAt(row, 0).toString();

        try {
            String sql = "DELETE FROM t_transaksi WHERE id_transaksi = '%s'";
            smt.executeUpdate(String.format(sql, selectedIdTransaksi));
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            refreshTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            refreshTable(jTextField3.getText());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Tidak ditemukan");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateDone;
    private com.toedter.calendar.JDateChooser jDateIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
