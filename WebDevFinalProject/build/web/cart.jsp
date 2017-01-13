<%-- 
    Document   : cart
    Created on : May 2, 2016, 4:12:55 PM
    Author     : Atan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<%@ include file="header.jsp" %>
    <body class="general">
        </br>
        </br>
        <div class="darkBanner">
            </br>
            <p>${data.fName}'s Cart:</p>
            </br>
        </div>
        </br>
        <c:set var="cartSize" value="${cart.getUniqueCartSize(data.username)}"/>
        <c:if test = "${cartSize > 0}">
        <table id="rcorners1">
            <tr id="plainfont">
                <th>Item Name</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            <c:forEach var="item" items="${cart.getCartItems(data.username)}">
                <tr>
                    <td> ${item.itemName} </td>
                    <td> ${item.itemPrice} </td>
                    <form  action="CartServlet" method="POST">
                        <td>
                            <input type="text" name="quantity" value="${item.quantity}" size="5">
                            <input type="hidden" name="itemId" value="${item.itemNumber}">
                        </td>
                        <td>
                            <input type="submit" value="Update" name="action">
                        </td>
                    </form>
                </tr>
                </tr>
            </c:forEach>
        </table>
        <p>There are ${cart.getCartSize(data.username)} items in your cart</p>
        <p>Total Cost: ${cart.getTotalCost(data.username)}</p>
        </c:if>
        <c:if test = "${cart.getUniqueCartSize(data.username) <= 0}">
            <h2>
                There are no items in your cart.
            </h2>
        </c:if>
        <div class="topRight">
            <form action="FormServlet" method="POST" >
                <input type='submit' value="Log Out" name='action'/>
            </form>
        </div>
        <form action="CartServlet" method="POST" >
                <input type='submit' value="Back to Shop" name='action'/>
                <input type='submit' value="Checkout" name='action'/>
        </form>
        </br>
    </body>
<%@ include file="footer.jsp" %>
