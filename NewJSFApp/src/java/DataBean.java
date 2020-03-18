/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author root
 */
@Named(value = "dataBean")
@RequestScoped
public class DataBean {
    
    private String username;
    private String password;

    /**
     * Creates a new instance of DataBean
     */
    public DataBean() {
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
    
    public String login()
    {
        return "faces/StylePage.xhtml";
    }
    
}
