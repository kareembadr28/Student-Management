package com.mycompany.projectdata;

import com.mycompany.projectdata.Gui.StartPage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectData {

    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_projectData_jar_1.0-SNAPSHOTPU");
//        StudentManage studentManage = new StudentManage(emf);
//         SubjectManage subjectManage = new SubjectManage(emf);
//         SectionManage sectionManage = new SectionManage(emf);
//         StudentSubjectManage sudentSubjectManage =new  StudentSubjectManage(emf);
//         AttendanceManage attendanceManage = new AttendanceManage(emf);
//         variousQuery VariousQuery = new variousQuery(emf);
//        // تشغيل StartPage
//        java.awt.EventQueue.invokeLater(() -> {
//            new StartPage(studentManage , subjectManage,sectionManage,sudentSubjectManage,attendanceManage,VariousQuery).setVisible(true);
//        });

        StudentManage st1 = new StudentManage(emf);
        SubjectManage sub1 = new SubjectManage(emf);
        SectionManage sec1 = new SectionManage(emf);
        StudentSubjectManage studentsub1 = new StudentSubjectManage(emf);
        AttendanceManage Att = new AttendanceManage(emf);
        variousQuery Q = new variousQuery(emf);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        Date sectionDate = dateFormat.parse("2025-05-10");       // التاريخ
        Date startTime = timeFormat.parse("09:00:00");           // وقت البداية
        Date endTime = timeFormat.parse("13:00:00");

        //List<Student>  students = Q.getStudentsByAttendanceTimeRange(startTime, endTime);
        //List<Student>  students = Q.getStudentsBySubjectId(101);
        //List<Student>  students = Q.getStudentsBySubjectIdAndsectionId(101, 204);
        List<Student> students = Q.getAllStudents();
//        for(Student s : students)
//        {
//          System.out.println(s);
//        }
//        System.out.println("===============================================\n");
//        students = Q.getMaleStudents();
//        for(Student s : students)
//        {
//          System.out.println(s);
//        }

        List<Subject> subjects = Q.getAllSubjects();
//        for (Subject s : subjects) {
//            System.out.println(s);
//        }
        //System.out.println("======================================");
        //Subject sub = Q.getSubjectByCode("COMP207");
        //System.out.println(sub);

        students = Q.getStudentsBySubjectIdAndsectionId(101, 204);
        for (Student s : students) {
            System.out.println(s);
        }
//        System.out.println("===============================================");

//        students = Q.getStudentsByAttendanceTimeRange(startTime, endTime);
//        for (Student s : students) {
//            System.out.println(s);
//        }
    }
}
