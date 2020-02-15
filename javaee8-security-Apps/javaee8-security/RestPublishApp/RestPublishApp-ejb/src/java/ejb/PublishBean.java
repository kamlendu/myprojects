/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Address;
import entity.Customer;
import entity.Subscription;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
//@WebService
@Stateless
public class PublishBean implements PublishBeanLocal {
@PersistenceContext(unitName = "RestPublishApp-ejbPU")
EntityManager em;
    
    
    @Override
    public void createCustomer(Customer c) {
 
       
        
        em.persist(c);
    }

     @Override
    public void updateCustomer(Customer c, Integer custId) {
   
       
        em.merge(c);
    }

     @Override
    public void removeCustomer(Integer custId) {
       Customer c = em.find(Customer.class, custId);
       
       em.remove(c);
    }

     @Override
    public Collection<Customer> getAllCustomers() {
   Collection<Customer> customers = em.createNamedQuery("Customer.findAll")
            .getResultList();
    return customers;
    }

     @Override
    public Collection<Customer> getAllCustomersByfirstName(String firstName) {
  
   Collection<Customer> customers = em.createNamedQuery("Customer.findByFirstName")
                                       .setParameter("firstName", firstName)
                                       .getResultList();
    return customers;
    }

     @Override
    public Collection<Customer> getAllCustomersBylastName(String lastName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void addAddressToCustomer(Address address, Integer custId) 
    {
        
          Customer c = em.find(Customer.class, custId);
          Collection<Address> addresses = c.getAddressCollection();
     
          
          address.setCustomerId(c);
          addresses.add(address);
          c.setAddressCollection(addresses);
          
          em.persist(address);
          em.merge(c);
     
    }

     @Override
    public void removeAddressToCustomer(Integer addressId, Integer custId) {
  
      Customer c = em.find(Customer.class, custId);
    Address address = em.find(Address.class, addressId);
    
    Collection<Address> addresses = c.getAddressCollection();
    
    if(addresses.contains(address))
    {
        addresses.remove(address);
        em.remove(address);
        c.setAddressCollection(addresses);
        em.merge(c);
    }
    
    }

     @Override
    public void updateAddressToCustomer(Integer addressId, String street, String city, String state, String zip, Integer custId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public Collection<Address> getAddressesOfCustomer(Integer custId) {
       Customer c = em.find(Customer.class, custId);
     
       return c.getAddressCollection();
    
    }

     @Override
    public Collection<Address> getAddresseByCity(String city) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public Collection<Address> getAddresseByState(String state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public Collection<Address> getAddresseByZip(String zip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void createSubscription(Subscription sub) {
 
    
    em.persist(sub);
    }

    

     @Override
    public void removeSubscription(Integer subId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public Collection<Subscription> getAllSubscriptions() {
    
    return em.createNamedQuery("Subscription.findAll").getResultList();
    }

     @Override
    public Collection<Subscription> getSubscriptionsByType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void addSubscriptionsToCustomer(Integer custId, Collection<Integer> subIds) {

          Customer c = em.find(Customer.class, custId);
          Collection<Subscription> subscriptions = c.getSubscriptionCollection();
     
          for(Integer sid : subIds)
          {
              Subscription sub = em.find(Subscription.class, sid);
              
              if(!subscriptions.contains(sub))
              {
                  Collection<Customer> customers = sub.getCustomerCollection();
                  subscriptions.add(sub);
                  customers.add(c);
                  sub.setCustomerCollection(customers);
                  c.setSubscriptionCollection(subscriptions);
                  
                  em.merge(c);
                  
              }
              
              
              
              
          }
        
        


    }

     @Override
    public void removeSubscriptionsToCustomer(Integer custId, Collection<Integer> subIds) {
            Customer c = em.find(Customer.class, custId);
            Collection<Subscription> subscriptions = c.getSubscriptionCollection();
     
          for(Integer sid : subIds)
          {
              Subscription sub = em.find(Subscription.class, sid);
              
              if(subscriptions.contains(sub))
              {
                  Collection<Customer> customers = sub.getCustomerCollection();
                  subscriptions.remove(sub);
                  customers.remove(c);
                  sub.setCustomerCollection(customers);
                  c.setSubscriptionCollection(subscriptions);
                  
                  em.merge(c);
                  
              }
              
              
              
              
          }
        
        
 }

    @Override
    public void updateSubscription(Integer SubId, Subscription s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public Collection<Subscription> getSubscriptionsOfCustomer(Integer custId) {
  
              Customer c = em.find(Customer.class, custId);
    
      return c.getSubscriptionCollection();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
