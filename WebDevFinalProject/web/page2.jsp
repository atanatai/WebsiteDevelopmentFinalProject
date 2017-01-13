<%-- 
    Document   : page2
    Author     : Atan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>
    <body class="general">
        </br>
        </br>

        <div class="darkBanner">
            <p>Hello ${data.fName} ${data.lName}!</p>
            <c:if test = "${cart.getUniqueCartSize(data.username) > 0}">
                <p class="smallerSize">There are ${cart.getCartSize(data.username)} items in your cart</p>
            </c:if>
        </div>
        
            <p>Things you can buy:</p>
            <table id="rcorners1">
            <tr id="plainfont">
                <th>Item Name</th>
                <th>Item Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${cart.getAllAvailableItems()}">
                <tr>
                    <form action="CartServlet" method="POST" >
                    <td> 
                        ${item.itemName}
                        <input type="hidden" name="itemName" value="${item.itemName}">
                        <input type="hidden" name="itemNumber" value="${item.itemNumber}">
                        <input type="hidden" name="qtyAvailable" value="${item.quantity}">
                        <input type="hidden" name="itemLink" value="${item.itemLink}">
                    </td>
                    <td> 
                        ${item.itemDescription} 
                        <input type="hidden" name="itemDescription" value="${item.itemDescription}">
                    </td>
                    <td>
                        ${item.itemPrice} 
                        <input type="hidden" name="itemPrice" value="${item.itemPrice}">
                    </td>
                    <td>
                    <select name='quantity'>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                    </td>
                    <td>
                        <input type='submit' value="Add" name='action'/>
                        <input type='submit' value="View" name='action'/>
                    </td>
                    </form>
                </tr>
            </c:forEach>
            </table>
            
            <form action="CartServlet" method="POST" >
                <input type='submit' value="View Cart" name='action'/>
            </form>
            <div class="topRight">
                <form action="FormServlet" method="POST" >
                <input type='submit' value="Log Out" name='action'/>
                </form>
            </div>
            </br>
            
            <iframe width="75%" height="500px" src="${itemLink}" name="iframe"></iframe>
            
            
            
        </br>
            
    </body>
    
    
<%@ include file="footer.jsp" %>