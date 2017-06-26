/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "book_language")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookLanguage.findAll", query = "SELECT b FROM BookLanguage b")
    , @NamedQuery(name = "BookLanguage.findAllNotDeleted", query = "SELECT b FROM BookLanguage b WHERE b.languageDeleted = false")
    , @NamedQuery(name = "BookLanguage.findByLanguageId", query = "SELECT b FROM BookLanguage b WHERE b.languageId = :languageId")
    , @NamedQuery(name = "BookLanguage.findByLanguageName", query = "SELECT b FROM BookLanguage b WHERE b.languageName = :languageName")
    , @NamedQuery(name = "BookLanguage.findByLanguageDeleted", query = "SELECT b FROM BookLanguage b WHERE b.languageDeleted = :languageDeleted")})
public class BookLanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "language_id")
    private Integer languageId;
    @Size(max = 30)
    @Column(name = "language_name")
    private String languageName;
    @Column(name = "language_deleted")
    private Boolean languageDeleted;
    @OneToMany(mappedBy = "eBooklanguage")
    private Collection<Ebook> ebookCollection;

    public BookLanguage() {
    }

    public BookLanguage(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Boolean getLanguageDeleted() {
        return languageDeleted;
    }

    public void setLanguageDeleted(Boolean languageDeleted) {
        this.languageDeleted = languageDeleted;
    }

    @XmlTransient
    public Collection<Ebook> getEbookCollection() {
        return ebookCollection;
    }

    public void setEbookCollection(Collection<Ebook> ebookCollection) {
        this.ebookCollection = ebookCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (languageId != null ? languageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookLanguage)) {
            return false;
        }
        BookLanguage other = (BookLanguage) object;
        if ((this.languageId == null && other.languageId != null) || (this.languageId != null && !this.languageId.equals(other.languageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "d.BookLanguage[ languageId=" + languageId + " ]";
    }
    
}
