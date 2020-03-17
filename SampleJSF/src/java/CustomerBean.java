/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author root
 */
@Named(value = "customerBean")
@RequestScoped
public class CustomerBean {

    private String customerUserName;
    private String customerPassword;
    private String customerName = "::sadasdsadas::";
    private int customerAge;
    private Date    regDate = new Date();
    private String customerEmail;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String showPrompt()
    {
        message = "hello I am here";
        return message;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

   
    
    public void validateLength(FacesContext ctx, UIComponent ui, Object obj)
    {
        String str = (String) obj;
    
    if(str.length()<=3)
    {
        ((UIInput)ui).setValid(false);
        
        FacesMessage message = new FacesMessage("Bean : The length sould not be less than 3 characters");
       // message.setSeverity(FacesMessage.SEVERITY_ERROR);
       ctx.addMessage(ui.getClientId(ctx), message);
    }
    }
    
    
    
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
  

    
    /**
     * Creates a new instance of CustomerBean
     */
    public CustomerBean() {
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }
    
    public String showData()
    {
       // message="Form done";
        return "ShowDataPage";
    }
}
