/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.BookMaster;
import java.util.Collection;
import javax.ejb.Stateful;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
//@Stateful
@Named(value = "dataLogic")
@Dependent
public class DataLogic {
//@PersistenceContext(unitName = "jsfpu")
EntityManager em;
    
    /**
     * Creates a new instance of DataLogic
     */
    public DataLogic() {
        
        em = Persistence.createEntityManagerFactory("jsfpu").createEntityManager();
        
        
    }
    
    public Collection<BookMaster> getAllBooks()
    {
       return em.createNamedQuery("BookMaster.findAll").getResultList();
    }
}
