/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rahm.data;

import java.io.Serializable;

/**
 * Class to hold the data fields of
 * @author Kai Rahm
 */
public class FormData implements Serializable{
    private String username;
    private String password;
    private String fName;
    private String lName;
    private String email;

    public FormData() {
        username = "";
        password = "";
        fName = "";
        lName = "";
        email = "";
    }

    public FormData( String fName, String lName, String email, String username, String password ) {
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }
    
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }
        
    
}
