package com.sync.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sync.daoImp.CustomerBoImp;
import com.sync.models.Customer;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
	 
	private static final int RECORDS_PER_PAGE = 10;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int page = 1;    
		
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		CustomerBoImp cBoImp = CustomerBoImp.objCustomerBoImp();
		
		int totalRecords;
		
		int totalPages ;
		
		List<Customer> all_details = null;
		HttpSession session = request.getSession();
		
			String searchField = request.getParameter("searchField");
			
			request.setAttribute("searchField", searchField);
		
			String searchTitle = request.getParameter("searchTitle");
			
			request.setAttribute("searchTitle", searchTitle);
			totalRecords=cBoImp.getTotalRecords(searchField,searchTitle);
			
			totalPages = (int) Math.ceil((double) totalRecords / RECORDS_PER_PAGE);
			
			all_details = cBoImp.getSearchBy(searchField,searchTitle,(page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
			request.setAttribute("currentPage", page);
			
			request.setAttribute("totalPages", totalPages);
			session.setAttribute("crntPage", page);
			
		
		request.setAttribute("customer-details", all_details);
		
		RequestDispatcher rd = request.getRequestDispatcher("customerlist.jsp?searchTitle="+searchTitle);
		rd.include(request, response);
		
		
	
	}


}
