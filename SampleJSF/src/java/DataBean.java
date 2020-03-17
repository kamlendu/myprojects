/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.BookMaster;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author root
 */
@Named(value = "dataBean")
@RequestScoped
public class DataBean {
@Inject DataLogic dl;
Collection<BookMaster> books;

    /**
     * Creates a new instance of DataBean
     */
    public DataBean() {
    }

    public Collection<BookMaster> getBooks() {
       
        books = dl.getAllBooks();
        return books;
    }

    public void setBooks(Collection<BookMaster> books) {
        this.books = books;
    }
    
    
    
}
