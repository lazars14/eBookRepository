/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "ebook")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ebook.findAll", query = "SELECT e FROM Ebook e")
    , @NamedQuery(name = "Ebook.findByEBookid", query = "SELECT e FROM Ebook e WHERE e.eBookid = :eBookid")
    , @NamedQuery(name = "Ebook.findByEBooktitle", query = "SELECT e FROM Ebook e WHERE e.eBooktitle = :eBooktitle")
    , @NamedQuery(name = "Ebook.findByEBookauthor", query = "SELECT e FROM Ebook e WHERE e.eBookauthor = :eBookauthor")
    , @NamedQuery(name = "Ebook.findByEBookkeywords", query = "SELECT e FROM Ebook e WHERE e.eBookkeywords = :eBookkeywords")
    , @NamedQuery(name = "Ebook.findByEBookpublicationyear", query = "SELECT e FROM Ebook e WHERE e.eBookpublicationyear = :eBookpublicationyear")
    , @NamedQuery(name = "Ebook.findByCategoryNotDeleted", query = "SELECT e FROM Ebook e WHERE e.eBookcategory = :eBookcategory AND e.eBookdeleted = false")
    , @NamedQuery(name = "Ebook.findBooksByCategory", query = "SELECT e FROM Ebook e WHERE e.eBookcategory = :eBookcategory")
    , @NamedQuery(name = "Ebook.findByEBookdeleted", query = "SELECT e FROM Ebook e WHERE e.eBookdeleted = :eBookdeleted")})
public class Ebook implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eBook_id")
    private Integer eBookid;
    @Size(max = 80)
    @Column(name = "eBook_title")
    private String eBooktitle;
    @Size(max = 120)
    @Column(name = "eBook_author")
    private String eBookauthor;
    @Size(max = 120)
    @Column(name = "eBook_keywords")
    private String eBookkeywords;
    @Column(name = "eBook_publication_year")
    private Integer eBookpublicationyear;
    @Column(name = "eBook_deleted")
    private Boolean eBookdeleted;
    @JoinColumn(name = "eBook_language", referencedColumnName = "language_id")
    @ManyToOne
    private BookLanguage eBooklanguage;
    @JoinColumn(name = "eBook_category", referencedColumnName = "category_id")
    @ManyToOne
    private Category eBookcategory;
    @JoinColumn(name = "eBook_file_id", referencedColumnName = "file_id")
    @ManyToOne
    private BookFile eBookfileid;

    public Ebook() {
    }

    public Ebook(Integer eBookid) {
        this.eBookid = eBookid;
    }

    public Integer getEBookid() {
        return eBookid;
    }

    public void setEBookid(Integer eBookid) {
        this.eBookid = eBookid;
    }

    public String getEBooktitle() {
        return eBooktitle;
    }

    public void setEBooktitle(String eBooktitle) {
        this.eBooktitle = eBooktitle;
    }

    public String getEBookauthor() {
        return eBookauthor;
    }

    public void setEBookauthor(String eBookauthor) {
        this.eBookauthor = eBookauthor;
    }

    public String getEBookkeywords() {
        return eBookkeywords;
    }

    public void setEBookkeywords(String eBookkeywords) {
        this.eBookkeywords = eBookkeywords;
    }

    public Integer getEBookpublicationyear() {
        return eBookpublicationyear;
    }

    public void setEBookpublicationyear(Integer eBookpublicationyear) {
        this.eBookpublicationyear = eBookpublicationyear;
    }

    public Boolean getEBookdeleted() {
        return eBookdeleted;
    }

    public void setEBookdeleted(Boolean eBookdeleted) {
        this.eBookdeleted = eBookdeleted;
    }

    public BookLanguage getEBooklanguage() {
        return eBooklanguage;
    }

    public void setEBooklanguage(BookLanguage eBooklanguage) {
        this.eBooklanguage = eBooklanguage;
    }

    public Category getEBookcategory() {
        return eBookcategory;
    }

    public void setEBookcategory(Category eBookcategory) {
        this.eBookcategory = eBookcategory;
    }

    public BookFile getEBookfileid() {
        return eBookfileid;
    }

    public void setEBookfileid(BookFile eBookfileid) {
        this.eBookfileid = eBookfileid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eBookid != null ? eBookid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ebook)) {
            return false;
        }
        Ebook other = (Ebook) object;
        if ((this.eBookid == null && other.eBookid != null) || (this.eBookid != null && !this.eBookid.equals(other.eBookid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "d.Ebook[ eBookid=" + eBookid + " ]";
    }
    
}
