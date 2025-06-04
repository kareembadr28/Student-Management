package com.mycompany.projectdata;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Date;

public class SectionManage {

    private EntityManagerFactory emf = null;

    public SectionManage(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // تعديل لإضافة Subject عند إضافة Section
    public void insertSection(Integer cid, Date cdate, Date cstime, Date cetime, Integer subjectId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // استرجاع Subject من قاعدة البيانات بناءً على الـ subjectId
            Subject subject = em.find(Subject.class, subjectId);
            if (subject == null) {
                System.out.println("Subject not found.");
                return;
            }

            Section section = new Section(cid, cdate, cstime, cetime);
            section.setSubjectSubjectId(subject);  // تعيين الـ Subject

            em.persist(section);
            em.getTransaction().commit();
            System.out.println("Section inserted successfully.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // تعديل لإضافة Subject عند تحديث Section
    public void updateSection(Integer cid, Date cdate, Date cstime, Date cetime, Integer subjectId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Section section = em.find(Section.class, cid);
            if (section != null) {
                // استرجاع Subject من قاعدة البيانات بناءً على الـ subjectId
                Subject subject = em.find(Subject.class, subjectId);
                if (subject != null) {
                    section.setSubjectSubjectId(subject);  // تعيين الـ Subject
                }
                
                section.setCdate(cdate);
                section.setCstime(cstime);
                section.setCetime(cetime);
                em.merge(section);
                em.getTransaction().commit();
                System.out.println("Section updated successfully.");
            } else {
                System.out.println("Section not found.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // حذف الـ Section بدون تغيير العلاقة مع Subject
    public void deleteSection(Integer cid) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Section section = em.find(Section.class, cid);
            if (section != null) {
                em.remove(section);
                em.getTransaction().commit();
                System.out.println("Section deleted successfully.");
            } else {
                System.out.println("Section not found.");
            }
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