<!DOCTYPE html>
<!--
Kai Rahm
Website Development Final Project
05/23/16
-->
<%@ include file="header.jsp" %>
    <body class="general">
        </br>
        </br>

        <div class="darkBanner">
            <h3>Hello there! Please log in to enter the store!</h3>
        </div>
        </br>
        <div class="topRight">
            <form action="FormServlet" method="POST" >
                <input type='submit' value="Sign In" name='action'/>
            </form>
        </div>
    </body>
<%@ include file="footer.jsp" %>
