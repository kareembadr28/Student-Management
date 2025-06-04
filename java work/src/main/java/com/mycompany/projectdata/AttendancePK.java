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
public class AttendancePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Student_Sid")
    private int studentSid;
    @Basic(optional = false)
    @Column(name = "Section_Cid")
    private int sectionCid;
    @Basic(optional = false)
    @Column(name = "Aid")
    private int aid;

    public AttendancePK() {
    }

    public AttendancePK(int studentSid, int sectionCid, int aid) {
        this.studentSid = studentSid;
        this.sectionCid = sectionCid;
        this.aid = aid;
    }

    public int getStudentSid() {
        return studentSid;
    }

    public void setStudentSid(int studentSid) {
        this.studentSid = studentSid;
    }

    public int getSectionCid() {
        return sectionCid;
    }

    public void setSectionCid(int sectionCid) {
        this.sectionCid = sectionCid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) studentSid;
        hash += (int) sectionCid;
        hash += (int) aid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttendancePK)) {
            return false;
        }
        AttendancePK other = (AttendancePK) object;
        if (this.studentSid != other.studentSid) {
            return false;
        }
        if (this.sectionCid != other.sectionCid) {
            return false;
        }
        if (this.aid != other.aid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projectdata.AttendancePK[ studentSid=" + studentSid + ", sectionCid=" + sectionCid + ", aid=" + aid + " ]";
    }
    
}
