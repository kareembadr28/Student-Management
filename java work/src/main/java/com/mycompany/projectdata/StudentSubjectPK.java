/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectdata;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author hp EliteBook
 */
@Embeddable
public class StudentSubjectPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Student_Sid")
    private int studentSid;
    @Basic(optional = false)
    @Column(name = "Subject_SubjectId")
    private int subjectSubjectId;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;

    public StudentSubjectPK() {
    }

    public StudentSubjectPK(int studentSid, int subjectSubjectId, int id) {
        this.studentSid = studentSid;
        this.subjectSubjectId = subjectSubjectId;
        this.id = id;
    }

    public int getStudentSid() {
        return studentSid;
    }

    public void setStudentSid(int studentSid) {
        this.studentSid = studentSid;
    }

    public int getSubjectSubjectId() {
        return subjectSubjectId;
    }

    public void setSubjectSubjectId(int subjectSubjectId) {
        this.subjectSubjectId = subjectSubjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) studentSid;
        hash += (int) subjectSubjectId;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentSubjectPK)) {
            return false;
        }
        StudentSubjectPK other = (StudentSubjectPK) object;
        if (this.studentSid != other.studentSid) {
            return false;
        }
        if (this.subjectSubjectId != other.subjectSubjectId) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projectdata.StudentSubjectPK[ studentSid=" + studentSid + ", subjectSubjectId=" + subjectSubjectId + ", id=" + id + " ]";
    }
    
}
