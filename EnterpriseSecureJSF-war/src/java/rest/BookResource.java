/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.DataSessionBeanLocal;
import entity.BookMaster;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author root
 */
@Path("bookmaster")
@DeclareRoles({"Admin","Supervisor"})
public class BookResource {
@EJB DataSessionBeanLocal dbl;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookResource
     */
    public BookResource() {
    }

    /**
     * Retrieves representation of an instance of rest.BookResource
     * @return an instance of java.lang.String
     */
   @RolesAllowed("Admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<BookMaster> getBooks() {
        //TODO return proper representation object
     //   throw new UnsupportedOperationException();
     Collection<BookMaster> books = dbl.getAllBooks();
     System.out.println("Books="+books.toString());
     return books;
    }

    /**
     * PUT method for updating or creating an instance of BookResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
