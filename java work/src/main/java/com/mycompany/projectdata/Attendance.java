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

/**
 *
 * @author hp EliteBook
 */
@Entity
@Table(name = "attendance")
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT a FROM Attendance a"),
    @NamedQuery(name = "Attendance.findByStudentSid", query = "SELECT a FROM Attendance a WHERE a.attendancePK.studentSid = :studentSid"),
    @NamedQuery(name = "Attendance.findBySectionCid", query = "SELECT a FROM Attendance a WHERE a.attendancePK.sectionCid = :sectionCid"),
    @NamedQuery(name = "Attendance.findByAid", query = "SELECT a FROM Attendance a WHERE a.attendancePK.aid = :aid"),
    @NamedQuery(name = "Attendance.findByStatus", query = "SELECT a FROM Attendance a WHERE a.status = :status")})
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AttendancePK attendancePK;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "Section_Cid", referencedColumnName = "Cid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Section section;
    @JoinColumn(name = "Student_Sid", referencedColumnName = "Sid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;

    public Attendance() {
    }

    public Attendance(AttendancePK attendancePK) {
        this.attendancePK = attendancePK;
    }

    public Attendance(AttendancePK attendancePK, String status) {
        this.attendancePK = attendancePK;
        this.status = status;
    }

    public Attendance(int studentSid, int sectionCid, int aid) {
        this.attendancePK = new AttendancePK(studentSid, sectionCid, aid);
    }

    public AttendancePK getAttendancePK() {
        return attendancePK;
    }

    public void setAttendancePK(AttendancePK attendancePK) {
        this.attendancePK = attendancePK;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attendancePK != null ? attendancePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.attendancePK == null && other.attendancePK != null) || (this.attendancePK != null && !this.attendancePK.equals(other.attendancePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projectdata.Attendance[ attendancePK=" + attendancePK + "\tStudentId=" + student.getSid() + "\tsectionId"  + section.getCid() + "\tstatus= "  + status + " ]";
    }
    
}
