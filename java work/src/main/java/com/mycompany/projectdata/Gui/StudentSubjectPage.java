/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectdata.Gui;
import com.mycompany.projectdata.StudentSubject;
import com.mycompany.projectdata.StudentSubjectManage;
import com.mycompany.projectdata.variousQuery;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
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
public class StudentSubjectPage extends javax.swing.JFrame {
    
private StudentSubjectManage studentSubjectManage;
    private variousQuery vq;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel cardPanel;
    private JPanel insertPanel, updatePanel, deletePanel;
    private JTextField txtStudentIdInsert, txtSubjectIdInsert, txtIdInsert, txtDegreeInsert;
    private JTextField txtStudentIdUpdate, txtSubjectIdUpdate, txtIdUpdate, txtDegreeUpdate;
    private JTextField txtStudentIdDelete, txtSubjectIdDelete, txtIdDelete;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");

    public StudentSubjectPage(StudentSubjectManage studentSubjectManage) {
        this.studentSubjectManage = studentSubjectManage;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Student-Subject Management");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---------- الجدول ----------
        tableModel = new DefaultTableModel(new String[]{"Student ID", "Subject ID", "ID", "Degree"}, 0);
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
        setPreferredSize(new Dimension(700, 600));
        pack();
        setLocationRelativeTo(null); // Center
    }

    private JPanel createInsertPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        txtStudentIdInsert = new JTextField(20);
        txtSubjectIdInsert = new JTextField(20);
        txtIdInsert = new JTextField(20);
        txtDegreeInsert = new JTextField(20);

        panel.add(new JLabel("Student ID:"));
        panel.add(txtStudentIdInsert);
        panel.add(new JLabel("Subject ID:"));
        panel.add(txtSubjectIdInsert);
        panel.add(new JLabel("ID:"));
        panel.add(txtIdInsert);
        panel.add(new JLabel("Degree:"));
        panel.add(txtDegreeInsert);

        JButton btnConfirmInsert = new JButton("Confirm Insert");
        btnConfirmInsert.addActionListener(this::insertStudentSubject);
        panel.add(new JLabel());
        panel.add(btnConfirmInsert);

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        txtStudentIdUpdate = new JTextField(20);
        txtSubjectIdUpdate = new JTextField(20);
        txtIdUpdate = new JTextField(20);
        txtDegreeUpdate = new JTextField(20);

        panel.add(new JLabel("Student ID:"));
        panel.add(txtStudentIdUpdate);
        panel.add(new JLabel("Subject ID:"));
        panel.add(txtSubjectIdUpdate);
        panel.add(new JLabel("ID:"));
        panel.add(txtIdUpdate);
        panel.add(new JLabel("Degree:"));
        panel.add(txtDegreeUpdate);

        JButton btnConfirmUpdate = new JButton("Confirm Update");
        btnConfirmUpdate.addActionListener(this::updateStudentSubject);
        panel.add(new JLabel());
        panel.add(btnConfirmUpdate);

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        txtStudentIdDelete = new JTextField(20);
        txtSubjectIdDelete = new JTextField(20);
        txtIdDelete = new JTextField(20);

        panel.add(new JLabel("Student ID:"));
        panel.add(txtStudentIdDelete);
        panel.add(new JLabel("Subject ID:"));
        panel.add(txtSubjectIdDelete);
        panel.add(new JLabel("ID:"));
        panel.add(txtIdDelete);

        JButton btnConfirmDelete = new JButton("Confirm Delete");
        btnConfirmDelete.addActionListener(this::deleteStudentSubject);
        panel.add(new JLabel());
        panel.add(btnConfirmDelete);

        return panel;
    }

    private void insertStudentSubject(ActionEvent e) {
        try {
            int studentSid = Integer.parseInt(txtStudentIdInsert.getText());
            int subjectSubjectId = Integer.parseInt(txtSubjectIdInsert.getText());
            int id = Integer.parseInt(txtIdInsert.getText());
            BigDecimal degree = new BigDecimal(txtDegreeInsert.getText());

            studentSubjectManage.insertStudentSubject(studentSid, subjectSubjectId, id, degree);
            JOptionPane.showMessageDialog(this, "Student-Subject inserted successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudentSubject(ActionEvent e) {
        try {
            int studentSid = Integer.parseInt(txtStudentIdUpdate.getText());
            int subjectSubjectId = Integer.parseInt(txtSubjectIdUpdate.getText());
            int id = Integer.parseInt(txtIdUpdate.getText());
            BigDecimal degree = new BigDecimal(txtDegreeUpdate.getText());

            studentSubjectManage.updateStudentSubject(studentSid, subjectSubjectId, id, degree);
            JOptionPane.showMessageDialog(this, "Student-Subject updated successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudentSubject(ActionEvent e) {
        try {
            int studentSid = Integer.parseInt(txtStudentIdDelete.getText());
            int subjectSubjectId = Integer.parseInt(txtSubjectIdDelete.getText());
            int id = Integer.parseInt(txtIdDelete.getText());

            studentSubjectManage.deleteStudentSubject(studentSid, subjectSubjectId, id);
            JOptionPane.showMessageDialog(this, "Student-Subject deleted successfully.");
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        vq = new variousQuery(emf);
        var studentSubjects = vq.getAllStudentSubjects(); // تأكد من وجود هذه الفنكشن

        for (StudentSubject ss : studentSubjects) {
            tableModel.addRow(new Object[]{
                ss.getStudentSubjectPK().getStudentSid(),
                ss.getStudentSubjectPK().getSubjectSubjectId(),
                ss.getStudentSubjectPK().getId(),
                ss.getDegree()
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
            java.util.logging.Logger.getLogger(StudentSubjectPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentSubjectPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentSubjectPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentSubjectPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");
        StudentSubjectManage studentSubjectManage = new StudentSubjectManage(emf);
        java.awt.EventQueue.invokeLater(() -> new StudentSubjectPage(studentSubjectManage).setVisible(true));
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
