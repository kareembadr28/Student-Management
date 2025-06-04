/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectdata;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author hp EliteBook
 */
@Entity
@Table(name = "student_subject")
@NamedQueries({
    @NamedQuery(name = "StudentSubject.findAll", query = "SELECT s FROM StudentSubject s"),
    @NamedQuery(name = "StudentSubject.findByStudentSid", query = "SELECT s FROM StudentSubject s WHERE s.studentSubjectPK.studentSid = :studentSid"),
    @NamedQuery(name = "StudentSubject.findBySubjectSubjectId", query = "SELECT s FROM StudentSubject s WHERE s.studentSubjectPK.subjectSubjectId = :subjectSubjectId"),
    @NamedQuery(name = "StudentSubject.findById", query = "SELECT s FROM StudentSubject s WHERE s.studentSubjectPK.id = :id"),
    @NamedQuery(name = "StudentSubject.findByDegree", query = "SELECT s FROM StudentSubject s WHERE s.degree = :degree")})
public class StudentSubject implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentSubjectPK studentSubjectPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "degree")
    private BigDecimal degree;
    @JoinColumn(name = "Student_Sid", referencedColumnName = "Sid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "Subject_SubjectId", referencedColumnName = "SubjectId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subject subject;

    public StudentSubject() {
    }

    public StudentSubject(StudentSubjectPK studentSubjectPK) {
        this.studentSubjectPK = studentSubjectPK;
    }

    public StudentSubject(StudentSubjectPK studentSubjectPK, BigDecimal degree) {
        this.studentSubjectPK = studentSubjectPK;
        this.degree = degree;
    }

    public StudentSubject(int studentSid, int subjectSubjectId, int id) {
        this.studentSubjectPK = new StudentSubjectPK(studentSid, subjectSubjectId, id);
    }

    public StudentSubjectPK getStudentSubjectPK() {
        return studentSubjectPK;
    }

    public void setStudentSubjectPK(StudentSubjectPK studentSubjectPK) {
        this.studentSubjectPK = studentSubjectPK;
    }

    public BigDecimal getDegree() {
        return degree;
    }

    public void setDegree(BigDecimal degree) {
        this.degree = degree;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentSubjectPK != null ? studentSubjectPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentSubject)) {
            return false;
        }
        StudentSubject other = (StudentSubject) object;
        if ((this.studentSubjectPK == null && other.studentSubjectPK != null) || (this.studentSubjectPK != null && !this.studentSubjectPK.equals(other.studentSubjectPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    return "com.mycompany.projectdata.Student[ StudentId=" + student.getSid() + "\tsubjectId=" + subject.getSubjectId() + "\tdegree=" + degree + "\tid=" + studentSubjectPK + " ]";

    }
    
}
