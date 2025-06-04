package com.mycompany.projectdata;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentManage {

    private EntityManagerFactory emf = null;

    public StudentManage(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertStudent(Integer sid, String sfname, String slname, String semail, String sex) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = new Student(sid, sfname, slname, semail, sex);
            em.persist(student);
            em.getTransaction().commit();
            System.out.println("Student inserted successfully.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateStudent(Integer sid, String sfname, String slname, String semail, String sex) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, sid);
            if (student != null) {
                student.setSfname(sfname);
                student.setSlname(slname);
                student.setSemail(semail);
                student.setSex(sex);
                em.merge(student);
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student not found.");
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

    public void deleteStudent(Integer studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            if (student != null) {
                em.remove(student);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
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
