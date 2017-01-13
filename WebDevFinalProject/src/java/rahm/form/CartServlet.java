/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rahm.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rahm.data.*;

/**
 *
 * @author Atan
 */
public class CartServlet extends HttpServlet {

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/page2.jsp";
        String message = "";
        
        
        //gets the customers current cart
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Orders orders = (Orders)request.getSession().getAttribute("orders");
        
        //get current action
        String action = request.getParameter("action");
        
        //gets the current customers data and keeps it in session
        FormData data = (FormData) request.getSession().getAttribute("data");
        request.setAttribute("data", data);
        String userMessage="Current user: "+data.getUsername();
        request.setAttribute("userMessage", userMessage);
        
        if(action == null){
            action = "Add"; //default form action
        }
        
        if(action.equals("Add")){
            url="/page2.jsp";
            String itemName = request.getParameter("itemName");
            int itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
            float itemPrice = Float.parseFloat(request.getParameter("itemPrice").replaceAll("[^\\d.]+", ""));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String itemDescription = request.getParameter("itemDescription");
            String itemFileLink = request.getParameter("itemLink");
            int qtyAvailable = Integer.parseInt(request.getParameter("qtyAvailable"));
            
            Item itemToAdd = new Item(itemName, itemNumber, itemPrice, quantity, itemDescription, itemFileLink);
            
            String itemLink = "blank.jsp";
            request.getSession().setAttribute("itemLink", itemLink);
            
            if((qtyAvailable-itemToAdd.getQuantity()) < 0){
                message = "There aren't enough items in stock to add. "+itemName+" : "+qtyAvailable;
                request.setAttribute("message", message);
            }
            else{
                cart.addItem(data.getUsername(), itemToAdd);
                qtyAvailable = qtyAvailable-itemToAdd.getQuantity();
                request.setAttribute("qtyAvailable", qtyAvailable);
                //message = "There are "+cart.getCartSize(data.getUsername())+" items in your cart.";
                //request.setAttribute("message", message);
                request.getSession().setAttribute("cart", cart);
            }
            
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
        if(action.equals("View")){
            url="/page2.jsp";
            String itemName = request.getParameter("itemName");
            int itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
            float itemPrice = Float.parseFloat(request.getParameter("itemPrice").replaceAll("[^\\d.]+", ""));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String itemDescription = request.getParameter("itemDescription");
            
            String itemFileLink = request.getParameter("itemLink");
            String itemLink = "item.jsp";
            request.getSession().setAttribute("itemLink", itemLink);
            
            
            
            Item itemSelected = new Item(itemName, itemNumber, itemPrice, quantity, itemDescription, itemFileLink);
            request.getSession().setAttribute("itemSelected", itemSelected);
            
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
         if(action.equals("View Cart")){
             url="/cart.jsp";
             request.getSession().setAttribute("cart", cart);
             String itemLink = "blank.jsp";
             
            request.getSession().setAttribute("itemLink", itemLink);
             //message="Hello "+data.getUsername();
             //request.setAttribute("message", message);
             getServletContext().getRequestDispatcher(url).forward(request, response);
         }
         
         if(action.equals("Update")){
             url="/cart.jsp";
             int itemId = Integer.parseInt(request.getParameter("itemId"));
             int quantity = Integer.parseInt(request.getParameter("quantity"));
             cart.updateCartItem(data.getUsername(), itemId, quantity);
             //message="Hello "+data.getfName()+". Items in your cart:";
             //request.setAttribute("message", message);
             getServletContext().getRequestDispatcher(url).forward(request, response);
         }
         
         if(action.equals("Back to Shop")){
            url="/page2.jsp";
            String itemLink = "blank.jsp";
            request.getSession().setAttribute("itemLink", itemLink);
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
         
         if(action.equals("Checkout")){
            if(cart.getCartSize(data.getUsername()) <=0){
                url = "/cart.jsp";
                message="There are no items in your cart";
                request.setAttribute("message", message);
            }else{
                url="/checkout.jsp";
            }
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        if(action.equals("Delete")){
            url="/checkout.jsp";
             int itemId = Integer.parseInt(request.getParameter("itemId"));
             cart.removeCartItem(data.getUsername(), itemId);
             //message="Hello "+data.getfName()+". Items in your cart:";
             //request.setAttribute("message", message);
             getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
        if(action.equals("Purchase")){
            url="/confirmation.jsp";
            String cardName = (request.getParameter("cardName"));
            double cardNumber = Double.parseDouble(request.getParameter("cardNumber"));
            int csv = Integer.parseInt(request.getParameter("csv"));
            String date = request.getParameter("date");
            String email = request.getParameter("email");
            List<Order> allOrders = new ArrayList<Order>();
            for(Item item : cart.getCartItems(data.getUsername())){
                allOrders.add(new Order(date, data.getUsername(), item.getItemNumber(), item.getQuantity(), item.getItemPriceRaw(), "no", cardName, cardNumber, csv, date, email));
                
            }
            for(Order order : allOrders){
                orders.addOrder(data.getUsername(), order);
            }
            
            //MailConfirmation sendmail=new MailConfirmation();
            
             //message="Hello "+data.getfName()+". Items in your cart:";
             //request.setAttribute("message", message);
             getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
