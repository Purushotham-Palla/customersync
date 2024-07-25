package com.sync.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sync.daoImp.UserBoImp;
import com.sync.models.Users;
import com.sync.util.JWTUtil;

@WebServlet("/loginUser")
public class LoginChecking extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login_id = request.getParameter("login_id");
        String password = request.getParameter("password");
        
        HttpSession session = request.getSession();
        
        UserBoImp userBoImp = UserBoImp.objUserBoImp();
        
        Users usr = userBoImp.getLogin(login_id, password);
        
        if (usr != null) {
            String token = JWTUtil.generateToken(login_id);
            
//            System.out.println("token " + token);
            
            session.setAttribute("Authorization", "Bearer " + token);
            
            // Redirect to the authenticate for sunbase login details 
            
            response.sendRedirect("authenticate");
//            response.sendRedirect("displayall");
            
        } else {
            request.setAttribute("errorMessage", "Invalid credentials. Please try again");
            request.getRequestDispatcher("login.jsp").forward(request, response);;
        }
    }
}
