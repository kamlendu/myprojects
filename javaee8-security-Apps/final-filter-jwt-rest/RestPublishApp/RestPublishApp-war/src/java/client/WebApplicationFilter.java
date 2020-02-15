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
public class WebApplicationFilter implements Filter {

    private static final boolean debug = true;
    String authtoken;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
@Inject SecurityContext securityContext;
    public WebApplicationFilter() {
    } 

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	System.out.println("In SecurityTokenFilter");
   try {
      String username=  request.getParameter("username");
      String password = request.getParameter("password");
        HttpServletRequest objHttpServletRequest = (HttpServletRequest) request;
//username="admin";
//password="admin";
//HttpSession session = objHttpServletRequest.getSession();
//String authtoken;
 HttpServletResponse objResponse= (HttpServletResponse)response;
        
Credential credential = new UsernamePasswordCredential(username, new Password(password));
        
        AuthenticationStatus status = securityContext.authenticate(
            objHttpServletRequest,
            objResponse,
            withParams().credential(credential));
        System.out.println(status);
        authtoken=    objResponse.getHeader("Authorization");
        System.out.println("Authorize ="+authtoken);
        //        CookieManager manager = new CookieManager();
//        CookieStore cookieJar = manager.getCookieStore();
//        List<HttpCookie> cookiesListe = cookieJar.getCookies();
      Cookie cookies[]=  objHttpServletRequest.getCookies();
      for (Cookie c: cookies)
      System.out.println("Cookie :"+c.getName()+ ": "+c.getValue());
      
        if (status.equals(SUCCESS)) {
            HttpSession session = objHttpServletRequest.getSession();
        //String authtoken=    objHttpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
      //  System.out.println("Authorize ="+authtoken);
              session.setAttribute("username", username);
            session.setAttribute("password", password);
            
    }
        
          //  request.getServletContext().responseComplete();
      else if (status.equals(SEND_FAILURE)) {
        
            objResponse.sendRedirect("loginError.jsp");
        }
        

//objHttpServletRequest.login(username, password);

//HttpSession session = objHttpServletRequest.getSession();

        
        
    } catch (Exception e) {
      System.err.println(e);

  }
	
    } 

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
//	if (debug) log("WebApplicationFilter:DoAfterProcessing");
System.out.println("After Filter");
HttpServletRequest objHttpServletRequest = (HttpServletRequest) request;
 HttpServletResponse objHttpServletResponse = (HttpServletResponse) response;
//// objHttpServletRequest.login(request.getParameter("username"), request.getParameter("password"));
//System.out.println(objHttpServletRequest.isUserInRole("AdminRole"));

 if(securityContext.isCallerInRole("Admin"))
 {
   //  objHttpServletResponse.setHeader("Authorization", authtoken);
   System.out.println("Authorizations :"+objHttpServletRequest.getHeader("Authorization"));
     System.out.println("Principal = "+ securityContext.getCallerPrincipal().getName());
    objHttpServletResponse.sendRedirect("/RestPublishApp-war/admin.jsp");
 
 }
    else if(securityContext.isCallerInRole("Supervisor"))
    {
    objHttpServletResponse.sendRedirect("/RestPublishApp-war/users.jsp");
    }
    else
      objHttpServletResponse.sendRedirect("loginError.jsp");
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
	
	Throwable problem = null;
	try {
	    chain.doFilter(request, response);
	}
	catch(Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
	    // we still want to execute our after processing, and then
	    // rethrow the problem after that.
	    problem = t;
	    t.printStackTrace();
	}

	doAfterProcessing(request, response);

	// If there was a problem, we want to rethrow it if it is
	// a known type, otherwise log it.
	if (problem != null) {
	    if (problem instanceof ServletException) throw (ServletException)problem;
	    if (problem instanceof IOException) throw (IOException)problem;
	    sendProcessingError(problem, response);
	}
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
	    if (debug) { 
		log("SecurityTokenFilter:Initializing filter");
	    }
	}
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("SecurityTokenFilter()");
	StringBuffer sb = new StringBuffer("SecurityTokenFilter(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {
	    try {
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
    }

    public static String getStackTrace(Throwable t) {
	String stackTrace = null;
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
