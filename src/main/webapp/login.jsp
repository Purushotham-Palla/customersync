<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login | Customer Sync</title>
    <link rel="icon" type="image/png" href="boy.png">
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <div class="background">
        <div class="login-container">
            <div class="login-form">
            <%
			    String errorMessage = (String) request.getAttribute("errorMessage");
			    if (errorMessage != null) {
			%>
			    <div class="error"><%= errorMessage %></div>
			<%
			    }
			%>
                <h2 class="form-title">Login</h2>
                <form action="loginUser" method="post">
                    <div class="form-group">
                        <label for="login-id">Login Id</label>
                        <input type="text" name="login_id" id="login-id" required placeholder="Enter your Login Id">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" required placeholder="Enter your password">
                    </div>
                    
                    <button type="submit" class="submit-button">Submit</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
