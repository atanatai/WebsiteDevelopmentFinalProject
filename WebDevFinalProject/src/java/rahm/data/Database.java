/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rahm.data;

//import com.mysql.jdbc.Connection;
import java.sql.Connection;

import com.mysql.jdbc.Statement;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;

/**
 *
 * @author Atan
 */
public class Database implements Serializable{
    Statement stmt;
    Connection connection = null;
    ResultSet results;
    PreparedStatement ps;
    String driver = "com.mysql.jdbc.Driver";
    
    
    private List<FormData> customers = new ArrayList<FormData>();
    private List<Item> allCartItems = new ArrayList<Item>();
    
    
    public Database(){
        try{
            try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
            String dbURL = "jdbc:mysql://localhost:3306/rahmTest";
            String username = "root";
            String password = "password";
            connection = DriverManager.getConnection(dbURL, username, password);
            stmt = (Statement) connection.createStatement();
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        
    }
    
    public List<FormData> getAllUsers(){
        customers.clear();
        try {
            results = stmt.executeQuery(
                    "SELECT * FROM Users"
            );
            while(results.next()){
                //FormData( String fName, String lName, String email, String username, String password )
                customers.add(new FormData(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5)));
            }
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        return customers;
    }
    
    //FormData(fName, lName, email, username, password )
    public List<FormData> addUser(FormData newUser){
        String fname = newUser.getfName();
        String lname = newUser.getlName();
        String email = newUser.getEmail();
        String username = newUser.getUsername();
        String password = newUser.getPassword();
             
        try{
            String insertQuery = "INSERT INTO Users(FName, LName, Email, UName, PWord) "
                     + "VALUES ('"+fname+"','"+lname+"','"+email+"','"+username+"','"+password+"')";
            stmt.executeUpdate(insertQuery);
             
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        return getAllUsers();
     }
    
    public List<Item> getAllItems(){
        allCartItems.clear();
        try {
            results = stmt.executeQuery(
                    "SELECT * FROM Items ORDER BY IName ASC"
            );
            while(results.next()){ //note the qty returned here is the quantity available, not selected
                //Item(String itemName, int itemNumber, float itemPrice, int quantity, String itemDescription)
                allCartItems.add(new Item(results.getString(2), results.getInt(1), results.getFloat(3), results.getInt(4), results.getString(5), results.getString(6)));
            }
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        return allCartItems;
    }
    
    public List<Item> getUserCart(String username){
        List<Item> userCartItems = new ArrayList<Item>();
        try {
            results = stmt.executeQuery(
                    "SELECT UName, IName, ItemNumber, IPrice, Qty, IDescription, ItemLink "
                            + "FROM Cart, Users, Items "
                            + "WHERE Users.UName=Cart.Username AND Items.INumber=Cart.ItemNumber AND UName='"+username+"'"
            );
            while(results.next()){ //note the qty returned here is the quantity available, not selected
                //Item(String itemName, int itemNumber, float itemPrice, int quantity, String itemDescription)
                userCartItems.add(new Item(results.getString(2),results.getInt(3),results.getFloat(4),results.getInt(5),results.getString(6), results.getString(7)));
                //cartItems.add(new Item(results.getString(2), results.getInt(1), results.getFloat(3), results.getInt(4), results.getString(5)));
                //userCart.add([results.getString(1), results.getString(2), String.valueOf(results.getInt(3)), String.valueOf(results.getInt(4).toString())]);
            }
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        return userCartItems;
    }
    
     public List<Item> addItem(String username, Item item){
        try{
             String insertQuery = "INSERT INTO Cart(Username, ItemNumber, Qty) "
                     + "VALUES ('"+username+"', "+item.getItemNumber()+", "+item.getQuantity()+")";
             stmt.executeUpdate(insertQuery);
             
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        return getUserCart(username);
     }
     
     public List<Item> updateItem(String username, int itemID, int qty){
        try{
            /**
             results = stmt.executeQuery(
                     "SELECT UID FROM Users"
                             + "WHERE UName='"+username+"'"
             );
             * **/
             String updateQuery = "UPDATE Cart "
                     + "SET Qty="+qty
                     + " WHERE Username='"+username+"' AND ItemNumber="+itemID;
             stmt.executeUpdate(updateQuery);
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        return getUserCart(username);
     }
     
     public List<Item> removeItem(String username, int itemID){
         try{
             /**
             results = stmt.executeQuery(
                     "SELECT UID FROM Users"
                             + "WHERE UName='"+username+"'"
             );
             * **/
             String deleteItemQuery = "DELETE FROM Cart "
                     + "WHERE Username='"+username+"' AND ItemNumber="+itemID;
             stmt.executeUpdate(deleteItemQuery);
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
         
        return getUserCart(username);
        
     }
     
     public void removeAllCartItems(String username){
         try{
             /**
             results = stmt.executeQuery(
                     "SELECT UID FROM Users"
                             + "WHERE UName='"+username+"'"
             );
             * **/
             String deleteItemQuery = "DELETE FROM Cart "
                     + "WHERE Username='"+username+"'";
             stmt.executeUpdate(deleteItemQuery);
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        
     }
     
     public List<Order> getAllOrders(){
        List<Order> allOrders = new ArrayList<Order>();
        try {
            results = stmt.executeQuery(
                    "SELECT * FROM Orders"
            );
            while(results.next()){ //note the qty returned here is the quantity available, not selected
                //Order(String ODate, String OUName, int OItemNumber, int Qty, float UnitPrice, String OComplete)
                allOrders.add(new Order(results.getString(1), results.getString(2), results.getInt(3), results.getInt(4), results.getFloat(5), results.getString(6), results.getString(7), results.getDouble(8), results.getInt(9), results.getString(10), results.getString(11)));
            }
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        return allOrders;
     }
     
     public List<Order> getUserOrders(String username){
         List<Order> userOrders = new ArrayList<Order>();
        try {
            results = stmt.executeQuery(
                    "SELECT UName, ODate, OUName, OItemNumber, Qty, UnitPrice, OComplete, CardName, CardNumer, CSV, ExpDate, Orders.Email "
                            + "FROM Orders, Users, Items "
                            + "WHERE Users.UName=Orders.OUName AND Items.INumber=Orders.OItemNumber AND UName='"+username+"'"
            );
            while(results.next()){ //note the qty returned here is the quantity available, not selected
                //Order(int ONumber, String ODate, String OUName, int OItemNumber, int Qty, float UnitPrice, String OComplete)
                userOrders.add(new Order(results.getString(2), results.getString(3), results.getInt(4), results.getInt(5), results.getFloat(6), results.getString(7), results.getString(8), results.getDouble(9), results.getInt(10), results.getString(11), results.getString(12)));
            }
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        return userOrders;
     }
     
     public List<Order> addOrder(String username, int itemNumber, int qty, float unitPrice, String cardName, double cardnumber, int csv, String expdate, String email){
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
        String dateString = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(date);
        
         String insertQuery = "INSERT INTO Orders(ODate, OUName, OItemNumber, Qty, UnitPrice, OComplete, CardName, CardNumer, CSV, ExpDate, Email) "
                     + "VALUES ('"+dateString+"', '"+username+"', "+itemNumber+", "+qty+", "+unitPrice+", 'no','"+cardName+"', "+cardnumber+", "+csv+", '"+expdate+"', '"+email+"')";
        try {
            stmt.executeUpdate(insertQuery);
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        return getUserOrders(username);
     }
    
}
