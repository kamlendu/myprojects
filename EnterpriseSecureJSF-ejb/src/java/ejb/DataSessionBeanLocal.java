/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.BookMaster;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DataSessionBeanLocal {
    
    Collection<BookMaster> getAllBooks();
    
}
