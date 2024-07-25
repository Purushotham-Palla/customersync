<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sync.models.Customer,com.sync.util.JWTUtil" %>

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
    <title>Customer List</title>
    <link rel="stylesheet" href="customerlists.css">
    <link rel="icon" type="image" href="man.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<h1>Customer List</h1>
    <div class="header">
        <div class="header-item">
         <a href="displayall" class="button" style="margin-right:70px">Home</a>
            <a href="addCustomer.jsp" class="button">Add Customer</a>
        </div>
        <div class="header-item">
        
            <form action="searchServlet" method="get" class="search-form">
                <label for="searchTitle">Search By</label>
                <select name="searchField" id="searchField">
                    <option value="firstname">First Name</option>
                    <option value="city">City</option>
                    <option value="email">Email</option>
                    <option value="phone">Phone</option>
                </select>
                <input type="text" id="searchTitle" name="searchTitle">
                <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
            </form>
        </div>
        <div class="header-item">
        	<a href="syncCustomers" class="button"  style="margin-right:70px">Sync</a>
        
            <a href="logoutservlet" onclick="return confirm('Are you sure you want to Logout ?');" class="button">Logout</a>
        </div>
    </div>

    
    <table>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Street</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th colspan="2">Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                		
                List<Customer> customerList = (List<Customer>) request.getAttribute("customer-details");
                for (Customer customer : customerList) {
            %>
            <tr>
                <td><%= customer.getFirst_name() %></td>
                <td><%= customer.getLast_name() %></td>
                <td><%= customer.getStreet() %></td>
                <td><%= customer.getAddress() %></td>
                <td><%= customer.getCity() %></td>
                <td><%= customer.getState() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getPhone() %></td>
                <td>
                <a href="editing?customerId=<%= customer.getId() %>"><i class="fa-solid fa-user-pen"></i></a>
                </td>
                
                <td><a href="deleting?customerId=<%= customer.getId() %>" onclick="return confirm('Are you sure you want to Delete ?');"><i class="fa-solid fa-user-minus"></i></a></td>
            </tr>
            <% } 
            %>
        </tbody>
    </table>
    
    <div class="pagination">
        <%
            int currentPage = (Integer) request.getAttribute("currentPage");
            int totalPages = (Integer) request.getAttribute("totalPages");
            String searchField =(String)request.getAttribute("searchField");
    		String searchTitle =(String)request.getAttribute("searchTitle");
    		if(totalPages>1)
    		{
    			
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
                	if(request.getAttribute("searchField") != null)
                	{
        %>
        
               	 <a href="searchServlet?page=<%= i %>&searchField=<%= searchField %>&searchTitle=<%= searchTitle %>" class="active"><%= i %></a>
          	<%      		
                	}
                	else{
           %>
            		<a  href="displayall?page=<%= i %>" class="active"><%= i %></a>
            <%	}
        
                } else {
                	if(request.getAttribute("searchField") != null)
                	{
                		
        	%>
                		<a href="searchServlet?page=<%= i %>&searchField=<%= searchField %>&searchTitle=<%= searchTitle %>"><%= i %></a>
             <% 	}
                	else{
              %>		
            	<a href="displayall?page=<%= i %>"><%= i %></a>
                	
        <%
                	}
                	
                }
            }
    	}
        %>
    </div>
</body>
</html>
