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
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface PublishBeanLocal {
    
    void createCustomer(Customer c);
    void updateCustomer(Customer c,Integer custId);
    void removeCustomer(Integer custId);
    Collection<Customer> getAllCustomers();
    Collection<Customer> getAllCustomersByfirstName(String firstName);
    Collection<Customer> getAllCustomersBylastName(String lastName);
    
    void addAddressToCustomer(Address a, Integer custId);
    void removeAddressToCustomer(Integer addressId, Integer custId);
   void updateAddressToCustomer(Integer addressId, String street, String city, String state, String zip, Integer custId);
    Collection<Address> getAddressesOfCustomer(Integer  custId); 
    Collection<Address> getAddresseByCity(String city);
    Collection<Address> getAddresseByState(String state);
    Collection<Address> getAddresseByZip(String zip);
    
    void createSubscription(Subscription s);
    void updateSubscription(Integer SubId, Subscription s);
    void removeSubscription(Integer subId);
    Collection<Subscription> getAllSubscriptions();
    Collection<Subscription> getSubscriptionsByType(String type);
    
    
    void addSubscriptionsToCustomer(Integer custId, Collection<Integer> subIds);
    void removeSubscriptionsToCustomer(Integer custId, Collection<Integer> subIds);
    Collection<Subscription> getSubscriptionsOfCustomer(Integer custId);
    
    
    
    
    
}
