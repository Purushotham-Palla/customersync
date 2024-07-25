<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.sync.util.JWTUtil" %>

<%
    String authHeader =(String)session.getAttribute("Authorization");
    
    if(JWTUtil.isAuthHeaderValid(authHeader) == false)
    {
    	//request.setAttribute("errorMessage", "Access denied , Please login.");
    	response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Customer Details | Customer Sync</title>
    <link rel="stylesheet" type="text/css" href="editcustomer.css">
    <link rel="icon" type="image" href="man.png">
</head>
<body>
    
    <div class="navbar">
        <button id="home-button">
            <a href="displayall">Home</a>
        </button>
        <div class="title">
            Customer Details
        </div>
        <button id="logout-button">
            <a href="logoutservlet" onclick="return confirm('Are you sure you want to Logout ?');" >LogOut</a>
        </button>
    </div>
    
    <div class="details">
        <div class="form">
            <form action="addCustomer" method="get">
                <label for="firstname">First Name:</label>
                <input type="text" name="firstname" placeholder="First Name" id="text-box" required>

                <label for="lastname">Last Name:</label>
                <input type="text" name="lastname" placeholder="Last Name" id="text-box" required><br>
                
                <label for="street">Street:</label>
                <input type="text" name="street" placeholder="Street" id="text-box" required>
                
                <label for="address">Address:</label>
                <input type="text" name="address" placeholder="Address" id="text-box" required><br>

                <label for="city">City:</label>
                <input type="text" name="city" placeholder="City" id="text-box" required>
                
                <label for="state">State:</label>
                <input type="text" name="state" placeholder="State" id="text-box" required><br>
                
                <label for="email">Email:</label>
                <input type="email" name="email" placeholder="Email" id="text-box" required>
                
                <label for="phone">Phone:</label>
                <input type="tel" name="phone" placeholder="Phone" id="text-box" required><br>
                
                <input type="submit" name="submit" id="submit">
            </form>
        </div>
    </div>
</body>
</html>
