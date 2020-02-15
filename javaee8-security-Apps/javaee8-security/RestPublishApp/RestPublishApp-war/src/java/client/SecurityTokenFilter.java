package client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//import misc.TempIO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
public class SecurityTokenFilter implements Filter {

    private static final boolean debug = true;
    String authtoken;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    @Inject
    SecurityContext securityContext;

    public SecurityTokenFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        System.out.println("In SecurityTokenFilter");
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            HttpServletRequest objHttpServletRequest = (HttpServletRequest) request;

            HttpServletResponse objResponse = (HttpServletResponse) response;

            /*== Code To handle data from form using CustomFormBasedAuthenticationDefinition
    The Credentials and Authorization are taken from DatabaseIdentityStore
    See ProjectConfig class to see the annotations used for Authentication and Authorization
 ==*/
            Credential credential = new UsernamePasswordCredential(username, new Password(password));

            AuthenticationStatus status = securityContext.authenticate(
                    objHttpServletRequest,
                    objResponse,
                    withParams().credential(credential));
            System.out.println(status);
            authtoken = objResponse.getHeader("Authorization");

            // Testing the result of authentication
            if (status.equals(SUCCESS)) {
                HttpSession session = objHttpServletRequest.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                
            } else if (status.equals(SEND_FAILURE)) {

                objResponse.sendRedirect("loginError.jsp");
            }

        } catch (Exception e) {
            System.err.println(e);

        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        System.out.println("After Filter");
        HttpServletRequest objHttpServletRequest = (HttpServletRequest) request;
        HttpServletResponse objHttpServletResponse = (HttpServletResponse) response;
Cookie cookies[] = objHttpServletRequest.getCookies();
for(Cookie cookie: cookies)
    System.out.println("cookie name="+cookie.getName()+"  "+ cookie.getValue()+"   "+ cookie.getPath()+"  "+ cookie.getDomain());
       
if (securityContext.isCallerInRole("Admin")) {
            objHttpServletResponse.sendRedirect("/RestPublishApp-war/admin.jsp");

        } else if (securityContext.isCallerInRole("Supervisor")) {
            objHttpServletResponse.sendRedirect("/RestPublishApp-war/users.jsp");
        } else {
            objHttpServletResponse.sendRedirect("loginError.jsp");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        doBeforeProcessing(request, response);

        chain.doFilter(request, response);

        doAfterProcessing(request, response);

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {

        }
    }

}
