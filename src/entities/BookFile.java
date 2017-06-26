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
@Table(name = "book_file")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookFile.findAll", query = "SELECT b FROM BookFile b")
    , @NamedQuery(name = "BookFile.findByFileId", query = "SELECT b FROM BookFile b WHERE b.fileId = :fileId")
    , @NamedQuery(name = "BookFile.findByFileName", query = "SELECT b FROM BookFile b WHERE b.fileName = :fileName")
    , @NamedQuery(name = "BookFile.findByFileMime", query = "SELECT b FROM BookFile b WHERE b.fileMime = :fileMime")})
public class BookFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "file_id")
    private Integer fileId;
    @Size(max = 200)
    @Column(name = "file_name")
    private String fileName;
    @Size(max = 100)
    @Column(name = "file_mime")
    private String fileMime;
    @OneToMany(mappedBy = "eBookfileid")
    private Collection<Ebook> ebookCollection;

    public BookFile() {
    }

    public BookFile(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileMime() {
        return fileMime;
    }

    public void setFileMime(String fileMime) {
        this.fileMime = fileMime;
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
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookFile)) {
            return false;
        }
        BookFile other = (BookFile) object;
        if ((this.fileId == null && other.fileId != null) || (this.fileId != null && !this.fileId.equals(other.fileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "d.BookFile[ fileId=" + fileId + " ]";
    }
    
}
