package com.sync.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sync.daoImp.UserBoImp;
import com.sync.models.Users;

@WebServlet("/registerUser")
public class RegisterUser extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login_id =request.getParameter("login_id");
		
		String password =request.getParameter("password");
		
//		System.out.println(login_id+" "+password);
	
		Users  c = new Users(login_id,password);
		
		UserBoImp userBoImp = UserBoImp.objUserBoImp();
		
		int i = userBoImp.register(c);
		
		PrintWriter out = response.getWriter();
		if(i == 1)
		{
			request.setAttribute("errorMessage", "User Registration Successfull, Please login.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		else {
			
			 request.setAttribute("errorMessage", "User already exists, please login.");
             request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	}
}
