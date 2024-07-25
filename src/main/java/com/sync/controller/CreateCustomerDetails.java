package com.sync.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sync.daoImp.CustomerBoImp;
import com.sync.models.Customer;

@WebServlet("/addCustomer")
public class CreateCustomerDetails extends HttpServlet {
	
//	https://github.com/Purushotham-Palla/customersync.git
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstname = request.getParameter("firstname");
		
		String lastname = request.getParameter("lastname");
		
		String street = request.getParameter("street");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String email = request.getParameter("email");
		
		String phone = request.getParameter("phone");		
	
		Customer customer = new Customer(firstname,  lastname,  street,  address,  city,  state,
				 email,  phone); 
		 
		CustomerBoImp customerBoimp = new CustomerBoImp();
		
		int result = customerBoimp.save(customer);
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			
//			out.println("Result="+"Succefully Added");
			response.sendRedirect("displayall");
		}
		else {
			out.println("Result="+"UnSuccefully Added");
			
		}
		
	}

}
