package com.sync.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sync.dao.UserBo;
import com.sync.models.Users;

public class UserBoImp implements UserBo {
	
	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static Statement statement;
	private static ResultSet res;	
	

	//Register
	private final static String RIGISTER_QUERY = "INSERT into `users`(`login_id`,`password`) values(?,?)";
	//Login
	private final static String GET_LOGIN_QUERY = "SELECT * FROM  `users` WHERE `login_id`=? AND `password`=?";
		
	
	
	public static final UserBoImp objUserBoImp() {
		return new UserBoImp();
	}
	
	public UserBoImp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_sync","root","root");			
			
		} catch (ClassNotFoundException |SQLException e1) {
			e1.printStackTrace();
		} 
		
	}
	
	
	
	//Login
		@Override
		public Users getLogin(String login_id,String password)
		{
			Users usr=null;
			
			try {
				
				prepareStatement=connection.prepareStatement(GET_LOGIN_QUERY);
				prepareStatement.setString(1, login_id);
				prepareStatement.setString(2, password);
				
				ResultSet res = prepareStatement.executeQuery();
				
				if(res.next())
				{
					login_id = res.getString("login_id");
					password = res.getString("password");
					usr=new Users(login_id,password);
					
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return usr;
			
		}
		
		
		
	//Register
		@Override
		public int register(Users usr)
		{
			try{
				prepareStatement = connection.prepareStatement(RIGISTER_QUERY);
				
				prepareStatement.setString(1, usr.getLogin_id());
				prepareStatement.setString(2, usr.getPassword());
				int i = prepareStatement.executeUpdate();
				
				return i;
				
			}
			catch(SQLException e )
			{
				e.printStackTrace();
				return 0;
				
			}
//			return 0;
			
		}
		

}
