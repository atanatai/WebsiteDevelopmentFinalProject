
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="header.jsp" %>
    <body class="general">
        <div  class="darkBanner">
            <h2 class="smallerSize">Please provide the information below to finish creating an account for username <b>${username}</b>!</h2>
        </div>
        </br>
        <div class="form" id="rcorners1">
            <form action="FormServlet" method="POST" >
                <span class="smallerSize">First Name: </span><input type="text" placeholder="Your Human Label" name="fName" size='30'/>
                <br/>
                <span class="smallerSize">Last Name: </span><input type="text" placeholder="Your Clan" name="lName" size='30'/>
                <br/>
                <span class="smallerSize">Email: </span><input type="email" placeholder="The Real Mailbox" name="email" size='50'/>
                <br/>
                <input type='submit' value="Submit" name='action'/>
            </form>
        </div>
        <form action="FormServlet" method="POST" >
                <input type='submit' value="Go Back" name='action'/>
        </form>
    </body>
<%@ include file="footer.jsp" %>
