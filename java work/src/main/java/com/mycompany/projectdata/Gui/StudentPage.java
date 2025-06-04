/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectdata.Gui;

import com.mycompany.projectdata.Student;
import com.mycompany.projectdata.StudentManage;
import com.mycompany.projectdata.variousQuery;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp EliteBook
 */
public class StudentPage extends javax.swing.JFrame {
    private StudentManage studentManage;
    private variousQuery vq;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel cardPanel;
    private JPanel insertPanel, updatePanel, deletePanel;
    private JTextField txtSidInsert, txtFnameInsert, txtLnameInsert, txtEmailInsert, txtSexInsert;
    private JTextField txtSidUpdate, txtFnameUpdate, txtLnameUpdate, txtEmailUpdate, txtSexUpdate;
    private JTextField txtSidDelete;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");

    public StudentPage(StudentManage studentManage) {
        this.studentManage = studentManage;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Student Management");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---------- الجدول ----------
        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Email", "Sex"}, 0);
        table = new JTable(tableModel);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.NORTH);

        // ---------- الأزرار ----------
        JPanel buttonPanel = new JPanel();
        JButton btnInsert = new JButton("Insert");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        btnInsert.addActionListener(e -> showCard("Insert"));
        btnUpdate.addActionListener(e -> showCard("Update"));
        btnDelete.addActionListener(e -> showCard("Delete"));

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(70, 130, 180)); // لون أزرق أنيق
        btnBack.setForeground(Color.WHITE);             // لون الخط أبيض
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
         btnBack.addActionListener(e -> {
            // هنا تكتب الكود اللي يفتح الصفحة التانية
            // مثلا لو عندك صفحة اسمها MainPage:
            //new StartPage().setVisible(true);
            this.dispose(); // يقفل الصفحة الحالية
        });
         buttonPanel.add(btnBack);
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.CENTER);

        // ---------- Panels للفورمز ----------
        cardPanel = new JPanel(new CardLayout());

        // Insert Panel
        insertPanel = createInsertPanel();
        // Update Panel
        updatePanel = createUpdatePanel();
        // Delete Panel
        deletePanel = createDeletePanel();

        cardPanel.add(new JPanel(), "Empty");
        cardPanel.add(insertPanel, "Insert");
        cardPanel.add(updatePanel, "Update");
        cardPanel.add(deletePanel, "Delete");

        add(cardPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null); // Center
    }

    private JPanel createInsertPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtSidInsert = new JTextField(20);
        txtFnameInsert = new JTextField(20);
        txtLnameInsert = new JTextField(20);
        txtEmailInsert = new JTextField(20);
        txtSexInsert = new JTextField(20);

        panel.add(new JLabel("Student ID:"));
        panel.add(txtSidInsert);
        panel.add(new JLabel("First Name:"));
        panel.add(txtFnameInsert);
        panel.add(new JLabel("Last Name:"));
        panel.add(txtLnameInsert);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmailInsert);
        panel.add(new JLabel("Sex:"));
        panel.add(txtSexInsert);

        JButton btnConfirmInsert = new JButton("Confirm Insert");
        btnConfirmInsert.addActionListener(this::insertStudent);
        panel.add(new JLabel()); // Empty
        panel.add(btnConfirmInsert);

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtSidUpdate = new JTextField(20);
        txtFnameUpdate = new JTextField(20);
        txtLnameUpdate = new JTextField(20);
        txtEmailUpdate = new JTextField(20);
        txtSexUpdate = new JTextField(20);

        panel.add(new JLabel("Student ID:"));
        panel.add(txtSidUpdate);
        panel.add(new JLabel("First Name:"));
        panel.add(txtFnameUpdate);
        panel.add(new JLabel("Last Name:"));
        panel.add(txtLnameUpdate);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmailUpdate);
        panel.add(new JLabel("Sex:"));
        panel.add(txtSexUpdate);

        JButton btnConfirmUpdate = new JButton("Confirm Update");
        btnConfirmUpdate.addActionListener(this::updateStudent);
        panel.add(new JLabel()); // Empty
        panel.add(btnConfirmUpdate);

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        txtSidDelete = new JTextField(20);

        panel.add(new JLabel("Student ID:"));
        panel.add(txtSidDelete);

        JButton btnConfirmDelete = new JButton("Confirm Delete");
        btnConfirmDelete.addActionListener(this::deleteStudent);
        panel.add(new JLabel()); // Empty
        panel.add(btnConfirmDelete);

        return panel;
    }

    private void insertStudent(ActionEvent e) {
        try {
            Integer sid = Integer.parseInt(txtSidInsert.getText());
            String fname = txtFnameInsert.getText();
            String lname = txtLnameInsert.getText();
            String email = txtEmailInsert.getText();
            String sex = txtSexInsert.getText();
            studentManage.insertStudent(sid, fname, lname, email, sex);
            JOptionPane.showMessageDialog(this, "Student inserted successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent(ActionEvent e) {
        try {
            Integer sid = Integer.parseInt(txtSidUpdate.getText());
            String fname = txtFnameUpdate.getText();
            String lname = txtLnameUpdate.getText();
            String email = txtEmailUpdate.getText();
            String sex = txtSexUpdate.getText();
            studentManage.updateStudent(sid, fname, lname, email, sex);
            JOptionPane.showMessageDialog(this, "Student updated successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent(ActionEvent e) {
        try {
            Integer sid = Integer.parseInt(txtSidDelete.getText());
            studentManage.deleteStudent(sid);
            JOptionPane.showMessageDialog(this, "Student deleted successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing rows
        vq = new variousQuery(emf);
        List<Student> students = vq.getAllStudents();
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                s.getSid(), s.getSfname(), s.getSlname(), s.getSemail(), s.getSex()
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
       // Initialize StudentManage and pass it to StudentPage
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit-name");
        StudentManage studentManage = new StudentManage(emf);
        
        java.awt.EventQueue.invokeLater(() -> new StudentPage(studentManage).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
