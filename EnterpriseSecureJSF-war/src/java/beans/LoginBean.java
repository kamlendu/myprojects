/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    
    @Inject SecurityContext securityContext;

    private String username;
    private String password;
    private String message;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

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
    
   public String login()
    {
        try{
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
       AuthenticationStatus status= securityContext.authenticate(request, response, 
                                    withParams().credential(credential));
      
       
       if(status.equals(SUCCESS))
       {
           HttpSession session = request.getSession(true);
           session.setAttribute("username", username);
           session.setAttribute("password", password);
           
           System.out.println("Login Success!");
        //    System.out.println(securityContext.isCallerInRole("Admin"));
           if(securityContext.isCallerInRole("Admin"))
           {
              return "/admins/AdminPage.jsf";
           }
           else if(securityContext.isCallerInRole("Supervisor"))
           {
               return "/users/UserPage.jsf";
           }
        }
       else if(status.equals(SEND_FAILURE))
           {
               message = "Either user or password is wrong !!!";
               return "/Login.jsf";
           }
       
        }
        catch (Exception e)
        {
             message = "Out- Either user or login is wrong !!!";
              e.printStackTrace();
        }
        
        return null;
    }
   
   public String logout() throws ServletException
   {
       System.out.println("In Log out");
          HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
          request.logout();
          
          return "/Login.jsf";
             
             }
    
}
