/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */

@RequestScoped
@Named
public class LoginBean {
   
    @Inject SecurityContext securityContext;
   
    AuthenticationStatus status;
    private String username;
    private String password;
    private String message="";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
  public  String login()
    {
        try{
            message="";
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        
         Credential credential = new UsernamePasswordCredential(username, new Password(password));
            
            status = securityContext.authenticate(
                    request,
                    response,
                    withParams().credential(credential));
           System.out.println(securityContext.isCallerInRole("Admin"));
            // Testing the result of authentication
            if (status.equals(SUCCESS)) {
                 if (securityContext.isCallerInRole("Admin"))
                    return "/admins/AdminHome.xhtml";
                 else if (securityContext.isCallerInRole("Supervisor"))
                    return "/users/UserHome.xhtml";
     
            } else if (status.equals(SEND_FAILURE)) {

                message= "Either Login or Password is wrong";
               return "/index.xhtml";
            }


        }
        catch(Exception e)
        {
            message= "Either Login or Password is wrong";
        }
            
        
        return null;
    }
  public String Logout() throws IOException,ServletException{
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      request.logout();
      
      
//      HttpSession objHttpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        objHttpSession.invalidate();
//        FacesContext.getCurrentInstance().getExternalContext().redirect("/SecureJSFApp/faces/index.xhtml");
   return "/index.xhtml";  
  }
}
