package com.mycompany.projectdata;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AttendanceManage {

    private EntityManagerFactory emf = null;

    public AttendanceManage(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertAttendance(int studentSid, int sectionCid, int aid, String status) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            AttendancePK attendancePK = new AttendancePK(studentSid, sectionCid, aid);
            Attendance attendance = new Attendance(attendancePK, status);
            em.persist(attendance);
            em.getTransaction().commit();
            System.out.println("Attendance inserted successfully.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateAttendance(int studentSid, int sectionCid, int aid, String status) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            AttendancePK attendancePK = new AttendancePK(studentSid, sectionCid, aid);
            Attendance attendance = em.find(Attendance.class, attendancePK);
            if (attendance != null) {
                attendance.setStatus(status);
                em.merge(attendance);
                System.out.println("Attendance updated successfully.");
            } else {
                System.out.println("Attendance not found.");
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

    public void deleteAttendance(int studentSid, int sectionCid, int aid) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            AttendancePK attendancePK = new AttendancePK(studentSid, sectionCid, aid);
            Attendance attendance = em.find(Attendance.class, attendancePK);
            if (attendance != null) {
                em.remove(attendance);
                System.out.println("Attendance deleted successfully.");
            } else {
                System.out.println("Attendance not found.");
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
