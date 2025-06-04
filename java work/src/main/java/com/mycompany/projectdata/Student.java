/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectdata;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author hp EliteBook
 */
@Entity
@Table(name = "student")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findBySid", query = "SELECT s FROM Student s WHERE s.sid = :sid"),
    @NamedQuery(name = "Student.findBySfname", query = "SELECT s FROM Student s WHERE s.sfname = :sfname"),
    @NamedQuery(name = "Student.findBySlname", query = "SELECT s FROM Student s WHERE s.slname = :slname"),
    @NamedQuery(name = "Student.findBySemail", query = "SELECT s FROM Student s WHERE s.semail = :semail"),
    @NamedQuery(name = "Student.findBySex", query = "SELECT s FROM Student s WHERE s.sex = :sex")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Sid")
    private Integer sid;
    @Basic(optional = false)
    @Column(name = "Sfname")
    private String sfname;
    @Basic(optional = false)
    @Column(name = "Slname")
    private String slname;
    @Basic(optional = false)
    @Column(name = "Semail")
    private String semail;
    @Basic(optional = false)
    @Column(name = "sex")
    private String sex;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<StudentSubject> studentSubjectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<Attendance> attendanceCollection;

    public Student() {
    }

    public Student(Integer sid) {
        this.sid = sid;
    }

    public Student(Integer sid, String sfname, String slname, String semail, String sex) {
        this.sid = sid;
        this.sfname = sfname;
        this.slname = slname;
        this.semail = semail;
        this.sex = sex;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSfname() {
        return sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public String getSlname() {
        return slname;
    }

    public void setSlname(String slname) {
        this.slname = slname;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Collection<StudentSubject> getStudentSubjectCollection() {
        return studentSubjectCollection;
    }

    public void setStudentSubjectCollection(Collection<StudentSubject> studentSubjectCollection) {
        this.studentSubjectCollection = studentSubjectCollection;
    }

    public Collection<Attendance> getAttendanceCollection() {
        return attendanceCollection;
    }

    public void setAttendanceCollection(Collection<Attendance> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projectdata.Student[ sid=" + sid + "\tfname=" + sfname + "\tlname=" + slname + "\temail=" + semail +"\tsex=" + sex + " ]";
    }
    
}
