package com.sync.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sync.daoImp.CustomerBoImp;
import com.sync.models.Customer;

@WebServlet("/syncCustomers")
public class SyncCustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    	
    	PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	HttpSession session = request.getSession();
    	
    	String accessToken =(String)session.getAttribute("accessTokens");

//    	System.out.println("synccustomer "+accessToken);
    	
    	final String API_URL1 = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    	
    	final String TOKEN = accessToken;
        
    	try {
        
    		// Set up the connection
        	URL url = new URL(API_URL1 + "?cmd=get_customer_list");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + TOKEN);

            // Get the response
            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // Parse the response JSON
                ObjectMapper objectMapper = new ObjectMapper();
                Customer[] customers = objectMapper.readValue(content.toString(), Customer[].class);

                CustomerBoImp objCBoImp = CustomerBoImp.objCustomerBoImp();
            
                for (Customer customer : customers) {
              	  
//                	System.out.println(customer);
                	
                	String uuid = customer.getUuid();
                	
                	//Verifying does the uuid contains in database is exists
                	
                	int records = objCBoImp.getTotalRecords(uuid);
                	
                	if(records ==0)
                	{
                		int i = objCBoImp.save(customer);
//                		System.out.println("insert "+i);
                	}
                	else {
                		
                		int i = objCBoImp.update(customer);
//                		System.out.println( "update "+i);
                		
                	}
                	
              	  
                }
                
                int page =1;
        		
                page =(Integer) session.getAttribute("crntPage");
        		
        		
        		response.sendRedirect("displayall?page="+page);
                
                // Set response status and content type
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
            
            } else {
            	
            	out.println("Failed to fetch customer data");
            }
        } catch (Exception  e) {
            e.printStackTrace();
//            	out.println("An error occurred during synchronization");
        }
    }
 }
