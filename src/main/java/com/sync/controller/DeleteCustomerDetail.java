package com.sync.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sync.daoImp.CustomerBoImp;

@WebServlet("/deleting")
public class DeleteCustomerDetail extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		CustomerBoImp customerBoImp = new CustomerBoImp();
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		int result = customerBoImp.delete(customerId);
		
		int page = 1;
		
		//This session for to display the page where we edited 
		 
		HttpSession session = request.getSession();
		page =(Integer) session.getAttribute("crntPage");
		
		
		if(result==1)
		{
			response.sendRedirect("displayall?page="+page);
			
		}
		else {
			out.println("Please Try again");
		}
		
		
	}



}

