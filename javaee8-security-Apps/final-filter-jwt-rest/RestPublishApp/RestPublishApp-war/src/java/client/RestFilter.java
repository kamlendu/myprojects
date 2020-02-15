package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author root
 */
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;


/**
 * This RequestFilter performs a form based authentication. The filter can be
 * used with a javax.ws.rs.client.Client.
 * 
 * Author : Kamlendu Pandey
 */
//@Secured

//@WebFilter(filterName = "AuthenticationFilter", urlPatterns = { "/webresources/*" })
@Provider
@PreMatching
public class RestFilter implements ClientRequestFilter {
    public static String mytoken;
    
    public RestFilter(String token) {      
        mytoken = token;
     }
 
    @Override
     public void filter(ClientRequestContext requestContext) throws IOException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
             System.out.println(" In form Auth Client Filter "+ mytoken);
      
         //requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer "+mytoken);   
           //System.out.println(" After auth header Auth Client Filter "+ mytoken);
      
         Cookie c = new Cookie("JREMEMBERMEID", mytoken);
   
         requestContext.getHeaders().add(HttpHeaders.COOKIE,c);
         
      System.out.println(" After cookie header Auth Client Filter "+ mytoken);
   
    }

    
}