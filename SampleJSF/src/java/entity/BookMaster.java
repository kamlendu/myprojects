/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "BookMaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookMaster.findAll", query = "SELECT b FROM BookMaster b"),
    @NamedQuery(name = "BookMaster.findByBookName", query = "SELECT b FROM BookMaster b WHERE b.bookName = :bookName"),
    @NamedQuery(name = "BookMaster.findByAuthorName", query = "SELECT b FROM BookMaster b WHERE b.authorName = :authorName"),
    @NamedQuery(name = "BookMaster.findByPublisherName", query = "SELECT b FROM BookMaster b WHERE b.publisherName = :publisherName"),
    @NamedQuery(name = "BookMaster.findBySynopsis", query = "SELECT b FROM BookMaster b WHERE b.synopsis = :synopsis"),
    @NamedQuery(name = "BookMaster.findByBookID", query = "SELECT b FROM BookMaster b WHERE b.bookID = :bookID")})
public class BookMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "BookName")
    private String bookName;
    @Column(name = "AuthorName")
    private String authorName;
    @Column(name = "PublisherName")
    private String publisherName;
    @Column(name = "Synopsis")
    private String synopsis;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BookID")
    private Integer bookID;

    public BookMaster() {
    }

    public BookMaster(Integer bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookID != null ? bookID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookMaster)) {
            return false;
        }
        BookMaster other = (BookMaster) object;
        if ((this.bookID == null && other.bookID != null) || (this.bookID != null && !this.bookID.equals(other.bookID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BookMaster[ bookID=" + bookID + " ]";
    }
    
}
