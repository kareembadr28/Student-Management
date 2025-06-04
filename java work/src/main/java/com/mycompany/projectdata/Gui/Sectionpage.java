/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectdata.Gui;
import com.mycompany.projectdata.Section;
import com.mycompany.projectdata.SectionManage;
import com.mycompany.projectdata.Subject;
import com.mycompany.projectdata.SubjectManage;
import com.mycompany.projectdata.variousQuery;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author hp EliteBook
 */
public class Sectionpage extends javax.swing.JFrame {

    private SectionManage sectionManage;
    private variousQuery vq;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel cardPanel;
    private JPanel insertPanel, updatePanel, deletePanel;
    private JTextField txtIdInsert, txtDateInsert, txtStartTimeInsert, txtEndTimeInsert, txtSubjectIdInsert;
    private JTextField txtIdUpdate, txtDateUpdate, txtStartTimeUpdate, txtEndTimeUpdate, txtSubjectIdUpdate;
    private JTextField txtIdDelete;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");

    public Sectionpage(SectionManage sectionManage) {
        this.sectionManage = sectionManage;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Section Management");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---------- الجدول ----------
        tableModel = new DefaultTableModel(new String[]{"ID", "Date", "Start Time", "End Time", "Subject ID"}, 0);
        table = new JTable(tableModel);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.NORTH);

        // ---------- الأزرار ----------
        JPanel buttonPanel = new JPanel();
        JButton btnInsert = new JButton("Insert");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

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
        
        btnInsert.addActionListener(e -> showCard("Insert"));
        btnUpdate.addActionListener(e -> showCard("Update"));
        btnDelete.addActionListener(e -> showCard("Delete"));

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

        setPreferredSize(new Dimension(700, 600));
        pack();
        setLocationRelativeTo(null); // Center
    }

    private JPanel createInsertPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        txtIdInsert = new JTextField(20);
        txtDateInsert = new JTextField(20); // Format: yyyy-MM-dd
        txtStartTimeInsert = new JTextField(20); // Format: HH:mm:ss
        txtEndTimeInsert = new JTextField(20); // Format: HH:mm:ss
        txtSubjectIdInsert = new JTextField(20);

        panel.add(new JLabel("Section ID:"));
        panel.add(txtIdInsert);
        panel.add(new JLabel("Section Date (yyyy-MM-dd):"));
        panel.add(txtDateInsert);
        panel.add(new JLabel("Start Time (HH:mm:ss):"));
        panel.add(txtStartTimeInsert);
        panel.add(new JLabel("End Time (HH:mm:ss):"));
        panel.add(txtEndTimeInsert);
        panel.add(new JLabel("Subject ID:"));
        panel.add(txtSubjectIdInsert);

        JButton btnConfirmInsert = new JButton("Confirm Insert");
        btnConfirmInsert.addActionListener(this::insertSection);
        panel.add(new JLabel());
        panel.add(btnConfirmInsert);

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        txtIdUpdate = new JTextField(20);
        txtDateUpdate = new JTextField(20);
        txtStartTimeUpdate = new JTextField(20);
        txtEndTimeUpdate = new JTextField(20);
        txtSubjectIdUpdate = new JTextField(20);

        panel.add(new JLabel("Section ID:"));
        panel.add(txtIdUpdate);
        panel.add(new JLabel("Section Date (yyyy-MM-dd):"));
        panel.add(txtDateUpdate);
        panel.add(new JLabel("Start Time (HH:mm:ss):"));
        panel.add(txtStartTimeUpdate);
        panel.add(new JLabel("End Time (HH:mm:ss):"));
        panel.add(txtEndTimeUpdate);
        panel.add(new JLabel("Subject ID:"));
        panel.add(txtSubjectIdUpdate);

        JButton btnConfirmUpdate = new JButton("Confirm Update");
        btnConfirmUpdate.addActionListener(this::updateSection);
        panel.add(new JLabel());
        panel.add(btnConfirmUpdate);

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        txtIdDelete = new JTextField(20);
        panel.add(new JLabel("Section ID:"));
        panel.add(txtIdDelete);

        JButton btnConfirmDelete = new JButton("Confirm Delete");
        btnConfirmDelete.addActionListener(this::deleteSection);
        panel.add(new JLabel());
        panel.add(btnConfirmDelete);

        return panel;
    }

    private void insertSection(ActionEvent e) {
        try {
            Integer id = Integer.parseInt(txtIdInsert.getText());
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(txtDateInsert.getText());
            Date startTime = new SimpleDateFormat("HH:mm:ss").parse(txtStartTimeInsert.getText());
            Date endTime = new SimpleDateFormat("HH:mm:ss").parse(txtEndTimeInsert.getText());
            Integer subjectId = Integer.parseInt(txtSubjectIdInsert.getText());

            // ربط السيكشن بالسبجكت
            vq = new variousQuery(emf);
            Subject subject = vq.getSubjectById(subjectId);

            

            sectionManage.insertSection(id, date, startTime, endTime, subjectId);
            JOptionPane.showMessageDialog(this, "Section inserted successfully.");
            refreshTable();
            clearInsertFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSection(ActionEvent e) {
        try {
            Integer id = Integer.parseInt(txtIdUpdate.getText());
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(txtDateUpdate.getText());
            Date startTime = new SimpleDateFormat("HH:mm:ss").parse(txtStartTimeUpdate.getText());
            Date endTime = new SimpleDateFormat("HH:mm:ss").parse(txtEndTimeUpdate.getText());
            Integer subjectId = Integer.parseInt(txtSubjectIdUpdate.getText());

            vq = new variousQuery(emf);
            Subject subject = vq.getSubjectById(subjectId);

            

            sectionManage.updateSection(id, date, startTime, endTime, subjectId);
            JOptionPane.showMessageDialog(this, "Section updated successfully.");
            refreshTable();
            clearUpdateFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSection(ActionEvent e) {
        try {
            Integer id = Integer.parseInt(txtIdDelete.getText());
            sectionManage.deleteSection(id);
            JOptionPane.showMessageDialog(this, "Section deleted successfully.");
            refreshTable();
            txtIdDelete.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        vq = new variousQuery(emf);
        var sections = vq.getAllSections();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        for (Section s : sections) {
            tableModel.addRow(new Object[]{
                s.getCid(),
                dateFormat.format(s.getCdate()),
                timeFormat.format(s.getCstime()),
                timeFormat.format(s.getCetime()),
                s.getSubjectSubjectId().getSubjectId()
            });
        }
    }

    private void clearInsertFields() {
        txtIdInsert.setText("");
        txtDateInsert.setText("");
        txtStartTimeInsert.setText("");
        txtEndTimeInsert.setText("");
        txtSubjectIdInsert.setText("");
    }

    private void clearUpdateFields() {
        txtIdUpdate.setText("");
        txtDateUpdate.setText("");
        txtStartTimeUpdate.setText("");
        txtEndTimeUpdate.setText("");
        txtSubjectIdUpdate.setText("");
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
            java.util.logging.Logger.getLogger(Sectionpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sectionpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sectionpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sectionpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");
    SectionManage sectionManage = new SectionManage(emf);

    java.awt.EventQueue.invokeLater(() -> new Sectionpage(sectionManage).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
