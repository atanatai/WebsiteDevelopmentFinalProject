

<%@ include file="header.jsp" %>
    <body class="general">
        </br>
        </br>
        <div class="darkBanner">
            <h3>Your order has been sent. Thank you for shopping!</h3>
        </div>
        <div class="topRight">
            <form action="FormServlet" method="POST" >
                <input type='submit' value="Log Out" name='action'/>
            </form>
        </div>
        <form action="CartServlet" method="POST" >
                <input type='submit' value="Back to Shop" name='action'/>
        </form>
    </body>
<%@ include file="footer.jsp" %>
