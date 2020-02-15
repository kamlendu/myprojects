/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.xml.ws.Service;

/**
 *
 * @author root
 */
 //@DeclareRoles({"Admin", "Supervisor"})
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

//    public ApplicationConfig() {
//        packages("rest");
//        // packages("servlets");
//        register(RolesAllowedDynamicFeature.class);
//        register(RestFilter.class);
//    }
    public ApplicationConfig() {
    }
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
   }
        

    

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.PublishRest.class);
    }
   

}
