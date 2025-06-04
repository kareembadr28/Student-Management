/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectdata.Gui;

import com.mycompany.projectdata.Attendance;
import com.mycompany.projectdata.AttendanceManage;
import com.mycompany.projectdata.variousQuery;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author hp EliteBook
 */
public class AttendancePage extends javax.swing.JFrame {
    
    private AttendanceManage attendanceManage;
    private variousQuery vq;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel cardPanel;
    private JPanel insertPanel, updatePanel, deletePanel;
    private JTextField txtStudentIdInsert, txtSectionIdInsert, txtAidInsert, txtStatusInsert;
    private JTextField txtStudentIdUpdate, txtSectionIdUpdate, txtAidUpdate, txtStatusUpdate;
    private JTextField txtStudentIdDelete, txtSectionIdDelete, txtAidDelete;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");

    public AttendancePage(AttendanceManage attendanceManage) {
        this.attendanceManage = attendanceManage;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Attendance Management");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // الجدول
        tableModel = new DefaultTableModel(new String[]{"Student ID", "Section ID", "AID", "Status"}, 0);
        table = new JTable(tableModel);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.NORTH);

        // الأزرار
        JPanel buttonPanel = new JPanel();
        JButton btnInsert = new JButton("Insert");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        // زر العودة
        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(70, 130, 180)); // لون أزرق أنيق
        btnBack.setForeground(Color.WHITE);             // لون الخط أبيض
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));

        btnInsert.addActionListener(e -> showCard("Insert"));
        btnUpdate.addActionListener(e -> showCard("Update"));
        btnDelete.addActionListener(e -> showCard("Delete"));
        btnBack.addActionListener(e -> {
            // هنا تكتب الكود اللي يفتح الصفحة التانية
            // مثلا لو عندك صفحة اسمها MainPage:
            //new StartPage().setVisible(true);
            this.dispose(); // يقفل الصفحة الحالية
        });

        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);  // أضفنا زرار الرجوع هنا
        add(buttonPanel, BorderLayout.CENTER);

        // Panels للفورمز
        cardPanel = new JPanel(new CardLayout());
        insertPanel = createInsertPanel();
        updatePanel = createUpdatePanel();
        deletePanel = createDeletePanel();
        cardPanel.add(new JPanel(), "Empty");
        cardPanel.add(insertPanel, "Insert");
        cardPanel.add(updatePanel, "Update");
        cardPanel.add(deletePanel, "Delete");
        add(cardPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(700, 600));
        pack();
        setLocationRelativeTo(null); // Center
    }

    private JPanel createInsertPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        txtStudentIdInsert = new JTextField(20);
        txtSectionIdInsert = new JTextField(20);
        txtAidInsert = new JTextField(20);
        txtStatusInsert = new JTextField(20);
        panel.add(new JLabel("Student ID:"));
        panel.add(txtStudentIdInsert);
        panel.add(new JLabel("Section ID:"));
        panel.add(txtSectionIdInsert);
        panel.add(new JLabel("AID:"));
        panel.add(txtAidInsert);
        panel.add(new JLabel("Status:"));
        panel.add(txtStatusInsert);
        JButton btnConfirmInsert = new JButton("Confirm Insert");
        btnConfirmInsert.addActionListener(this::insertAttendance);
        panel.add(new JLabel());
        panel.add(btnConfirmInsert);
        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        txtStudentIdUpdate = new JTextField(20);
        txtSectionIdUpdate = new JTextField(20);
        txtAidUpdate = new JTextField(20);
        txtStatusUpdate = new JTextField(20);
        panel.add(new JLabel("Student ID:"));
        panel.add(txtStudentIdUpdate);
        panel.add(new JLabel("Section ID:"));
        panel.add(txtSectionIdUpdate);
        panel.add(new JLabel("AID:"));
        panel.add(txtAidUpdate);
        panel.add(new JLabel("Status:"));
        panel.add(txtStatusUpdate);
        JButton btnConfirmUpdate = new JButton("Confirm Update");
        btnConfirmUpdate.addActionListener(this::updateAttendance);
        panel.add(new JLabel());
        panel.add(btnConfirmUpdate);
        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        txtStudentIdDelete = new JTextField(20);
        txtSectionIdDelete = new JTextField(20);
        txtAidDelete = new JTextField(20);
        panel.add(new JLabel("Student ID:"));
        panel.add(txtStudentIdDelete);
        panel.add(new JLabel("Section ID:"));
        panel.add(txtSectionIdDelete);
        panel.add(new JLabel("AID:"));
        panel.add(txtAidDelete);
        JButton btnConfirmDelete = new JButton("Confirm Delete");
        btnConfirmDelete.addActionListener(this::deleteAttendance);
        panel.add(new JLabel());
        panel.add(btnConfirmDelete);
        return panel;
    }

    private void insertAttendance(ActionEvent e) {
        try {
            int studentId = Integer.parseInt(txtStudentIdInsert.getText());
            int sectionId = Integer.parseInt(txtSectionIdInsert.getText());
            int aid = Integer.parseInt(txtAidInsert.getText());
            String status = txtStatusInsert.getText();
            attendanceManage.insertAttendance(studentId, sectionId, aid, status);
            JOptionPane.showMessageDialog(this, "Attendance inserted successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAttendance(ActionEvent e) {
        try {
            int studentId = Integer.parseInt(txtStudentIdUpdate.getText());
            int sectionId = Integer.parseInt(txtSectionIdUpdate.getText());
            int aid = Integer.parseInt(txtAidUpdate.getText());
            String status = txtStatusUpdate.getText();
            attendanceManage.updateAttendance(studentId, sectionId, aid, status);
            JOptionPane.showMessageDialog(this, "Attendance updated successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAttendance(ActionEvent e) {
        try {
            int studentId = Integer.parseInt(txtStudentIdDelete.getText());
            int sectionId = Integer.parseInt(txtSectionIdDelete.getText());
            int aid = Integer.parseInt(txtAidDelete.getText());
            attendanceManage.deleteAttendance(studentId, sectionId, aid);
            JOptionPane.showMessageDialog(this, "Attendance deleted successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        vq = new variousQuery(emf);
        var attendances = vq.getAllAttendance(); // لازم تتأكد الفنكشن دي موجودة
        for (Attendance a : attendances) {
            tableModel.addRow(new Object[]{
                a.getAttendancePK().getStudentSid(),
                a.getAttendancePK().getSectionCid(),
                a.getAttendancePK().getAid(),
                a.getStatus()
            });
        }
    }

    private void showCard(String name) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, name);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AttendancePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendancePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendancePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendancePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");
        AttendanceManage attendanceManage = new AttendanceManage(emf);
        java.awt.EventQueue.invokeLater(() -> new AttendancePage(attendanceManage).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
