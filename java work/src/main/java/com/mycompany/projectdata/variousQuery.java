package com.mycompany.projectdata;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class variousQuery {

    private EntityManagerFactory emf = null;

    public variousQuery(EntityManagerFactory emf) {
        this.emf = emf;
    }

public List<Student> getAllStudents() {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        List<Student> students = em.createNamedQuery("Student.findAll", Student.class).getResultList();
        em.getTransaction().commit();
        return students;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}



public List<Student> getMaleStudents() {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        List<Student> maleStudents = em.createNamedQuery("Student.findBySex", Student.class)
                                       .setParameter("sex", "Male")
                                       .getResultList();
        em.getTransaction().commit();
        return maleStudents;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}

public List<Subject> getAllSubjects() {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        List<Subject> subjects = em.createNamedQuery("Subject.findAll", Subject.class).getResultList();
        em.getTransaction().commit();
        return subjects;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}



public Subject getSubjectByCode(String subjectCode) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        Subject subject = em.createNamedQuery("Subject.findBySubjectCode", Subject.class)
                            .setParameter("subjectCode", subjectCode)
                            .getSingleResult();
        em.getTransaction().commit();
        return subject;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}


public List<Section> getAllSections() {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        List<Section> sections = em.createNamedQuery("Section.findAll", Section.class).getResultList();
        em.getTransaction().commit();
        return sections;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}


public Section getSectionById(int sectionId) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        Section section = em.createNamedQuery("Section.findByCid", Section.class)
                            .setParameter("cid", sectionId)
                            .getSingleResult();
        em.getTransaction().commit();
        return section;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}

public Subject getSubjectById(int SubjectId) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        Subject subject = em.createNamedQuery("Subject.findBySubjectId", Subject.class)
                            .setParameter("SubjectId", SubjectId)
                            .getSingleResult();
        em.getTransaction().commit();
        return subject;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}


public List<Student> getStudentsBySubjectCode(String subjectCode) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        List<Student> students = em.createQuery(
                """
                select s.* 
                FROM (student s join student_subject ss on s.Sid = ss.Student_Sid)
                join subject sub on ss.Subject_SubjectId = sub.SubjectId AND sub.SubjectCode = :SubjectCode
                """,
                
                Student.class)
                                   .setParameter("SubjectCode", subjectCode)
                                   .getResultList();
        em.getTransaction().commit();
        return students;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}

public List<Attendance> getAttendanceByDate(Date date) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        List<Attendance> attendance = em.createQuery("SELECT a FROM Attendance a WHERE a.date = :date", Attendance.class)
                                       .setParameter("date", date)
                                       .getResultList();
        em.getTransaction().commit();
        return attendance;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}

public List<Attendance> getAttendanceBySectionId(int sectionId) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        List<Attendance> attendance = em.createQuery("SELECT a FROM Attendance a JOIN a.studentSubjectCollection ss WHERE ss.section.sectionId = :sectionId", Attendance.class)
                                       .setParameter("sectionId", sectionId)
                                       .getResultList();
        em.getTransaction().commit();
        return attendance;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}


public List<Student> getStudentsByDateInMultipleSubjects(Date date) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        List<Student> students = em.createQuery(
            "SELECT DISTINCT s FROM Student s JOIN s.studentSubjectCollection ss JOIN ss.attendanceCollection a WHERE a.date = :date", 
            Student.class)
            .setParameter("date", date)
            .getResultList();
        em.getTransaction().commit();
        return students;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}
 
public List<Student> getStudentsByAttendanceTimeRange(Date startTime, Date endTime) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        String sql = """
                SELECT s.* 
                FROM (student s JOIN attendance a 
                      ON s.Sid = a.Student_Sid AND a.status = 'ok')
                JOIN section ss 
                ON ss.Cid = a.Section_Cid 
                AND ss.Cstime BETWEEN :startTime AND :endTime
                """;
        List<Student> students = em.createNativeQuery(sql, Student.class)
                                   .setParameter("startTime", startTime)
                                   .setParameter("endTime", endTime)
                                   .getResultList();
        em.getTransaction().commit();
        return students;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}
public List<Student> getStudentsBySubjectIdAndsectionId(int subjectId, int sectionId) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        String sql = """
                SELECT s.* 
                FROM (student s JOIN attendance a 
                      ON s.Sid = a.Student_Sid )
                JOIN section ss 
                ON ss.Cid = :sectionId
                AND ss.Subject_SubjectId = :subjectId
                """;
        List<Student> students = em.createNativeQuery(sql, Student.class)
                                   .setParameter("subjectId", subjectId)
                                   .setParameter("sectionId", sectionId)
                                   .getResultList();
        em.getTransaction().commit();
        return students;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}

public List<Student> getStudentsBySubjectId(int subjectId) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        String sql = """
                SELECT s.* 
                FROM student s
                JOIN student_subject ss ON s.Sid = ss.Student_Sid
                WHERE ss.Subject_SubjectId = :subjectId
                """;
        List<Student> students = em.createNativeQuery(sql,Student.class)
                           .setParameter("subjectId", subjectId)
                           .getResultList();
        em.getTransaction().commit();
        return students;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}
   public List<StudentSubject> getAllStudentSubjects() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<StudentSubject> studentSubjects = em.createNamedQuery("StudentSubject.findAll", StudentSubject.class)
                                                     .getResultList();
            em.getTransaction().commit();
            return studentSubjects;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
   
   public List<Attendance> getAllAttendance() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Attendance> attendances = em.createNamedQuery("Attendance.findAll", Attendance.class)
                                                     .getResultList();
            em.getTransaction().commit();
            return attendances;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
