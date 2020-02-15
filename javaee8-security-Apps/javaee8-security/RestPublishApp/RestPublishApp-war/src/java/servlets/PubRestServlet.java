/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import client.PubRestClient;
import entity.Address;
import entity.Customer;
import entity.Subscription;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
//import org.glassfish.jersey.client.ClientRequest;

/**
 *
 * @author root
 */
@DeclareRoles({"Admin", "Supervisor"})
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"Admin", "Supervisor"}))
public class PubRestServlet extends HttpServlet {

    PubRestClient rs;
    Response res;
    Customer customer;
    Collection<Customer> customers;
    GenericType<Collection<Customer>> custgeneric;
    Address address;
    Collection<Address> addresses;
    GenericType<Collection<Address>> addressgeneric;
    Subscription subscription;
    Collection<Subscription> subscriptions;
    GenericType<Collection<Subscription>> subsgeneric;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      //  ClientRequestContext ctx;
        //ClientRequest cr = new ClientRequest();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PubRestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<hr/><p align='right'><a href='logout.jsp'>Log Out </a></p><hr/>");
         
            HttpSession session = request.getSession();

            // Client client = PubRestClient.
            //rs = PubRestClient;
            //  Cookie cookies[]= request.getCookies();
//            rs = new PubRestClient(session.getAttribute("username").toString(),session.getAttribute("password").toString());
            session = request.getSession();
            rs = new PubRestClient(session.getAttribute("username").toString(), session.getAttribute("password").toString());
            // rs = new PubRestClient();

// javax.ws.rs.client.ClientRequestContext  requestContext.getHeaders().put("Cookie", cookies);
            customers = new ArrayList<Customer>();
            custgeneric = new GenericType<Collection<Customer>>() {
            };
            addresses = new ArrayList<>();
            addressgeneric = new GenericType<Collection<Address>>() {
            };
            subscriptions = new ArrayList<>();
            subsgeneric = new GenericType<Collection<Subscription>>() {
            };

            //Customer cust = new Customer(); cust.setFirstName("Shweta"); cust.setLastName("Shah");
            //rs.createCustomer_JSON(cust);
//           Address address = new Address();
//           address.setStreet("Agam Complex"); address.setCity("Aurangabad"); address.setState("Mahashtra");
//           address.setZip("54535");
//           rs.addAddressToCustomer_JSON(address, new Integer(65).toString());
//         ArrayList<Integer> id1 = new ArrayList<Integer>();
//          id1.add(7);
//           id1.add(10);
//           
//         rs.addSubscriptionsToCustomer_JSON(id1, new Integer(44).toString());
            // rs.removeSubscriptionsToCustomer_JSON(id1, "43");
// Retrieve All data 
            res = rs.getAllCustomers_XML(Response.class);
 if(!res.getStatusInfo().getReasonPhrase().equals("Forbidden"))
      {
            customers = res.readEntity(custgeneric);

            for (Customer c : customers) {
                out.println("<h1> Id =" + c.getCustomerID() + " Name = " + c.getFirstName() + " " + c.getLastName() + "</h1>");

                res = rs.getAddressesOfCustomer_XML(Response.class, c.getCustomerID().toString());
                addresses = (Collection<Address>) res.readEntity(addressgeneric);

                for (Address addres : addresses) {
                    out.println("<h2>Id =" + addres.getAddressId() + " Name = " + addres.getCity() + " " + addres.getState() + "</h2>");

                }

                res = rs.getSubscriptionsOfCustomer_XML(Response.class, new Integer(c.getCustomerID()).toString());
                subscriptions = (Collection<Subscription>) res.readEntity(subsgeneric);

                for (Subscription s : subscriptions) {
                    out.println("<h2>Name = " + s.getTitle() + " " + s.getType() + "</h2>");

                }
                out.println("<hr>");
            }
            }
        
        else{
                    out.println("<h3><i>Sorry you are not authorized to view this result</i></h3>");
                }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
