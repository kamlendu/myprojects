/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.BookRestClient;
import ejb.DataSessionBeanLocal;
import entity.BookMaster;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author root
 */
@Named(value = "cust")
@RequestScoped
public class BookCDIBean {
    //@EJB DataSessionBeanLocal dbl;
    Response res;
    String username;
    String password;
    BookRestClient brc;
    Collection<BookMaster> books;
    GenericType<Collection<BookMaster>> gbooks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BookRestClient getBrc() {
        return brc;
    }

    public void setBrc(BookRestClient brc) {
        this.brc = brc;
    }

    public Collection<BookMaster> getBooks() {
        res = brc.getBooks(Response.class);
        //res.
        books = res.readEntity(gbooks);
        //books = dbl.getAllBooks();
        return books;
    }

    public void setBooks(Collection<BookMaster> books) {
        this.books = books;
    }

//    public GenericType<Collection<BookMaster>> getGbooks() {
//        return gbooks;
//    }
//
//    public void setGbooks(GenericType<Collection<BookMaster>> gbooks) {
//        this.gbooks = gbooks;
//    }
//    
    

    /**
     * Creates a new instance of BookCDIBean
     */
    public BookCDIBean() {
        System.out.println("Hello BookCDI Bean ");
          HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       
     //   HttpServl session = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      HttpSession session = request.getSession();
        username = session.getAttribute("username").toString();
      password = session.getAttribute("password").toString();
       System.out.println(username + "  "+ password);
        brc = new BookRestClient(username, password);
        books = new ArrayList<BookMaster>();
        gbooks = new GenericType<Collection<BookMaster>>(){};
        
    }
    
}
