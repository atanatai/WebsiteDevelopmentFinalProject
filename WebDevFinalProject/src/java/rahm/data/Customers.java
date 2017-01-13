/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rahm.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Atan
 */
public class Customers implements Serializable {
    
    private static List<FormData> customers = new ArrayList<FormData>();
    
    private static Database db = new Database();
    
            
    
    public static boolean createCustomer(FormData customer){
        if(customer.getUsername().trim().equals("") || customer.getPassword().trim().equals("")){
            return false; //returns false because there is no username or password given
        }
        if(customers.isEmpty()){
            //customers.add(customer);
            customers = db.addUser(customer);
            return true; //adds customer because there are no customers yet
        }
        for (FormData c : customers){
            if(c.getUsername().equals(customer.getUsername())  
                    || c.getEmail().equals(customer.getEmail()) ){
                return false; //returns false because customer exists, or login credentials are used
            }
        }
        //customers.add(customer);
        customers = db.addUser(customer);
        return true;
    }
    
    public static boolean loginCustomer(String userName, String password){
        customers = db.getAllUsers();
        for (FormData c : customers){
            if(c.getUsername().equals(userName) && c.getPassword().equals(password)){
                return true; //login credentials were found
            }
        }
        return false; //login credentials were not found
    }
    
    public static FormData getCustomerData(String userName, String password){
        customers = db.getAllUsers();
        for (FormData c : customers){
            if(c.getUsername().equals(userName) && c.getPassword().equals(password)){
                return c; //login credentials were found, data returned
            }
        }
        return null; //login credentials were not found, nothing returned
    }
}
