/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:PublishRest [publish]<br>
 * USAGE:
 * <pre>
 *        PubRestClient client = new PubRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author root
 */
public class PubRestClient {
//@Context HttpServletRequest request;

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8181/RestPublishApp-war/webresources";

    public PubRestClient(String username, String password) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
    
        webTarget = client.target(BASE_URI).path("publish")
                .queryParam("username", username)
                .queryParam("password", password);


 
    }

    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {

            public boolean verify(String hostname,
                    javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
            }
        });
    }

    public void addAddressToCustomer_XML(Object requestEntity, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addaddresstocust/{0}", new Object[]{custid})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void addAddressToCustomer_JSON(Object requestEntity, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addaddresstocust/{0}", new Object[]{custid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void createCustomer_XML(Object requestEntity) throws ClientErrorException {
        webTarget.path("createcust").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void createCustomer_JSON(Object requestEntity) throws ClientErrorException {
        webTarget.path("createcust").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeAddressToCustomer_XML(Object requestEntity, String addressid, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addaddresstocust/{0}/{1}", new Object[]{addressid, custid})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void removeAddressToCustomer_JSON(Object requestEntity, String addressid, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addaddresstocust/{0}/{1}", new Object[]{addressid, custid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void createSubscription_XML(Object requestEntity) throws ClientErrorException {
        webTarget.path("createsub").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void createSubscription_JSON(Object requestEntity) throws ClientErrorException {
        webTarget.path("createsub").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void addSubscriptionsToCustomer_XML(Object requestEntity, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addsubstocust/{0}", new Object[]{custid})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void addSubscriptionsToCustomer_JSON(Object requestEntity, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addsubstocust/{0}", new Object[]{custid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllCustomers_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllCustomers_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAddressesOfCustomer_XML(Class<T> responseType, String custid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getaddByCust/{0}", new Object[]{custid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAddressesOfCustomer_JSON(Class<T> responseType, String custid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getaddByCust/{0}", new Object[]{custid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeSubscriptionsToCustomer_XML(Object requestEntity, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addsubstocust/{0}", new Object[]{custid})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void removeSubscriptionsToCustomer_JSON(Object requestEntity, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removesubstocust/{0}", new Object[]{custid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void updateCustomer_XML(Object requestEntity, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateCust/{0}", new Object[]{custid})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void updateCustomer_JSON(Object requestEntity, String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateCust/{0}", new Object[]{custid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllCustomersByfirstName_XML(Class<T> responseType, String firstname) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getbyfirst/{0}", new Object[]{firstname}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllCustomersByfirstName_JSON(Class<T> responseType, String firstname) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getbyfirst/{0}", new Object[]{firstname}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllSubscriptions_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallsubs");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllSubscriptions_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallsubs");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeCustomer(String custid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteCust/{0}", new Object[]{custid})).request().delete();
    }

    public <T> T getSubscriptionsOfCustomer_XML(Class<T> responseType, String custid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getsubbycust/{0}", new Object[]{custid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getSubscriptionsOfCustomer_JSON(Class<T> responseType, String custid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getsubbycust/{0}", new Object[]{custid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }

}
