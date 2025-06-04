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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author hp EliteBook
 */
@Entity
@Table(name = "section")
@NamedQueries({
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s"),
    @NamedQuery(name = "Section.findByCid", query = "SELECT s FROM Section s WHERE s.cid = :cid"),
    @NamedQuery(name = "Section.findByCdate", query = "SELECT s FROM Section s WHERE s.cdate = :cdate"),
    @NamedQuery(name = "Section.findByCstime", query = "SELECT s FROM Section s WHERE s.cstime = :cstime"),
    @NamedQuery(name = "Section.findByCetime", query = "SELECT s FROM Section s WHERE s.cetime = :cetime")})
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Cid")
    private Integer cid;
    @Basic(optional = false)
    @Column(name = "Cdate")
    @Temporal(TemporalType.DATE)
    private Date cdate;
    @Basic(optional = false)
    @Column(name = "Cstime")
    @Temporal(TemporalType.TIME)
    private Date cstime;
    @Basic(optional = false)
    @Column(name = "Cetime")
    @Temporal(TemporalType.TIME)
    private Date cetime;
    @JoinColumn(name = "Subject_SubjectId", referencedColumnName = "SubjectId")
    @ManyToOne(optional = false)
    private Subject subjectSubjectId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
    private Collection<Attendance> attendanceCollection;

    public Section() {
    }

    public Section(Integer cid) {
        this.cid = cid;
    }

    public Section(Integer cid, Date cdate, Date cstime, Date cetime) {
        this.cid = cid;
        this.cdate = cdate;
        this.cstime = cstime;
        this.cetime = cetime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getCstime() {
        return cstime;
    }

    public void setCstime(Date cstime) {
        this.cstime = cstime;
    }

    public Date getCetime() {
        return cetime;
    }

    public void setCetime(Date cetime) {
        this.cetime = cetime;
    }

    public Subject getSubjectSubjectId() {
        return subjectSubjectId;
    }

    public void setSubjectSubjectId(Subject subjectSubjectId) {
        this.subjectSubjectId = subjectSubjectId;
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
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.projectdata.Section[ cid=" + cid + "\tdate=" + cdate + "\tstime="  + cstime + "\tetime=" + cetime + "\tsubjectId=" + subjectSubjectId.getSubjectId() +  " ]";
    }
    
}
