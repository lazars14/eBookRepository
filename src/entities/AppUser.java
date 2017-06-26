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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "app_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a")
    , @NamedQuery(name = "AppUser.findByAppUserId", query = "SELECT a FROM AppUser a WHERE a.appUserId = :appUserId")
    , @NamedQuery(name = "AppUser.findByAppUserFirstname", query = "SELECT a FROM AppUser a WHERE a.appUserFirstname = :appUserFirstname")
    , @NamedQuery(name = "AppUser.findByAppUserLastname", query = "SELECT a FROM AppUser a WHERE a.appUserLastname = :appUserLastname")
    , @NamedQuery(name = "AppUser.findByAppUserUsername", query = "SELECT a FROM AppUser a WHERE a.appUserUsername = :appUserUsername")
    , @NamedQuery(name = "AppUser.findByAppUserPassword", query = "SELECT a FROM AppUser a WHERE a.appUserPassword = :appUserPassword")
    , @NamedQuery(name = "AppUser.findByAppUserType", query = "SELECT a FROM AppUser a WHERE a.appUserType = :appUserType")
    , @NamedQuery(name = "AppUser.findLogin", query = "SELECT u FROM AppUser u WHERE u.appUserUsername like :username AND u.appUserPassword LIKE :password AND u.appUserDeleted = false")
    , @NamedQuery(name = "AppUser.findByAppUserDeleted", query = "SELECT a FROM AppUser a WHERE a.appUserDeleted = :appUserDeleted")})
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "app_user_id")
    private Integer appUserId;
    @Size(max = 30)
    @Column(name = "app_user_firstname")
    private String appUserFirstname;
    @Size(max = 30)
    @Column(name = "app_user_lastname")
    private String appUserLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "app_user_username")
    private String appUserUsername;
    @Size(max = 10)
    @Column(name = "app_user_password")
    private String appUserPassword;
    @Size(max = 30)
    @Column(name = "app_user_type")
    private String appUserType;
    @Column(name = "app_user_deleted")
    private Boolean appUserDeleted;
    @JoinColumn(name = "app_user_category_id", referencedColumnName = "category_id")
    @ManyToOne
    private Category appUserCategoryId;

    public AppUser() {
    }

    public AppUser(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public AppUser(Integer appUserId, String appUserUsername) {
        this.appUserId = appUserId;
        this.appUserUsername = appUserUsername;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getAppUserFirstname() {
        return appUserFirstname;
    }

    public void setAppUserFirstname(String appUserFirstname) {
        this.appUserFirstname = appUserFirstname;
    }

    public String getAppUserLastname() {
        return appUserLastname;
    }

    public void setAppUserLastname(String appUserLastname) {
        this.appUserLastname = appUserLastname;
    }

    public String getAppUserUsername() {
        return appUserUsername;
    }

    public void setAppUserUsername(String appUserUsername) {
        this.appUserUsername = appUserUsername;
    }

    public String getAppUserPassword() {
        return appUserPassword;
    }

    public void setAppUserPassword(String appUserPassword) {
        this.appUserPassword = appUserPassword;
    }

    public String getAppUserType() {
        return appUserType;
    }

    public void setAppUserType(String appUserType) {
        this.appUserType = appUserType;
    }

    public Boolean getAppUserDeleted() {
        return appUserDeleted;
    }

    public void setAppUserDeleted(Boolean appUserDeleted) {
        this.appUserDeleted = appUserDeleted;
    }

    public Category getAppUserCategoryId() {
        return appUserCategoryId;
    }

    public void setAppUserCategoryId(Category appUserCategoryId) {
        this.appUserCategoryId = appUserCategoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appUserId != null ? appUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.appUserId == null && other.appUserId != null) || (this.appUserId != null && !this.appUserId.equals(other.appUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "d.AppUser[ appUserId=" + appUserId + " ]";
    }
    
}
