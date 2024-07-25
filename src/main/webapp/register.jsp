<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Register | Customer Sync</title>
        <link rel="stylesheet" href="register.css">
        <link rel="icon" type="image" href="man.png">
    </head>
    <body>
        <div class="details">
            <div class="form">
                <form action="registerUser" method="post">
                    <h2>Register</h2>
                    <input type="text" name="login_id" id="text-box" placeholder="Login Id" required><br><br>
                    <input type="password" name="password" id="text-box" placeholder="Password" required><br><br>
                    <input type="submit" id="submit" value="Register">
                </form>
            </div>
        </div>
    </body>
</html>
