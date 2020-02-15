/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.PublishBeanLocal;
import entity.Address;
import entity.Customer;
import entity.Subscription;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author root
 */
//@DeclareRoles({"Admin","Supervisor"})
@Path("publish")
public class PublishRest {

    @EJB
    PublishBeanLocal pb;

    public PublishRest() {

    }

    @RolesAllowed("Admin")
    @POST
    @Path("createcust")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createCustomer(Customer c) {
        pb.createCustomer(c);
    }

    @RolesAllowed("Supervisor")
    @PUT
    @Path("updateCust/{custid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateCustomer(Customer c, @PathParam("custid") Integer custId) {
        pb.updateCustomer(c, custId);
    }

    @RolesAllowed("Admin")
    @DELETE
    @Path("deleteCust/{custid}")
    public void removeCustomer(@PathParam("custid") Integer custId) {
        pb.removeCustomer(custId);
    }

    @RolesAllowed("Admin")
    // @PermitAll
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Customer> getAllCustomers() {

        return (Collection<Customer>) pb.getAllCustomers();
    }

    @PermitAll
    @GET
    @Path("getbyfirst/{firstname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Customer> getAllCustomersByfirstName(@PathParam("firstname") String firstName) {

        return pb.getAllCustomersByfirstName(firstName);
    }

    @RolesAllowed("Supervisor")
    @PUT
    @Path("addaddresstocust/{custid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addAddressToCustomer(Address address, @PathParam("custid") Integer custId) {

        pb.addAddressToCustomer(address, custId);
    }

    @PermitAll
    @PUT
    @Path("addaddresstocust/{addressid}/{custid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void removeAddressToCustomer(@PathParam("addressid") Integer addressId, @PathParam("custid") Integer custId) {

        pb.removeAddressToCustomer(addressId, custId);
    }

    @PermitAll
    @GET
    @Path("getaddByCust/{custid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Address> getAddressesOfCustomer(@PathParam("custid") Integer custId) {

        return pb.getAddressesOfCustomer(custId);
    }

    @RolesAllowed("Admin")
    @POST
    @Path("createsub")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createSubscription(Subscription sub) {
        pb.createSubscription(sub);
    }

    @PermitAll
    @GET
    @Path("getallsubs")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Subscription> getAllSubscriptions() {
        return pb.getAllSubscriptions();
    }

    @RolesAllowed("Admin")
    @PUT
    @Path("addsubstocust/{custid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addSubscriptionsToCustomer(@PathParam("custid") Integer custId, Collection<Integer> subIds) {
        pb.addSubscriptionsToCustomer(custId, subIds);
    }

    @RolesAllowed("Admin")
    @PUT
    @Path("removesubstocust/{custid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void removeSubscriptionsToCustomer(@PathParam("custid") int custId, Collection<Integer> subIds) {

        pb.removeSubscriptionsToCustomer(custId, subIds);
    }

    @PermitAll
    @GET
    @Path("getsubbycust/{custid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Subscription> getSubscriptionsOfCustomer(@PathParam("custid") Integer custId) {

        return pb.getSubscriptionsOfCustomer(custId);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
