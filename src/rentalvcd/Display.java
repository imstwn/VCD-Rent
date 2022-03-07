/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalvcd;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Asus
 */
public class Display extends javax.swing.JFrame {

    DefaultTableModel model;
    Koneksi con = new Koneksi();
    private String ID_Trans;
    private String ID_Member;
    private String ID_VCD;
    private String ID_Status;
    private String Old_Status;
    
    /**
     * Creates new form Display
     */
    public Display() {
        initComponents();
        ComboMember();
        ComboVCD();
        ComboStatus();
        DisplayTrans();
        setNotEditable();
        setIDMembership("ID");
        
        tanggal_kembali.setEditable(false);
        
    }
    
    public void ChangePanel(JPanel panel) {
        Main_Panel.removeAll();
        Main_Panel.add(panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
    }
    
    private void DisplayDetTrans() {
        String judulDetTrans[] = {"Nama", "No HP", "Judul", "Genre", "Pinjam", "Kembali", "Status", "Sewa", "Denda", "Total"};
        model = new DefaultTableModel(judulDetTrans, 0);
        Table_Det.setModel(model);
        
        int row = Table_Det.getRowCount();
        for (int i = 0; i < row; i++) {
            model.removeRow(0);
        }
        
        try {
            ResultSet hasil = Koneksi.statement.executeQuery("SELECT * FROM det_trans");
            while (hasil.next()) {
                String data[] = {hasil.getString(2), hasil.getString(3), hasil.getString(4), hasil.getString(5), hasil.getString(6), hasil.getString(7), hasil.getString(8), hasil.getString(9), hasil.getString(10), hasil.getString(11)};
                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void DisplayTrans() {        
        String judulTrans[] = {"ID Trans", "ID Member", "ID VCD", "ID Status", "Pinjam", "Kembali", "Bayar", "Denda"};
        model = new DefaultTableModel(judulTrans, 0);
        Table_Trans.setModel(model);
        
        int row = Table_Trans.getRowCount();
        for (int i = 0; i < row; i++) {
            model.removeRow(0);
        }
        
        try {
            ResultSet hasil = Koneksi.statement.executeQuery("SELECT * FROM transaksi");
            while (hasil.next()) {
                String data[] = {hasil.getString(1), hasil.getString(2), hasil.getString(3), hasil.getString(4), hasil.getString(5), hasil.getString(6), hasil.getString(7), hasil.getString(8)};
                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void DisplayMember() {
        String judulMemb[] = {"ID Member", "Nama", "Alamat", "NO HP", "Email"};
        model = new DefaultTableModel(judulMemb, 0);
        Table_Mem.setModel(model);
        
        int row = Table_Mem.getRowCount();
        for (int i = 0; i < row; i++) {
            model.removeRow(0);
        }
        
        try {
            ResultSet hasil = Koneksi.statement.executeQuery("SELECT * FROM member");
            while (hasil.next()) {
                String data[] = {hasil.getString(1), hasil.getString(2), hasil.getString(3), hasil.getString(4), hasil.getString(5)};
                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void DisplayMovies() {
        String judulMovie[] = {"ID", "Title", "Genre", "Jml", "Harga Beli", "Tanggal Beli", "Harga Sewa", "Director", "Actor"};
        model = new DefaultTableModel(judulMovie, 0);
        Table_Film.setModel(model);
        
        int row = Table_Film.getRowCount();
        for (int i = 0; i < row; i++) {
            model.removeRow(0);
        }
        
        try {
            ResultSet hasil = Koneksi.statement.executeQuery("SELECT * FROM vcd");
            while (hasil.next()) {
                String data[] = {hasil.getString(1), hasil.getString(2), hasil.getString(3), hasil.getString(4), hasil.getString(5), hasil.getString(6), hasil.getString(7), hasil.getString(8), hasil.getString(9)};
                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void search(String s, JTable t) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        t.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(s));
    }
    
    private void ComboMember() {
        try {     
            ResultSet hasil = Koneksi.statement.executeQuery("SELECT * FROM member");
            while (hasil.next()) {
                nama_member.addItem(hasil.getString("nama"));   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ComboVCD() {
        try {     
            ResultSet hasil = Koneksi.statement.executeQuery("SELECT * FROM vcd");
            while (hasil.next()) {
                title_movie.addItem(hasil.getString("title"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ComboStatus() {
        try {     
            ResultSet hasil = Koneksi.statement.executeQuery("SELECT * FROM status");
            while (hasil.next()) {
                status.addItem(hasil.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setID_Trans(String id) {
        this.ID_Trans = id;
    }
    
    public void setID_Member(String id) {
        this.ID_Member = id;
    }
    
    public void setID_VCD(String id) {
        this.ID_VCD = id;
    }
    
    public void setID_Status(String id) {
        this.ID_Status = id;
    }
    
    public void oldStatus(String id) {
        this.Old_Status = id;
    }
    
    public String getID_Trans() {
        return this.ID_Trans;
    }
    
    public String getID_Member() {
        return this.ID_Member;
    }
    
    public String getID_VCD() {
        return this.ID_VCD;
    }
    
    public String getID_Status() {
        return this.ID_Status;
    }
    
    public String getOldStatus() {
        return this.Old_Status;
    }
    
    private void SetDIValue() {
        try {            
            String sqlID_mem = "SELECT id FROM member WHERE nama = '"+nama_member.getSelectedItem()+"'";
            ResultSet id_mem = Koneksi.statement.executeQuery(sqlID_mem);
            while (id_mem.next()) {
                setID_Member(id_mem.getString("id"));
            }
            
            String sqlID_vcd = "SELECT id FROM vcd WHERE title = '"+title_movie.getSelectedItem()+"'";
            ResultSet id_vcd = Koneksi.statement.executeQuery(sqlID_vcd);
            while (id_vcd.next()) {
                setID_VCD(id_vcd.getString("id"));
            }
            
            String sqlID_sts = "SELECT id FROM status WHERE status = '"+status.getSelectedItem()+"'";
            ResultSet id_sts = Koneksi.statement.executeQuery(sqlID_sts);
            while (id_sts.next()) {
                setID_Status(id_sts.getString("id"));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void InsertTransaction() {
        try {
            String sql = "CALL in_trans('"+getID_Member()+"', '"+getID_VCD()+"')";
            Koneksi.statement.executeQuery(sql);
            JOptionPane.showMessageDialog(null, "Tambah Transaksi Berhasil");
            DisplayTrans();
            
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void EditTransaction() {
        try {
            String editSQL;
            PreparedStatement psmt;
            if (tanggal_kembali.getText().isEmpty()) {
                editSQL = "UPDATE transaksi SET id_mem = ?, id_vcd = ?, id_sts = ?, tgl_pinjam = ? WHERE id = '"+getID_Trans()+"'";
                
                psmt = Koneksi.con.prepareStatement(editSQL);
                psmt.setString(1, getID_Member());
                psmt.setString(2, getID_VCD());
                psmt.setString(3, getID_Status());
                psmt.setString(4, tanggal_pinjam.getText());                
                psmt.executeUpdate();
            } else {
                editSQL = "UPDATE transaksi SET id_mem = ?, id_vcd = ?, id_sts = ?, tgl_pinjam = ?, tgl_kembali = ? WHERE id = '"+getID_Trans()+"'";
            
                psmt = Koneksi.con.prepareStatement(editSQL);
                psmt.setString(1, getID_Member());
                psmt.setString(2, getID_VCD());
                psmt.setString(3, getID_Status());
                psmt.setString(4, tanggal_pinjam.getText());
                psmt.setString(5, tanggal_kembali.getText());                
                psmt.executeUpdate();
            }
                        
            JOptionPane.showMessageDialog(null, "Edit Transaksi Berhasil");
            DisplayTrans();
            
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void DeleteTransaction() {
        try {
            String deleteSQL = "DELETE FROM transaksi WHERE id = '"+getID_Trans()+"'";
            Koneksi.statement.executeUpdate(deleteSQL);
            JOptionPane.showMessageDialog(null, "Hapus Transaksi Berhasil");
            DisplayTrans();
            
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void InsertMembership() {
        try {
            String sql = "CALL in_memb('"+Nama_Mem.getText()+"', '"+Alamat_Mem.getText()+"', '"+NoHP_Mem.getText()+"', '"+Email_Mem.getText()+"')";
            Koneksi.statement.executeQuery(sql);
            JOptionPane.showMessageDialog(null, "Tambah Member Berhasil");
            DisplayMember();
            
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void EditMembership() {
        try {
            String editSQL = "UPDATE member SET nama = ?, alamat = ?, nohp = ?, email = ? WHERE id = '"+getID_Member()+"'";
            PreparedStatement psmt = Koneksi.con.prepareStatement(editSQL);
            psmt.setString(1, Nama_Mem.getText());
            psmt.setString(2, Alamat_Mem.getText());
            psmt.setString(3, NoHP_Mem.getText());
            psmt.setString(4, Email_Mem.getText());                
            psmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Edit Member Berhasil");
            DisplayMember();
            
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void DeleteMembership() {
        try {
            String deleteSQL = "DELETE FROM member WHERE id = '"+getID_Member()+"'";
            Koneksi.statement.executeUpdate(deleteSQL);
            JOptionPane.showMessageDialog(null, "Hapus Member Berhasil");
            DisplayMember();
            
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setNotEditable() {
        tanggal_kembali.setEditable(false);
        tanggal_pinjam.setEditable(false);
    }
    
    private void setEditable() {
        tanggal_kembali.setEditable(true);
        tanggal_pinjam.setEditable(true);
    }
    
    private void nullForm() {
        nama_member.setSelectedIndex(-1);
        title_movie.setSelectedIndex(-1);
        status.setSelectedIndex(-1);
        tanggal_kembali.setText(null);
        tanggal_pinjam.setText(null);
        setNotEditable();
    }
    
    private void nullFormMember() {
        setIDMembership("ID");
        Nama_Mem.setText(null);
        Alamat_Mem.setText(null);
        NoHP_Mem.setText(null);
        Email_Mem.setText(null);
    }
    
    private void setIDMembership(String s) {
        jLabel13.setText(s);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_rent = new javax.swing.JButton();
        btn_list = new javax.swing.JButton();
        Main_Panel = new javax.swing.JLayeredPane();
        Layout_Rent = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_Trans = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nama_member = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        title_movie = new javax.swing.JComboBox<>();
        add_button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tgl = new javax.swing.JLabel();
        tanggal_pinjam = new javax.swing.JTextField();
        status = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tanggal_kembali = new javax.swing.JTextField();
        update_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        Layout_Trans = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Det = new javax.swing.JTable();
        searching = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Layout_Memb = new javax.swing.JPanel();
        Search_Memb = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_Mem = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Nama_Mem = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Alamat_Mem = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        NoHP_Mem = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Email_Mem = new javax.swing.JTextField();
        add_Member = new javax.swing.JButton();
        edit_Member = new javax.swing.JButton();
        delete_Member = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cancel_Member = new javax.swing.JButton();
        Layout_Film = new javax.swing.JPanel();
        Search_Film = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table_Film = new javax.swing.JTable();
        btn_mem = new javax.swing.JButton();
        btn_film = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Disc For Rent");

        btn_rent.setText("Rental VCD");
        btn_rent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rentActionPerformed(evt);
            }
        });

        btn_list.setText("Daftar Transaksi");
        btn_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listActionPerformed(evt);
            }
        });

        Main_Panel.setLayout(new java.awt.CardLayout());

        Table_Trans.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_Trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_TransMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table_Trans);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Pelanggan");

        jLabel3.setText("Movie");

        add_button.setText("Add Transaksi");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nama_member, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title_movie, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nama_member, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(title_movie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tgl.setText("Tanggal Kembali");

        status.setSelectedItem("Pilih Status");

        jLabel4.setText("Status");

        jLabel5.setText("Tanggal Pinjam");

        update_button.setText("Edit Transaksi");
        update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_buttonActionPerformed(evt);
            }
        });

        delete_button.setText("Hapus");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tgl))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tanggal_kembali, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(tanggal_pinjam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(update_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(tanggal_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tgl)
                    .addComponent(tanggal_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_button))
                .addContainerGap())
        );

        javax.swing.GroupLayout Layout_RentLayout = new javax.swing.GroupLayout(Layout_Rent);
        Layout_Rent.setLayout(Layout_RentLayout);
        Layout_RentLayout.setHorizontalGroup(
            Layout_RentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Layout_RentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layout_RentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
                .addContainerGap())
        );
        Layout_RentLayout.setVerticalGroup(
            Layout_RentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Layout_RentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );

        Main_Panel.add(Layout_Rent, "card2");

        Table_Det.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Table_Det);

        searching.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchingKeyReleased(evt);
            }
        });

        jLabel6.setText("Cari");

        javax.swing.GroupLayout Layout_TransLayout = new javax.swing.GroupLayout(Layout_Trans);
        Layout_Trans.setLayout(Layout_TransLayout);
        Layout_TransLayout.setHorizontalGroup(
            Layout_TransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Layout_TransLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layout_TransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Layout_TransLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(searching, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Layout_TransLayout.setVerticalGroup(
            Layout_TransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Layout_TransLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layout_TransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Main_Panel.add(Layout_Trans, "card3");

        Search_Memb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Search_MembKeyReleased(evt);
            }
        });

        jLabel7.setText("Cari");

        Table_Mem.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_Mem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_MemMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Table_Mem);

        jLabel8.setText("Member");

        jLabel9.setText("Nama");

        jLabel10.setText("Alamat");

        jLabel11.setText("No HP");

        jLabel12.setText("Email");

        add_Member.setText("Tambah Member");
        add_Member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_MemberActionPerformed(evt);
            }
        });

        edit_Member.setText("Update Member");
        edit_Member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_MemberActionPerformed(evt);
            }
        });

        delete_Member.setText("Delete");
        delete_Member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_MemberActionPerformed(evt);
            }
        });

        jLabel13.setText("jLabel13");

        cancel_Member.setText("Batal");
        cancel_Member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_MemberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Layout_MembLayout = new javax.swing.GroupLayout(Layout_Memb);
        Layout_Memb.setLayout(Layout_MembLayout);
        Layout_MembLayout.setHorizontalGroup(
            Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Layout_MembLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addGroup(Layout_MembLayout.createSequentialGroup()
                        .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Layout_MembLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(Search_Memb, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Layout_MembLayout.createSequentialGroup()
                                .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Nama_Mem, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(Alamat_Mem)
                                    .addComponent(NoHP_Mem))
                                .addGap(55, 55, 55)
                                .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Layout_MembLayout.createSequentialGroup()
                                        .addComponent(add_Member)
                                        .addGap(18, 18, 18)
                                        .addComponent(edit_Member))
                                    .addGroup(Layout_MembLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(Email_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cancel_Member, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(delete_Member, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        Layout_MembLayout.setVerticalGroup(
            Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Layout_MembLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Search_Memb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nama_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(Email_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancel_Member)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Alamat_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Layout_MembLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(NoHP_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_Member)
                    .addComponent(edit_Member)
                    .addComponent(delete_Member))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Main_Panel.add(Layout_Memb, "card4");

        Search_Film.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_FilmActionPerformed(evt);
            }
        });
        Search_Film.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Search_FilmKeyReleased(evt);
            }
        });

        jLabel14.setText("Cari");

        Table_Film.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_Film.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_FilmMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Table_Film);

        javax.swing.GroupLayout Layout_FilmLayout = new javax.swing.GroupLayout(Layout_Film);
        Layout_Film.setLayout(Layout_FilmLayout);
        Layout_FilmLayout.setHorizontalGroup(
            Layout_FilmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Layout_FilmLayout.createSequentialGroup()
                .addContainerGap(432, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(Search_Film, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(Layout_FilmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Layout_FilmLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Layout_FilmLayout.setVerticalGroup(
            Layout_FilmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Layout_FilmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layout_FilmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Search_Film, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(290, Short.MAX_VALUE))
            .addGroup(Layout_FilmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Layout_FilmLayout.createSequentialGroup()
                    .addContainerGap(54, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(13, Short.MAX_VALUE)))
        );

        Main_Panel.add(Layout_Film, "card5");

        btn_mem.setText("Membership");
        btn_mem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_memActionPerformed(evt);
            }
        });

        btn_film.setText("Movie Collection");
        btn_film.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btn_rent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_list)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_mem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_film)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Main_Panel)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_rent)
                    .addComponent(btn_list)
                    .addComponent(btn_mem)
                    .addComponent(btn_film))
                .addGap(18, 18, 18)
                .addComponent(Main_Panel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_rentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rentActionPerformed
        // TODO add your handling code here:
        ChangePanel(Layout_Rent);
        DisplayTrans();
    }//GEN-LAST:event_btn_rentActionPerformed

    private void btn_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listActionPerformed
        // TODO add your handling code here:
        ChangePanel(Layout_Trans);
        DisplayDetTrans();
    }//GEN-LAST:event_btn_listActionPerformed

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        // TODO add your handling code here:
        SetDIValue();
        InsertTransaction();
    }//GEN-LAST:event_add_buttonActionPerformed

    private void Table_TransMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_TransMouseClicked
        // TODO add your handling code here:
        setEditable();
        
        int row = Table_Trans.rowAtPoint(evt.getPoint());
        
        String id_trans_tab = Table_Trans.getValueAt(row, 0).toString();
        setID_Trans(id_trans_tab);
        
        String id_member_tab = Table_Trans.getValueAt(row, 1).toString();
        nama_member.setSelectedIndex(Integer.parseInt(id_member_tab) - 1);
        
        String id_vcd_tab = Table_Trans.getValueAt(row, 2).toString();
        title_movie.setSelectedIndex(Integer.parseInt(id_vcd_tab) - 1);
        
        String id_status_tab = Table_Trans.getValueAt(row, 3).toString();
        status.setSelectedIndex(Integer.parseInt(id_status_tab) - 1);
        
        String tgl_pinjam = Table_Trans.getValueAt(row, 4).toString();
        tanggal_pinjam.setText(tgl_pinjam);
        
        Object tgl_kembali = Table_Trans.getValueAt(row, 5);
        tanggal_kembali.setText((String) tgl_kembali);
        
        tanggal_kembali.setEditable(false);
        
        if (!tanggal_kembali.getText().isEmpty()) {
            tanggal_kembali.setEditable(true);
        } else { }
    }//GEN-LAST:event_Table_TransMouseClicked

    private void update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_buttonActionPerformed
        // TODO add your handling code here:
        SetDIValue();        
        EditTransaction();
        nullForm();
    }//GEN-LAST:event_update_buttonActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        // TODO add your handling code here:
        SetDIValue();
        DeleteTransaction();
        nullForm();
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void searchingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchingKeyReleased
        // TODO add your handling code here:
        search(searching.getText(), Table_Det);
    }//GEN-LAST:event_searchingKeyReleased

    private void btn_memActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_memActionPerformed
        // TODO add your handling code here:
        ChangePanel(Layout_Memb);
        DisplayMember();
    }//GEN-LAST:event_btn_memActionPerformed

    private void Search_MembKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Search_MembKeyReleased
        // TODO add your handling code here:
        search(Search_Memb.getText(), Table_Mem);
    }//GEN-LAST:event_Search_MembKeyReleased

    private void Table_MemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_MemMouseClicked
        // TODO add your handling code here:        
        int row = Table_Mem.rowAtPoint(evt.getPoint());
        
        String id_member_tab = Table_Mem.getValueAt(row, 0).toString();
        setID_Member(id_member_tab);
        setIDMembership(getID_Member());
        
        String nama_member_tab = Table_Mem.getValueAt(row, 1).toString();
        Nama_Mem.setText(nama_member_tab);
        
        String alamat_member_tab = Table_Mem.getValueAt(row, 2).toString();
        Alamat_Mem.setText(alamat_member_tab);
        
        String nohp_member_tab = Table_Mem.getValueAt(row, 3).toString();
        NoHP_Mem.setText(nohp_member_tab);
        
        String email_member_tab = Table_Mem.getValueAt(row, 4).toString();
        Email_Mem.setText(email_member_tab);
    }//GEN-LAST:event_Table_MemMouseClicked

    private void delete_MemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_MemberActionPerformed
        // TODO add your handling code here:
        DeleteMembership();
        nullFormMember();
    }//GEN-LAST:event_delete_MemberActionPerformed

    private void edit_MemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_MemberActionPerformed
        // TODO add your handling code here:
        EditMembership();
        nullFormMember();
    }//GEN-LAST:event_edit_MemberActionPerformed

    private void add_MemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_MemberActionPerformed
        // TODO add your handling code here:
        InsertMembership();
        nullFormMember();
    }//GEN-LAST:event_add_MemberActionPerformed

    private void cancel_MemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_MemberActionPerformed
        // TODO add your handling code here:
        nullFormMember();
    }//GEN-LAST:event_cancel_MemberActionPerformed

    private void Search_FilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_FilmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Search_FilmActionPerformed

    private void Search_FilmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Search_FilmKeyReleased
        // TODO add your handling code here:
        search(Search_Film.getText(), Table_Film);
    }//GEN-LAST:event_Search_FilmKeyReleased

    private void Table_FilmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_FilmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_FilmMouseClicked

    private void btn_filmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filmActionPerformed
        // TODO add your handling code here:
        ChangePanel(Layout_Film);
        DisplayMovies();
    }//GEN-LAST:event_btn_filmActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Alamat_Mem;
    private javax.swing.JTextField Email_Mem;
    private javax.swing.JPanel Layout_Film;
    private javax.swing.JPanel Layout_Memb;
    private javax.swing.JPanel Layout_Rent;
    private javax.swing.JPanel Layout_Trans;
    private javax.swing.JLayeredPane Main_Panel;
    private javax.swing.JTextField Nama_Mem;
    private javax.swing.JTextField NoHP_Mem;
    private javax.swing.JTextField Search_Film;
    private javax.swing.JTextField Search_Memb;
    private javax.swing.JTable Table_Det;
    private javax.swing.JTable Table_Film;
    private javax.swing.JTable Table_Mem;
    private javax.swing.JTable Table_Trans;
    private javax.swing.JButton add_Member;
    private javax.swing.JButton add_button;
    private javax.swing.JButton btn_film;
    private javax.swing.JButton btn_list;
    private javax.swing.JButton btn_mem;
    private javax.swing.JButton btn_rent;
    private javax.swing.JButton cancel_Member;
    private javax.swing.JButton delete_Member;
    private javax.swing.JButton delete_button;
    private javax.swing.JButton edit_Member;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> nama_member;
    private javax.swing.JTextField searching;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField tanggal_kembali;
    private javax.swing.JTextField tanggal_pinjam;
    private javax.swing.JLabel tgl;
    private javax.swing.JComboBox<String> title_movie;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables
}
