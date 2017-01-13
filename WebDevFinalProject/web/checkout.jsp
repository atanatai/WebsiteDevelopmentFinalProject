

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<%@ include file="header.jsp" %>
    <body class="general">
        </br>
        </br>
        <div class="darkBanner">
            <p>Checkout</p>
            <p class="smallerSize">There are ${cart.getCartSize(data.username)} items in your cart</p>
            <p class="smallerSize">Total Cost: ${cart.getTotalCost(data.username)}</p>
        </div>
        </br>
        <table id="rcorners1">
            <tr id="plainfont">
                <th>Item Name</th>
                <th>Price</th>
                <th>Item Description</th>
                <th>Quantity</th>
            </tr>
            <c:forEach var="item" items="${cart.getCartItems(data.username)}">
                <tr>
                    <td> 
                        ${item.itemName}
                    </td>
                    <td> ${item.itemPrice} </td>
                    <td> ${item.itemDescription} </td>
                    <form  action="CartServlet" method="POST">
                        <td>
                            ${item.quantity}
                            <input type="hidden" name="itemId" value="${item.itemNumber}">
                        </td>
                        <td>
                            <input type="submit" value="Delete" name="action">
                        </td>
                    </form>
                </tr>
                </tr>
            </c:forEach>
        </table>
        <div id="rcorners2">
        <form action="CartServlet" method="POST">
            Name on Card <input type="text" value="${data.fName} ${data.lName}" name="cardName" size='30'/>
            <br/>
            Card Number <input type="text" placeholder="The 16 numbers" name="cardNumber" size='16'/>
            <br/>
            CSV <input type="password"  name="csv" size='3'/>
            <br/>
            Expiration <input type="text" placeholder="mm/dd/yy" name="date" size='11'/>
            <br/>
            Email <input type="text" value="${data.email}" name="email" size='30'/>
            <br/>
            <input type="submit" value="Purchase" name="action">
        </form>
        </div>
        
        <div class="topRight">
            <form action="FormServlet" method="POST" >
                <input type='submit' value="Log Out" name='action'/>
            </form>
        </div>
        <form action="CartServlet" method="POST" >
                <input type='submit' value="Back to Shop" name='action'/>
                <input type='submit' value="View Cart" name='action'/>
        </form>
    </body>
<%@ include file="footer.jsp" %>
