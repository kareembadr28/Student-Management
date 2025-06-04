package com.mycompany.projectdata;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;

public class StudentSubjectManage {

    private EntityManagerFactory emf = null;

    public StudentSubjectManage(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertStudentSubject(int studentSid, int subjectSubjectId, int id, BigDecimal degree) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            StudentSubjectPK studentSubjectPK = new StudentSubjectPK(studentSid, subjectSubjectId, id);
            StudentSubject studentSubject = new StudentSubject(studentSubjectPK, degree);
            em.persist(studentSubject);
            em.getTransaction().commit();
            System.out.println("Student-Subject inserted successfully.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateStudentSubject(int studentSid, int subjectSubjectId, int id, BigDecimal degree) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            StudentSubjectPK studentSubjectPK = new StudentSubjectPK(studentSid, subjectSubjectId, id);
            StudentSubject studentSubject = em.find(StudentSubject.class, studentSubjectPK);
            if (studentSubject != null) {
                studentSubject.setDegree(degree);
                em.merge(studentSubject);
                System.out.println("Student-Subject updated successfully.");
            } else {
                System.out.println("Student-Subject not found.");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteStudentSubject(int studentSid, int subjectSubjectId, int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            StudentSubjectPK studentSubjectPK = new StudentSubjectPK(studentSid, subjectSubjectId, id);
            StudentSubject studentSubject = em.find(StudentSubject.class, studentSubjectPK);
            if (studentSubject != null) {
                em.remove(studentSubject);
                System.out.println("Student-Subject deleted successfully.");
            } else {
                System.out.println("Student-Subject not found.");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
