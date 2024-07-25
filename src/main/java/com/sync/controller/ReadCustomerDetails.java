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

@WebServlet("/displayall")
public class ReadCustomerDetails extends HttpServlet {
    
    private static final int RECORDS_PER_PAGE = 10;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int totalRecords;
        int totalPages;
        List<Customer> all_details;
        HttpSession session = request.getSession();
        CustomerBoImp cBoImp = CustomerBoImp.objCustomerBoImp();

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        totalRecords = cBoImp.getTotalRecords();
        totalPages = (int) Math.ceil((double) totalRecords / RECORDS_PER_PAGE);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
                
        //for searchServlet
        session.setAttribute("crntPage", page);
        
        all_details = cBoImp.getAll((page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
        request.setAttribute("customer-details", all_details);


        RequestDispatcher rd = request.getRequestDispatcher("customerlist.jsp");
        rd.forward(request, response);
    }
}
