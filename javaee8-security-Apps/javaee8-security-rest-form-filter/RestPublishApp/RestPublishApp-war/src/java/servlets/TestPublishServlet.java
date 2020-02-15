/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb.PublishBeanLocal;
import entity.Address;
import entity.Customer;
import entity.Subscription;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "TestPublishServlet", urlPatterns = {"/TestPublishServlet"})
@DeclareRoles({"Admin", "Supervisor"})
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"Admin"}))
public class TestPublishServlet extends HttpServlet {

    @EJB
    PublishBeanLocal pb;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestPublishServlet</title>");
            out.println("</head>");
            out.println("<body>");

//            Customer nc = new Customer();
//            nc.setFirstName("Survir");
//            nc.setLastName("Singh");
//            
//            pb.createCustomer(nc);.
//            Address ad = new Address();
//            ad.setStreet("sadas");
//            ad.setCity("Mumbai");
//            ad.setState("maharashtra");
//            ad.setZip("21321");
////            
//            pb.addAddressToCustomer(ad, 41);
            //pb.removeCustomer(42);
            Collection ids = new ArrayList<Integer>();
            ids.add(7);
//            ids.add(6);
//            ids.add(8);
//            
//            pb.addSubscriptionsToCustomer(41, ids);

//pb.removeSubscriptionsToCustomer(41, ids);
            Collection<Customer> customers = pb.getAllCustomers();
            for (Customer c : customers) {
                out.println("<h2> id = " + c.getCustomerID() + " name = " + c.getFirstName() + "  " + c.getLastName() + "</h2>");
                Collection<Address> ads = pb.getAddressesOfCustomer(c.getCustomerID());
                for (Address a : ads) {
                    out.println("<h2> id = " + a.getAddressId() + " city = " + a.getCity() + " State = " + a.getState() + "</h2>");

                }
                Collection<Subscription> subs = pb.getSubscriptionsOfCustomer(c.getCustomerID());
                for (Subscription s : subs) {
                    out.println("<h2>  id = " + s.getSubscriptionId() + " title = " + s.getTitle() + " Type = " + s.getType() + "</h2>");

                }

                out.println("<hr>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
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
