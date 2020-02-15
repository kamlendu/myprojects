/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Set;
import javax.annotation.security.DeclareRoles;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 *
 * @author root
 */
//@ApplicationScoped
//@DeclareRoles({"Admin","Supervisor"})
//@FormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/index.jsp",
//                errorPage = "/loginError.jsp",
//                useForwardToLogin = false
//                
//        )
//)
//@DatabaseIdentityStoreDefinition(
//  dataSourceLookup = "jdbc/mysql",
//  callerQuery = "select password from users where username = ?",
//  groupsQuery = "select GROUPNAME from groups where username = ?",
//  hashAlgorithm = Pbkdf2PasswordHash.class,
//  priority=30)
@DeclareRoles({"Admin", "Supervisor"})
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("rest");
        // packages("servlets");
        register(RolesAllowedDynamicFeature.class);
       //  register(Authenticator.class);

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
