/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectdata.Gui;
import com.mycompany.projectdata.Attendance;
import com.mycompany.projectdata.Section;
import com.mycompany.projectdata.Student;
import com.mycompany.projectdata.variousQuery;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author hp EliteBook
 */
public class QueryPage extends javax.swing.JFrame {

    private variousQuery query;
    private JTable table;
    private DefaultTableModel tableModel;

    public QueryPage(variousQuery query) {
        this.query = query;
        setTitle("Student Attendance Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add text fields and buttons for each function
        JTextField subjectCodeField = new JTextField(10);
        JTextField sectionIdField = new JTextField(10);
        JTextField dateField = new JTextField(10);
        JButton btnGetStudentsBySubjectCode = new JButton("Get Students by Subject Code");
        JButton btnGetSectionById = new JButton("Get Section by Id");
        JButton btnGetAttendanceByDate = new JButton("Get Attendance by Date");

        // Add components to panel
        panel.add(new JLabel("Enter Subject Code:"));
        panel.add(subjectCodeField);
        panel.add(btnGetStudentsBySubjectCode);
        panel.add(new JLabel("Enter Section ID:"));
        panel.add(sectionIdField);
        panel.add(btnGetSectionById);
        panel.add(new JLabel("Enter Date (yyyy-MM-dd):"));
        panel.add(dateField);
        panel.add(btnGetAttendanceByDate);

        // JTable for displaying results
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        // Button Actions
        btnGetStudentsBySubjectCode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int subjectCode =Integer.parseInt(subjectCodeField.getText());
                if (subjectCode > 0) {
                    List<Student> students = query.getStudentsBySubjectId(subjectCode);
                    if (students.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No students found for this subject code.");
                    } else {
                        updateTable(students);
                        JOptionPane.showMessageDialog(null, "Students data fetched successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid subject code.");
                }
            }
        });

        btnGetAttendanceByDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateField.getText());
                    List<Attendance> attendance = query.getAttendanceByDate(date);
                    if (attendance.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No attendance found for this date.");
                    } else {
                        updateTable(attendance);
                        JOptionPane.showMessageDialog(null, "Attendance data fetched successfully.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Use yyyy-MM-dd.");
                }
            }
        });
        btnGetSectionById.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
         try {
                    int SectionId = Integer.parseInt(sectionIdField.getText());
                    Section attendance = query.getSectionById(SectionId);
                    if (attendance==null) {
                        JOptionPane.showMessageDialog(null, "No attendance found for this date.");
                    } else {
                        updateTable((List<?>) attendance);
                        JOptionPane.showMessageDialog(null, "Attendance data fetched successfully.");   
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Use yyyy-MM-dd.");
                }
         }
        });

        add(panel);
    }

    private void updateTable(List<?> list) {
        // Clear the existing table model
        tableModel.setRowCount(0);
        if (list != null && !list.isEmpty()) {
            if (list.get(0) instanceof Student) {
                tableModel.setColumnIdentifiers(new Object[]{"ID", "Name", "Sex"});
                for (Object obj : list) {
                    Student student = (Student) obj;
                    tableModel.addRow(new Object[]{student.getSid(), student.getSfname(), student.getSex()});
                }
            } else if (list.get(0) instanceof Attendance) {
                tableModel.setColumnIdentifiers(new Object[]{"Student ID", "Section ID", "Status", "Aid"});
                for (Object obj : list) {
                    Attendance attendance = (Attendance) obj;
                    tableModel.addRow(new Object[]{
                        attendance.getStudent().getSid(),
                        attendance.getSection().getCid(),
                        attendance.getStatus(),
                        attendance.getAttendancePK().getAid()
                    });
                }
            }
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
            java.util.logging.Logger.getLogger(QueryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QueryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QueryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QueryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        // Sample EntityManagerFactory initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");
        variousQuery query = new variousQuery(emf);

        // Start the GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QueryPage(query).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
