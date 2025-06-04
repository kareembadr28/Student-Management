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
@Table(name = "subject")
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
    @NamedQuery(name = "Subject.findBySubjectId", query = "SELECT s FROM Subject s WHERE s.subjectId = :subjectId"),
    @NamedQuery(name = "Subject.findBySubjectName", query = "SELECT s FROM Subject s WHERE s.subjectName = :subjectName"),
    @NamedQuery(name = "Subject.findBySubjectCode", query = "SELECT s FROM Subject s WHERE s.subjectCode = :subjectCode"),
    @NamedQuery(name = "Subject.findByCredithour", query = "SELECT s FROM Subject s WHERE s.credithour = :credithour")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SubjectId")
    private Integer subjectId;
    @Basic(optional = false)
    @Column(name = "SubjectName")
    private String subjectName;
    @Basic(optional = false)
    @Column(name = "SubjectCode")
    private String subjectCode;
    @Basic(optional = false)
    @Column(name = "credithour")
    private int credithour;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private Collection<StudentSubject> studentSubjectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectSubjectId")
    private Collection<Section> sectionCollection;

    public Subject() {
    }

    public Subject(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Subject(Integer subjectId, String subjectName, String subjectCode, int credithour) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credithour = credithour;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getCredithour() {
        return credithour;
    }

    public void setCredithour(int credithour) {
        this.credithour = credithour;
    }

    public Collection<StudentSubject> getStudentSubjectCollection() {
        return studentSubjectCollection;
    }

    public void setStudentSubjectCollection(Collection<StudentSubject> studentSubjectCollection) {
        this.studentSubjectCollection = studentSubjectCollection;
    }

    public Collection<Section> getSectionCollection() {
        return sectionCollection;
    }

    public void setSectionCollection(Collection<Section> sectionCollection) {
        this.sectionCollection = sectionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectId != null ? subjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.subjectId == null && other.subjectId != null) || (this.subjectId != null && !this.subjectId.equals(other.subjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
     return "com.mycompany.projectdata.Student[ subjectId=" + subjectId + "\tname=" + subjectName + "\tcode=" + subjectCode + "\tNohours=" + credithour + " ]";
    }
    
}