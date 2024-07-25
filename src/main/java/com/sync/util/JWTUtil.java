package com.sync.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
    private static final String SECRET_KEY = "secsret"; // use this is consistent
//
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // 10 minutes
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)//javax.xml.bind.DatatypeConverter
                .compact();
    }

    public static boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    ;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isAuthHeaderValid(String authHeader){
		
		boolean valid=false;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
//            System.out.println("token "+token);
            
            // Verify JWT token
            if (JWTUtil.verifyToken(token)) {
//                System.out.println("Welcome, " + token);
            	valid= true;
            }
		}
		return valid;
	}
    
    
//    public static Claims parseToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    
    
}
