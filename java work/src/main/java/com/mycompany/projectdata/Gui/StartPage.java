/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectdata.Gui;
import com.mycompany.projectdata.AttendanceManage;
import com.mycompany.projectdata.SectionManage;
import com.mycompany.projectdata.StudentManage;
import com.mycompany.projectdata.StudentManage;
import com.mycompany.projectdata.StudentSubjectManage;
import com.mycompany.projectdata.SubjectManage;
import com.mycompany.projectdata.variousQuery;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author hp EliteBook
 */
public class StartPage extends javax.swing.JFrame {

    private JButton btnOperationOnStudent;
    private JButton btnAttendance;
    private JButton btnStudentSubject;
    private JButton btnSection;
    private JButton btnSubject;
    private JButton btnQuery;
    private StudentManage studentManage;
    private SubjectManage subjectManage;
    private SectionManage sectionManage;
    private StudentSubjectManage studentSubjectManage;
    private AttendanceManage attendanceManage;
    private variousQuery VariousQuery;

    public StartPage(StudentManage studentManage , SubjectManage subjectManage ,SectionManage sectionManage,StudentSubjectManage studentSubjectManage ,AttendanceManage attendanceManage , variousQuery VariousQuery) {
        this.studentManage = studentManage;
        this.subjectManage = subjectManage;
        this.sectionManage = sectionManage;
        this.studentSubjectManage = studentSubjectManage;
        this.attendanceManage = attendanceManage;
        this.VariousQuery = VariousQuery;
        initComponents();
    }

    public StartPage() {
        initComponents();
    }

    
    
    @Override
    public void addNotify() {
        super.addNotify();
        setupButtons(); // نضمن إنها تشتغل بعد كل حاجة تترسم صح
    }

    private void setupButtons() {
        // Create buttons
        btnOperationOnStudent = new JButton("Operation on Student");
        btnAttendance = new JButton("Attendance");
        btnStudentSubject = new JButton("Student Subject");
        btnSection = new JButton("Section");
        btnSubject = new JButton("Subject");
        btnQuery = new JButton("Query");
        
        // Set font and size for better look
        Font btnFont = new Font("Tahoma", Font.BOLD, 14);
        JButton[] buttons = {btnOperationOnStudent, btnAttendance, btnStudentSubject, btnSection, btnSubject,btnQuery};
        for (JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            btn.setBackground(new Color(70, 130, 180));
            btn.setForeground(Color.WHITE);
            btn.setPreferredSize(new Dimension(200, 40));
        }

        // Clear any existing content (if needed)
        getContentPane().removeAll();

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(btnOperationOnStudent, gbc);
        gbc.gridy = 1;
        buttonPanel.add(btnAttendance, gbc);
        gbc.gridy = 2;
        buttonPanel.add(btnStudentSubject, gbc);
        gbc.gridy = 3;
        buttonPanel.add(btnSection, gbc);
        gbc.gridy = 4;
        buttonPanel.add(btnSubject, gbc);
        gbc.gridy = 5;
        buttonPanel.add(btnQuery, gbc);

        // Add the panel to your frame
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Refresh the frame
        revalidate();
        repaint();

        // Add action listeners
        btnOperationOnStudent.addActionListener(e -> openStudentPage());
        btnAttendance.addActionListener(e -> openAttendancePage());
        btnStudentSubject.addActionListener(e -> openStudentSubjectPage());
        btnSection.addActionListener(e -> openSectionPage());
        btnSubject.addActionListener(e -> openSubjectPage());
        btnQuery.addActionListener(e -> openQueryPage());
    }

    // Open StudentPage
    private void openStudentPage() {
        // Create and show StudentPage
        new StudentPage(studentManage).setVisible(true);
        //this.setVisible(false); // Hide current frame (optional, if you want to hide the main page)
    }
     private void openSubjectPage() {
        // Create and show StudentPage
        new SubjectPage(subjectManage).setVisible(true);
        //this.setVisible(false); // Hide current frame (optional, if you want to hide the main page)
    }
     private void openSectionPage() {
        // Create and show StudentPage
        new Sectionpage(sectionManage).setVisible(true);
        //this.setVisible(false); // Hide current frame (optional, if you want to hide the main page)
    }
     private void openStudentSubjectPage() {
        // Create and show StudentPage
        new StudentSubjectPage(studentSubjectManage).setVisible(true);
        //this.setVisible(false); // Hide current frame (optional, if you want to hide the main page)
    }

      private void openAttendancePage() {
        // Create and show StudentPage
        new AttendancePage(attendanceManage).setVisible(true);
        //this.setVisible(false); // Hide current frame (optional, if you want to hide the main page)
    }

      private void openQueryPage() {
        // Create and show StudentPage
        new QueryPage(VariousQuery).setVisible(true);
        //this.setVisible(false); // Hide current frame (optional, if you want to hide the main page)
    }
      
    private void openNewFrame(String title) {
        JFrame newFrame = new JFrame(title);
        newFrame.setSize(400, 300);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newFrame.add(label);
        newFrame.setVisible(true);
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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
