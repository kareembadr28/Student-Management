/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectdata.Gui;

import com.mycompany.projectdata.Subject;
import com.mycompany.projectdata.SubjectManage;
import com.mycompany.projectdata.variousQuery;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp EliteBook
 */
public class SubjectPage extends javax.swing.JFrame {

   private SubjectManage subjectManage;
    private variousQuery vq;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel cardPanel;
    private JPanel insertPanel, updatePanel, deletePanel;
    private JTextField txtIdInsert, txtNameInsert, txtCodeInsert, txtCreditHourInsert;
    private JTextField txtIdUpdate, txtNameUpdate, txtCodeUpdate, txtCreditHourUpdate;
    private JTextField txtIdDelete;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");

    public SubjectPage(SubjectManage subjectManage) {
        this.subjectManage = subjectManage;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Subject Management");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---------- الجدول ----------
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Code", "Credit Hour"}, 0);
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
        insertPanel = createInsertPanel();
        updatePanel = createUpdatePanel();
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
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        txtIdInsert = new JTextField(20);
        txtNameInsert = new JTextField(20);
        txtCodeInsert = new JTextField(20);
        txtCreditHourInsert = new JTextField(20);
        panel.add(new JLabel("Subject ID:"));
        panel.add(txtIdInsert);
        panel.add(new JLabel("Subject Name:"));
        panel.add(txtNameInsert);
        panel.add(new JLabel("Subject Code:"));
        panel.add(txtCodeInsert);
        panel.add(new JLabel("Credit Hour:"));
        panel.add(txtCreditHourInsert);

        JButton btnConfirmInsert = new JButton("Confirm Insert");
        btnConfirmInsert.addActionListener(this::insertSubject);
        panel.add(new JLabel());
        panel.add(btnConfirmInsert);
        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        txtIdUpdate = new JTextField(20);
        txtNameUpdate = new JTextField(20);
        txtCodeUpdate = new JTextField(20);
        txtCreditHourUpdate = new JTextField(20);
        panel.add(new JLabel("Subject ID:"));
        panel.add(txtIdUpdate);
        panel.add(new JLabel("Subject Name:"));
        panel.add(txtNameUpdate);
        panel.add(new JLabel("Subject Code:"));
        panel.add(txtCodeUpdate);
        panel.add(new JLabel("Credit Hour:"));
        panel.add(txtCreditHourUpdate);

        JButton btnConfirmUpdate = new JButton("Confirm Update");
        btnConfirmUpdate.addActionListener(this::updateSubject);
        panel.add(new JLabel());
        panel.add(btnConfirmUpdate);
        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        txtIdDelete = new JTextField(20);
        panel.add(new JLabel("Subject ID:"));
        panel.add(txtIdDelete);

        JButton btnConfirmDelete = new JButton("Confirm Delete");
        btnConfirmDelete.addActionListener(this::deleteSubject);
        panel.add(new JLabel());
        panel.add(btnConfirmDelete);
        return panel;
    }

    private void insertSubject(ActionEvent e) {
        try {
            Integer id = Integer.parseInt(txtIdInsert.getText());
            String name = txtNameInsert.getText();
            String code = txtCodeInsert.getText();
            Integer creditHour = Integer.parseInt(txtCreditHourInsert.getText());
            subjectManage.insertSubject(id, name, code, creditHour);
            JOptionPane.showMessageDialog(this, "Subject inserted successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSubject(ActionEvent e) {
        try {
            Integer id = Integer.parseInt(txtIdUpdate.getText());
            String name = txtNameUpdate.getText();
            String code = txtCodeUpdate.getText();
            Integer creditHour = Integer.parseInt(txtCreditHourUpdate.getText());
            subjectManage.updateSubject(id, name, code, creditHour);
            JOptionPane.showMessageDialog(this, "Subject updated successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSubject(ActionEvent e) {
        try {
            Integer id = Integer.parseInt(txtIdDelete.getText());
            subjectManage.deleteSubject(id);
            JOptionPane.showMessageDialog(this, "Subject deleted successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        vq = new variousQuery(emf);
        var subjects = vq.getAllSubjects(); // تأكد إن عندك الفنكشن دي
        for (Subject s : subjects) {
            tableModel.addRow(new Object[]{
                s.getSubjectId(), s.getSubjectName(), s.getSubjectCode(), s.getCredithour()
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
         // Initialize EntityManagerFactory and SubjectManage
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");
    SubjectManage subjectManage = new SubjectManage(emf);

    java.awt.EventQueue.invokeLater(() -> new SubjectPage(subjectManage).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
