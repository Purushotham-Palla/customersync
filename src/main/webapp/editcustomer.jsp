<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.sync.daoImp.CustomerBoImp, com.sync.models.Customer,com.sync.util.JWTUtil  " %>

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
    <title>Editing Customer | Customer Sync</title>
    <link rel="icon" type="image/png" href="man.png">
    <link rel="stylesheet" href="editcustomer.css">
    
    
</head>
<body>

<%
    Customer  customer=(Customer) request.getAttribute("customer");
%>
    <p>Customer Details</p>
    <div class="details">
        <div class="form">
            <form action="updating?customerId=<%= customer.getId() %> " method="get">
                <input style="display:none" type="number" name="customerId" required value="<%=customer.getId()%>">
                
                <label for="firstname">First Name:</label>
                <input type="text" name="firstname" placeholder="First Name" required value="<%=customer.getFirst_name()%>"><br>
                
                <label for="lastname">Last Name:</label>
                <input type="text" name="lastname" placeholder="Last Name" required value="<%=customer.getLast_name()%>"><br>
                
                <label for="street">Street:</label>
                <input type="text" name="street" placeholder="Street" required value="<%=customer.getStreet()%>"><br>
                
                <label for="address">Address:</label>
                <input type="text" name="address" placeholder="Address" required value="<%=customer.getAddress()%>"><br>
                
                <label for="city">City:</label>
                <input type="text" name="city" placeholder="City" required value="<%=customer.getCity()%>"><br>
                
                <label for="state">State:</label>
                <input type="text" name="state" placeholder="State" required value="<%=customer.getState()%>"><br>
                
                <label for="email">Email:</label>
                <input type="email" name="email" placeholder="Email" required value="<%=customer.getEmail()%>"><br>
                
                <label for="phone">Phone:</label>
                <input type="tel" name="phone" placeholder="Phone" required value="<%=customer.getPhone()%>"><br>
                
                <input type="submit" name="submit" value="Update">
            </form>
        </div>
    </div>
</body>
</html>
