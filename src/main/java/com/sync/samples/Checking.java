package com.sync.samples;

import com.sync.util.JWTUtil;

public class Checking {

	public Checking() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		String token = JWTUtil.generateToken("rahul");
		
		
		System.out.println("token " + token);
		
		
		 if (JWTUtil.verifyToken(token)) {
             System.out.println("Welcome, " + token);
		 }
		
	}
	

	
	

}
