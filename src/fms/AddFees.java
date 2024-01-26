/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms;

import com.mysql.cj.jdbc.*;
import java.awt.*;
import java.sql.*;
import java.sql.ResultSet;
import javax.swing.*;
import dao.ConnectionProvider;
import java.text.SimpleDateFormat;

/**
 *
 * @author Rampukar
 */
public class AddFees extends JFrame {

    /**
     * Creates new form AddFees
     */
    public AddFees() {
        initComponents();
        displayCashFirst();
        fillComboBox();
        
        int receiptNo  = getReceiptNo();
        txt_receiptNo.setText(Integer.toString(receiptNo));
    }

    public void displayCashFirst() {
        lbl_ddNo.setVisible(false);
        lbl_chequeNo.setVisible(false);
        lbl_bankName.setVisible(false);

        txt_ddNo.setVisible(false);
        txt_chequeNo.setVisible(false);
        txt_bankName.setVisible(false);
    }

    public boolean validation() {
        if (txt_receivedFrom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "please enter user name");
            return false;
        }
        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "please select a date");
            return false;
        }
        if (txt_amount.getText().equals("") || txt_amount.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "please enter amount(in numbers)");
            return false;
        }
        if (combo_paymentMode.getSelectedItem().toString().equalsIgnoreCase("cheque")){
            if (txt_chequeNo.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "please enter cheque number");
                return false;
            }
            if (txt_bankName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "please enter bank name");
                return false;
            }
        }
        if (combo_paymentMode.getSelectedItem().toString().equalsIgnoreCase("dd")){
            if (txt_ddNo.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "please enter dd no.");
                return false;
            }
            if (txt_bankName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "please enter bank name");
                return false;
            }
        }
        if(combo_paymentMode.getSelectedItem().toString().equalsIgnoreCase("card")){
            if (txt_bankName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "please enter bank name");
                return false;
            }
        }
        
        return true;
    }
    
    public void fillComboBox(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fms?useSSL=false","root","");
            PreparedStatement pst = con.prepareStatement("select cname from course");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                comboCourse.addItem(rs.getString("cname"));
            }       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getReceiptNo(){
        int receiptNo = 0;
        try {
            Connection con = ConnectionProvider.getcon();
            PreparedStatement pst = con.prepareStatement("select max(receipt_no) from fee_details"); 
            ResultSet rs = pst.executeQuery();
            
            if (rs.next() == true) {
                receiptNo = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiptNo+1;
    }
    
    public String insertData(){
        
        String status = "";
        
        int receiptNo = Integer.parseInt(txt_receiptNo.getText());
        String studentName = txt_receivedFrom.getText();
        String rollNo = txt_roll_no.getText();
        String paymentMode = combo_paymentMode.getSelectedItem().toString();
        String chequeNo = txt_chequeNo.getText();
        String bankName = txt_bankName.getText();
        String ddNo = txt_ddNo.getText();
        String courseName = txt_courseName.getText();
        float totalAmount = Float.parseFloat(txt_total.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = dateFormat.format(dateChooser.getDate());
        float initialAmount = Float.parseFloat(txt_amount.getText());
        float disAmount = Float.parseFloat(txt_discount.getText());
        float subTotal = Float.parseFloat(txt_subTotal.getText());
        float vatAmount = Float.parseFloat(txt_vat.getText());
        String totalInWords = txt_total_words.getText();
        String remark = txt_remark.getText();
        int year1 = Integer.parseInt(txt_year1.getText());
        int year2 = Integer.parseInt(txt_year2.getText());
        
        try {
            Connection con = ConnectionProvider.getcon();
            PreparedStatement pst = con.prepareStatement("insert into fee_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setInt(1, receiptNo);
            pst.setString(2, studentName);
            pst.setString(3, rollNo);
            pst.setString(4, paymentMode);
            pst.setString(5, chequeNo);
            pst.setString(6, bankName);
            pst.setString(7, ddNo);
            pst.setString(8, courseName);
            pst.setFloat(9, totalAmount);
            pst.setString(10, date);
            pst.setFloat(11, initialAmount);
            pst.setFloat(12, disAmount);
            pst.setFloat(13, subTotal);
            pst.setFloat(14, vatAmount);
            pst.setString(15, totalInWords);
            pst.setString(16, remark);
            pst.setInt(17, year1);
            pst.setInt(18, year2);
            
            int rowCount = pst.executeUpdate();
            if (rowCount == 1) {
                status = "success";
            }else{
                status = "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelsideBar = new javax.swing.JPanel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();
        panelHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        panelSearchRecord = new javax.swing.JPanel();
        btnSearchRecord = new javax.swing.JLabel();
        panelEditCourses = new javax.swing.JPanel();
        btnEditCourses = new javax.swing.JLabel();
        panelCourseList = new javax.swing.JPanel();
        btnCourseList = new javax.swing.JLabel();
        panelViewAllRecord = new javax.swing.JPanel();
        btnViewAllRecord = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        panelParent = new javax.swing.JPanel();
        lbl_ddNo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_chequeNo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_receiptNo = new javax.swing.JTextField();
        txt_ddNo = new javax.swing.JTextField();
        txt_chequeNo = new javax.swing.JTextField();
        panelChild = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_total_words = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboCourse = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        txt_courseName = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_discount = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_vat = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_remark = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_receivedFrom = new javax.swing.JTextField();
        txt_subTotal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_roll_no = new javax.swing.JTextField();
        lbl_bankName = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        combo_paymentMode = new javax.swing.JComboBox<>();
        txt_bankName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsideBar.setBackground(new java.awt.Color(0, 102, 102));
        panelsideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLogout.setBackground(new java.awt.Color(0, 102, 102));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelLogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fms_icons/logout.png"))); // NOI18N
        btnLogout.setText("   Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });
        panelLogout.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 240, 60));

        panelsideBar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, 280, 60));

        panelHome.setBackground(new java.awt.Color(0, 102, 102));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fms_icons/home.png"))); // NOI18N
        btnHome.setText("   HOME");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });
        panelHome.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 230, 60));

        panelsideBar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 280, 60));

        panelSearchRecord.setBackground(new java.awt.Color(0, 102, 102));
        panelSearchRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelSearchRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearchRecord.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnSearchRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fms_icons/search2.png"))); // NOI18N
        btnSearchRecord.setText("   Search Record");
        btnSearchRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseExited(evt);
            }
        });
        panelSearchRecord.add(btnSearchRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 240, 60));

        panelsideBar.add(panelSearchRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 280, 60));

        panelEditCourses.setBackground(new java.awt.Color(0, 102, 102));
        panelEditCourses.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelEditCourses.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditCourses.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnEditCourses.setForeground(new java.awt.Color(255, 255, 255));
        btnEditCourses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fms_icons/edit2.png"))); // NOI18N
        btnEditCourses.setText("   Edit Courses");
        btnEditCourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditCoursesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditCoursesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditCoursesMouseExited(evt);
            }
        });
        panelEditCourses.add(btnEditCourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 240, 60));

        panelsideBar.add(panelEditCourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 280, 60));

        panelCourseList.setBackground(new java.awt.Color(0, 102, 102));
        panelCourseList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelCourseList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCourseList.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnCourseList.setForeground(new java.awt.Color(255, 255, 255));
        btnCourseList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fms_icons/list_1.png"))); // NOI18N
        btnCourseList.setText("   Course List");
        btnCourseList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCourseListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCourseListMouseExited(evt);
            }
        });
        panelCourseList.add(btnCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 240, 60));

        panelsideBar.add(panelCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 280, 60));

        panelViewAllRecord.setBackground(new java.awt.Color(0, 102, 102));
        panelViewAllRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelViewAllRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnViewAllRecord.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnViewAllRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fms_icons/list_1.png"))); // NOI18N
        btnViewAllRecord.setText(" View All Record");
        btnViewAllRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewAllRecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllRecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllRecordMouseExited(evt);
            }
        });
        panelViewAllRecord.add(btnViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 240, 60));

        panelsideBar.add(panelViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 280, 60));

        panelBack.setBackground(new java.awt.Color(0, 102, 102));
        panelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelBack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fms_icons/left-arrow.png"))); // NOI18N
        btnBack.setText("   Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });
        panelBack.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 240, 60));

        panelsideBar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 280, 60));

        getContentPane().add(panelsideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 840));

        panelParent.setBackground(new java.awt.Color(0, 153, 153));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_ddNo.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lbl_ddNo.setText("DD no :");
        panelParent.add(lbl_ddNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel2.setText("Date :");
        panelParent.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, -1, -1));

        lbl_chequeNo.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lbl_chequeNo.setText("Cheque no :");
        panelParent.add(lbl_chequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel5.setText("Mode of Payment :");
        panelParent.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel6.setText("Receipt no :");
        panelParent.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        txt_receiptNo.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_receiptNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receiptNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 180, -1));

        txt_ddNo.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_ddNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ddNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_ddNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 180, -1));

        txt_chequeNo.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_chequeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chequeNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_chequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 180, -1));

        panelChild.setBackground(new java.awt.Color(0, 153, 153));
        panelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel4.setText("The following payment in the college office for the year");
        panelChild.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel8.setText("to");
        panelChild.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, -1, -1));

        txt_year2.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year2ActionPerformed(evt);
            }
        });
        panelChild.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 70, -1));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel9.setText("Roll No. :");
        panelChild.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, -1, -1));

        txt_total_words.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_total_words.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_wordsActionPerformed(evt);
            }
        });
        panelChild.add(txt_total_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 430, -1));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel10.setText("Received From :");
        panelChild.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        comboCourse.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        comboCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCourseActionPerformed(evt);
            }
        });
        panelChild.add(comboCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 260, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel11.setText("Amount(Rs.)");
        panelChild.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, -1, -1));
        panelChild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1120, 20));
        panelChild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 500, 250, 20));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel12.setText("Course :");
        panelChild.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel13.setText("Remark :");
        panelChild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel14.setText("Sub Total");
        panelChild.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, -1));

        txt_year1.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year1ActionPerformed(evt);
            }
        });
        panelChild.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 70, -1));

        txt_courseName.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_courseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_courseNameActionPerformed(evt);
            }
        });
        panelChild.add(txt_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 430, -1));

        txt_amount.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        panelChild.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 200, -1));

        txt_discount.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_discountActionPerformed(evt);
            }
        });
        panelChild.add(txt_discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 200, -1));
        panelChild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1120, 20));

        txt_vat.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_vat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vatActionPerformed(evt);
            }
        });
        panelChild.add(txt_vat, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 350, 200, -1));

        txt_total.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        panelChild.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 410, 200, -1));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel15.setText("Sr No.");
        panelChild.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
        panelChild.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, 250, 20));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel16.setText("Receiver Signature");
        panelChild.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 510, -1, -1));

        txt_remark.setColumns(20);
        txt_remark.setRows(5);
        jScrollPane1.setViewportView(txt_remark);

        panelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 430, 100));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel17.setText("Total in word :");
        panelChild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, -1));

        btn_print.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btn_print.setText("Print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        panelChild.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 560, 120, -1));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel18.setText("Head");
        panelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel19.setText("Discount");
        panelChild.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, -1, -1));

        txt_receivedFrom.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_receivedFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receivedFromActionPerformed(evt);
            }
        });
        panelChild.add(txt_receivedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 270, -1));

        txt_subTotal.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_subTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_subTotalActionPerformed(evt);
            }
        });
        panelChild.add(txt_subTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 310, 200, -1));

        jLabel20.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel20.setText("Vat@13%");
        panelChild.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, -1, -1));

        txt_roll_no.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        panelChild.add(txt_roll_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, 50, -1));

        panelParent.add(panelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1170, 650));

        lbl_bankName.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lbl_bankName.setText("Bank Name :");
        panelParent.add(lbl_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));
        panelParent.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 30, 150, 30));

        combo_paymentMode.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        combo_paymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Cheque", "Card", "DD" }));
        combo_paymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_paymentModeActionPerformed(evt);
            }
        });
        panelParent.add(combo_paymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 180, -1));

        txt_bankName.setFont(new java.awt.Font("Tw Cen MT", 0, 17)); // NOI18N
        txt_bankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bankNameActionPerformed(evt);
            }
        });
        panelParent.add(txt_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 180, -1));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1170, 840));

        setSize(new java.awt.Dimension(1582, 881));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 102, 102);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogoutMouseExited

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 102, 102);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnSearchRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchRecordMouseEntered

    private void btnSearchRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 102, 102);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchRecordMouseExited

    private void btnEditCoursesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCoursesMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        panelEditCourses.setBackground(clr);
    }//GEN-LAST:event_btnEditCoursesMouseEntered

    private void btnEditCoursesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCoursesMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 102, 102);
        panelEditCourses.setBackground(clr);
    }//GEN-LAST:event_btnEditCoursesMouseExited

    private void btnCourseListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_btnCourseListMouseEntered

    private void btnCourseListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 102, 102);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_btnCourseListMouseExited

    private void btnViewAllRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordMouseEntered

    private void btnViewAllRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 102, 102);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordMouseExited

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 102, 102);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_btnBackMouseExited

    private void txt_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year2ActionPerformed

    private void txt_receiptNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receiptNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receiptNoActionPerformed

    private void txt_ddNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ddNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ddNoActionPerformed

    private void txt_chequeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chequeNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chequeNoActionPerformed

    private void txt_receivedFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receivedFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receivedFromActionPerformed

    private void txt_total_wordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_wordsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_wordsActionPerformed

    private void txt_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year1ActionPerformed

    private void txt_courseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_courseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_courseNameActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_amountActionPerformed

    private void txt_discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_discountActionPerformed
        // TODO add your handling code here:
        Float amt = Float.parseFloat(txt_amount.getText());
        Float dis = Float.parseFloat(txt_discount.getText());
        
        Float subtotal = (float)(amt-dis);
        Float vat = (float)(subtotal*0.13);
        
        txt_subTotal.setText(subtotal.toString());
        txt_vat.setText(vat.toString());
        
        float total = subtotal+vat;
        txt_total.setText(Float.toString(total)); 
        
        txt_total_words.setText(NumberToWordsConverter.convert((int)total)+ " only");
    }//GEN-LAST:event_txt_discountActionPerformed

    private void txt_vatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vatActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        if (validation() == true) {
            String result = insertData();
            if (result.equals("success")) {
                JOptionPane.showMessageDialog(this, "record inserted successfully");
                PrintReceipt pr=new PrintReceipt();
                pr.setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "record insertion failed");
            }
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_bankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bankNameActionPerformed

    private void combo_paymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_paymentModeActionPerformed
        // TODO add your handling code here:
        if (combo_paymentMode.getSelectedIndex()==0){
            lbl_ddNo.setVisible(false);
            txt_ddNo.setVisible(false);
            lbl_chequeNo.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_bankName.setVisible(false);
            txt_bankName.setVisible(false);
        }
        if (combo_paymentMode.getSelectedIndex()==1){
            lbl_ddNo.setVisible(false);
            txt_ddNo.setVisible(false);
            lbl_chequeNo.setVisible(true);
            txt_chequeNo.setVisible(true);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
        if (combo_paymentMode.getSelectedIndex()==2){
            lbl_ddNo.setVisible(false);
            txt_ddNo.setVisible(false);
            lbl_chequeNo.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
        if (combo_paymentMode.getSelectedIndex()==3){
            lbl_ddNo.setVisible(true);
            txt_ddNo.setVisible(true);
            lbl_chequeNo.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
    }//GEN-LAST:event_combo_paymentModeActionPerformed

    private void txt_subTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_subTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_subTotalActionPerformed

    private void comboCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCourseActionPerformed
        // TODO add your handling code here:
        txt_courseName.setText(comboCourse.getSelectedItem().toString());
    }//GEN-LAST:event_comboCourseActionPerformed

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        // TODO add your handling code here:
        Home hm = new Home();
        hm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnSearchRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseClicked
        // TODO add your handling code here:
        SearchRecord sr = new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchRecordMouseClicked

    private void btnEditCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCoursesMouseClicked
        // TODO add your handling code here:
        EditCourse ec = new EditCourse();
        ec.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditCoursesMouseClicked

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnCourseListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseClicked
        // TODO add your handling code here:
        EditCourse ec = new EditCourse();
        ec.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCourseListMouseClicked

    private void btnViewAllRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseClicked
        // TODO add your handling code here:
        ViewAllRecords var = new ViewAllRecords();
        var.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewAllRecordMouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        // TODO add your handling code here:
        Login lgn = new Login();
        lgn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutMouseClicked

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
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnCourseList;
    private javax.swing.JLabel btnEditCourses;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSearchRecord;
    private javax.swing.JLabel btnViewAllRecord;
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> comboCourse;
    private javax.swing.JComboBox<String> combo_paymentMode;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_bankName;
    private javax.swing.JLabel lbl_chequeNo;
    private javax.swing.JLabel lbl_ddNo;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelCourseList;
    private javax.swing.JPanel panelEditCourses;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelSearchRecord;
    private javax.swing.JPanel panelViewAllRecord;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bankName;
    private javax.swing.JTextField txt_chequeNo;
    private javax.swing.JTextField txt_courseName;
    private javax.swing.JTextField txt_ddNo;
    private javax.swing.JTextField txt_discount;
    private javax.swing.JTextField txt_receiptNo;
    private javax.swing.JTextField txt_receivedFrom;
    private javax.swing.JTextArea txt_remark;
    private javax.swing.JTextField txt_roll_no;
    private javax.swing.JTextField txt_subTotal;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_words;
    private javax.swing.JTextField txt_vat;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables
}
