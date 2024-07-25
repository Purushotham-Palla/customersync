package com.sync.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sync.util.JWTUtil;



@WebServlet("/login")
public class AuthServlets extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate user credentials
        
        if ("admin".equals(username) && "password".equals(password)) {
            String token;
				token = JWTUtil.generateToken(username);
            response.setHeader("Authorization", "Bearer " + token);
            response.sendRedirect("customers.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
        
    }
}

