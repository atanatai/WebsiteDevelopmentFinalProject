/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rahm.form;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import rahm.data.Cart;
import rahm.data.Customers;
import rahm.data.FormData;
import rahm.data.Orders;

/**
 *
 * @author Atan
 */
//@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {


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
        //processRequest(request, response);
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
        String url = "/index.jsp";
        String message = "";
        String userMessage = "";
        Cart cart = new Cart();
        Orders orders = new Orders();
        request.getSession().setAttribute("orders", orders);
        
        
        
        //get current action
        String action = request.getParameter("action");
      
        
        if(action == null){
            action = "Login"; //default form action
        }
        /**
        if(action.equals("submit")){
            url="page2.jsp";
        }
        **/
        
        
        //perform default interaction
        if(action.equals("Login")){ //logs in user if credentials exist
            //get parameters from form
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            //FormData data = new FormData(username, password);
            boolean loginSuccess = Customers.loginCustomer(username, password);
            if(loginSuccess){
                FormData data = Customers.getCustomerData(username, password);
                //message = "Successful login for "+data.getfName()+" "+data.getlName();
                userMessage="Current user: "+username;
                request.getSession().setAttribute("data", data);
                //request.setAttribute("message", message);
                request.setAttribute("userMessage", userMessage);
                request.getSession().setAttribute("cart", cart);
                String itemLink = "blank.jsp";
                request.getSession().setAttribute("itemLink", itemLink);
                url = "/page2.jsp";
            }else{
                message = "The login attempt failed. Please try again.";
                request.setAttribute("message", message);
                url = "/index.jsp";
            }
            
            
            //forward request/response to the url
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } 
        
        if(action.equals("Create")){ //indicates intent to create a new user
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("password", password);
            //request.setAttribute("username", username);
            
            request.getSession().setAttribute("cart", cart);
            
            url = "/newUser.jsp";
            
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
                
        if(action.equals("Submit")){ //creates a new User
            String username = request.getSession().getAttribute("username").toString();
            String password = request.getSession().getAttribute("password").toString();
            //String password = request.getParameter("password");
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            String email = request.getParameter("email");
            
            FormData data = new FormData(fName, lName, email, username, password );
            
            boolean createSuccess = Customers.createCustomer(data);
            if(createSuccess){
                //message = "Customer account succesfully created for "+fName+" "+lName;
                request.getSession().setAttribute("data", data);
                //request.setAttribute("message", message);
                url = "/page2.jsp";
                request.getSession().setAttribute("cart", cart);
                userMessage="Current user: "+username;
                request.setAttribute("userMessage", userMessage);
                String itemLink = "blank.jsp";
                request.getSession().setAttribute("itemLink", itemLink);
                
            }else{
                message = "These credentials are invalid. Please log in or try again.";
                request.setAttribute("message", message);
                url = "/index.jsp";
            }
            
            
            //forward request/response to the url
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
        if(action.equals("Log Out")){
            url="/index.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        if(action.equals("Go Back")){
            url="/login.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        if(action.equals("Sign In")){
            url="/login.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
        
        //processRequest(request, response);
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
