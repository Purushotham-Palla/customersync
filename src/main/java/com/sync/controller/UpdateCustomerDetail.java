package com.sync.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sync.daoImp.CustomerBoImp;
import com.sync.models.Customer;

/**
 * Servlet implementation class Update
 */
@WebServlet("/updating")
public class UpdateCustomerDetail extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 1;
		
		//This session for to display the page where we edited 
		 
		HttpSession session = request.getSession();
		page =(Integer) session.getAttribute("crntPage");
		
//		System.out.println(page);
		
		int id = Integer.parseInt(request.getParameter("customerId"));
		String firstName = request.getParameter("firstname");
		String lastName= request.getParameter("lastname");
		String street = request.getParameter("street");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
			
//       int totalPages = (Integer) request.getAttribute("totalPages");
        
        
		Customer customer = new Customer(id,firstName,lastName, street,address, city, state,email, phone);
		
		CustomerBoImp customerBoImp = new CustomerBoImp();
		int i = customerBoImp.update(customer);
		
		response.sendRedirect("displayall?page="+page);
		
	}

	
	
}

