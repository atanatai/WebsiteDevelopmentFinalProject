<%@ include file="header.jsp" %>
    <body class="general">
        </br>
        <div class="darkBanner">
            <h3>Log in or create a new user</h3>
        </div>
        </br>
        <div class="form" id="rcorners1">
            <form action="FormServlet" method="POST" >
                <span class="smallerSize">Username: </span><input type="text" placeholder="Your User Name" name="username" size='30'/>
                <br/>
                <span class="smallerSize">Password: </span><input type="password" name="password" size='30'/>
                <br/>
                <input type='submit' value="Login" name='action'/>
                <input type='submit' value="Create" name='action'/>
            </form>
            <br/>
            <!--
            use get method to retrieve information submitted
            -->
        </div>
    </body>
<%@ include file="footer.jsp" %>

