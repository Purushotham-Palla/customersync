package com.sync.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sync.daoImp.CustomerBoImp;
import com.sync.models.Customer;

@WebServlet("/editing")
public class EditCustomerDetail extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			
			
			CustomerBoImp objCBoImp = CustomerBoImp.objCustomerBoImp();
			
			Customer customer = objCBoImp.getOne(customerId); 
			
			//setting attribute 
			request.setAttribute("customer", customer);
			
			request.getRequestDispatcher("editcustomer.jsp").forward(request, response);

			//response.sendRedirect("editcustomer.jsp?customerId="+customerId);
			
				
			
	}
		
	

}
