package com.mycompany.projectdata;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.Date;

public class SubjectManage {

    private EntityManagerFactory emf = null;

    public SubjectManage(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertSubject(Integer subjectId, String subjectName, String subjectCode, int credithour) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Subject subject = new Subject(subjectId, subjectName, subjectCode, credithour);
            em.persist(subject);
            em.getTransaction().commit();
            System.out.println("Subject inserted successfully.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateSubject(Integer subjectId, String subjectName, String subjectCode, int credithour) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Subject subject = em.find(Subject.class, subjectId);
            if (subject != null) {
                subject.setSubjectName(subjectName);
                subject.setSubjectCode(subjectCode);
                subject.setCredithour(credithour);
                em.merge(subject);
                System.out.println("Subject updated successfully.");
            } else {
                System.out.println("Subject not found.");
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

    public void deleteSubject(Integer subjectId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Subject subject = em.find(Subject.class, subjectId);
            if (subject != null) {
                em.remove(subject);
                System.out.println("Subject deleted successfully.");
            } else {
                System.out.println("Subject not found.");
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
