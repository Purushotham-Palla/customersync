package com.sync.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/authenticate")
public class AuthenticationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private String loginId = "test@sunbasedata.com";
    private String password = "Test@123";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        HttpPost httpPost = new HttpPost("https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp");
        
        httpPost.setHeader("Content-Type", "application/json");

        //data to insert into json format;
        
        JSONObject json = new JSONObject();
        
        json.put("login_id", loginId);
        json.put("password", password);
        
        
        
        StringEntity entity = new StringEntity(json.toString());
        httpPost.setEntity(entity);
        
//        System.out.println(entity);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        
        String responseString = EntityUtils.toString(httpResponse.getEntity());
//        System.out.println("responseString "+responseString);
        
        httpResponse.close();

        try {

            // the access token is in the response string
            JSONObject responseObject = new JSONObject(responseString);
            
            if (responseObject.has("access_token")) {
            	
            	//for server
                String accessToken = responseObject.getString("access_token");
//                System.out.println("Authentication "+accessToken);
                
                 HttpSession session = request.getSession();
                 session.setAttribute("accessTokens", accessToken);
                
                 response.sendRedirect("displayall");
            }
        } catch (JSONException e) {
            e.printStackTrace();
//            out.println("Error parsing JSON response.");
        } finally {
            httpClient.close();
        }
    }
}


