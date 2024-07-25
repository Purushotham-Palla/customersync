package com.sync.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sync.dao.CustomerBo;
import com.sync.models.Customer;

public class CustomerBoImp implements CustomerBo {
	
	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static Statement statement;
	private static ResultSet res;	
	
	
	
	//Customer Details
	private final static String INSERT_CUSTOMER_QUERY = "INSERT into `customerdetails`(`firstname`,`lastname`,`street`,`address`,`city`,`state`,`email`,`phone`,`uuid`) values(?,?,?,?,?,?,?,?,?)";
	
	private final static String UPDATE_CUSTOMER_QUERY = "UPDATE `customerdetails` SET firstname=?,lastname=?, street = ?, address = ?,  city = ?, state = ?, email = ?, phone = ? "; 
	
	private final static String GET_CUSTOMER_QUERY = "SELECT * FROM  `customerdetails`";
	
	private final static String DELETE_CUSTOMER_QUERY = "DELETE FROM `customerdetails` WHERE `id` = ?";

	private final static String COUNT_RECORDS = "SELECT COUNT(*) FROM `customerdetails`";

	public static final CustomerBoImp objCustomerBoImp() {
		return new CustomerBoImp();
	}
	
	
	public CustomerBoImp() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_sync","root","root");			
			
		} catch (ClassNotFoundException |SQLException e1) {
			e1.printStackTrace();
		} 
	}
	

//Insert Customer Details
	@Override
	public int save(Customer c) {
		try {
			
			prepareStatement = connection.prepareStatement(INSERT_CUSTOMER_QUERY);
			prepareStatement.setString(1, c.getFirst_name());
			prepareStatement.setString(2, c.getLast_name());
			prepareStatement.setString(3, c.getStreet());
			prepareStatement.setString(4, c.getAddress());
			prepareStatement.setString(5, c.getCity());
			prepareStatement.setString(6, c.getState());
			prepareStatement.setString(7, c.getEmail());
			prepareStatement.setString(8, c.getPhone());
			
			if(c.getUuid() == null)
			{
				prepareStatement.setString(9,null);
				
			}
			else {
				
				prepareStatement.setString(9,c.getUuid());
			}
			
			 
			 int i = prepareStatement.executeUpdate();
			 
			 return i;
			 
			 
		} catch (SQLException c2)
		{
			
			c2.printStackTrace();
		}
		return 0; 
		
	}
	
//Update a customer
	@Override
	public int update(Customer c) {
		
		String UPDATE_QUERY;
		
		if(c.getUuid() !=null)
		{
			UPDATE_QUERY = UPDATE_CUSTOMER_QUERY + " WHERE `uuid` =?";
			
		}
		else {
			UPDATE_QUERY = UPDATE_CUSTOMER_QUERY + " WHERE `id` =?";
			
		}

		try {
				prepareStatement = connection.prepareStatement(UPDATE_QUERY);
				prepareStatement.setString(1, c.getFirst_name());
				prepareStatement.setString(2, c.getLast_name());
				prepareStatement.setString(3, c.getStreet());
				prepareStatement.setString(4, c.getAddress());
				prepareStatement.setString(5, c.getCity());
				prepareStatement.setString(6, c.getState());
				prepareStatement.setString(7, c.getEmail());
				prepareStatement.setString(8, c.getPhone());
				
//				WHERE `id` = ? or `uuid` =?";
				
				
				if(c.getUuid()!=null)
				{
					prepareStatement.setString(9,c.getUuid());
					
				}
				else {
					prepareStatement.setInt(9, c.getId());
					
				}
					
				int i = prepareStatement.executeUpdate();
				
				return i;
				
				
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
			
			return 0;
			
			
		}
		
	
//Deleting customer by using id
	@Override
	public int delete(int id) {
		try {
				prepareStatement = connection.prepareStatement(DELETE_CUSTOMER_QUERY);
				
				prepareStatement.setInt(1, id);
				
				int i = prepareStatement.executeUpdate();
				return i;
				
		} catch (SQLException e) {
				e.printStackTrace();
		}
			return 0;
		}

		
//Fetch One Customer
	@Override
	public Customer getOne(int id) {
			
	Customer e=null;
	String GET_ONE_QUERY =  GET_CUSTOMER_QUERY +" WHERE `id` = ?";
	try {
		prepareStatement = connection.prepareStatement(GET_ONE_QUERY);
				
		prepareStatement.setInt(1, id);
				
		res  = prepareStatement.executeQuery();
				
			if(res.next()){
					
					int id1 = res.getInt("id");
					String firstname = res.getString("firstname");
					String lastname = res.getString("lastname");
					String street = res.getString("street");
					String address = res.getString("address");
					String city = res.getString("city");
					String state = res.getString("state");
					String email = res.getString("email");
					String phone = res.getString("phone");
					e=new Customer(id1,firstname,lastname,street,address,city,state,email,phone);
			}
				return e;
			
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			return null;
		}
	
	

	



	@Override
	public List<Customer> getSearchBy(String searchField,String searchTitle) {
		
		ArrayList<Customer> list = new ArrayList<Customer>();
		
//		searchField--> column names
		
		//values
		String searchTitles = searchTitle.toLowerCase().trim();
		
		String searchBy = GET_CUSTOMER_QUERY +" WHERE "+searchField+ " LIKE '%"+searchTitles +"%'";
		
		try {
			
			 prepareStatement = connection.prepareStatement(searchBy);
			 res = prepareStatement.executeQuery();
			 
			 while(res.next())
			 {
				
				 	int id = res.getInt("id");
					String firstname = res.getString("firstname");
					String lastname = res.getString("lastname");
					String street = res.getString("street");
					String address = res.getString("address");
					String city = res.getString("city");
					String state = res.getString("state");
					String email = res.getString("email");
					String phone = res.getString("phone");
					
//					System.out.println(firstname+" "+lastname);
					Customer c = new Customer(id,firstname,lastname,street,address,city,state,email,phone);
					
					list.add(c);
			 }
			 return list;
			 
			 
		} catch (Exception e) {
			

				e.printStackTrace();
		}
		
		
		return list;
	}
	
	

//for pagination getall
		public List<Customer>  getAll(int offset, int noOfRecords) {
			
			ArrayList<Customer> list = new ArrayList<Customer>();
			

		String PAGINATION_QUERY = GET_CUSTOMER_QUERY +" LIMIT ?, ?";
			try {
				prepareStatement = connection. prepareStatement(PAGINATION_QUERY);
				prepareStatement.setInt(1, offset);
				prepareStatement.setInt(2, noOfRecords);
				res= prepareStatement.executeQuery();
				
				while(res.next())
				{
					int id = res.getInt("id");
					String firstname = res.getString("firstname");
					String lastname = res.getString("lastname");
					String street = res.getString("street");
					String address = res.getString("address");
					String city = res.getString("city");
					String state = res.getString("state");
					String email = res.getString("email");
					String phone = res.getString("phone");
					
					Customer c = new Customer(id,firstname,lastname,street,address,city,state,email,phone);
					
					list.add(c);
				}
				
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return null;
			
		}
		
		
//for getSearchyBY
		@Override
		public List<Customer> getSearchBy(String searchField, String searchTitle, int offset, int recordsPerPage) {
			ArrayList<Customer> list = new ArrayList<Customer>();
			
//			searchField--> column names
			
			// searchTitles --> values
			String searchTitles = searchTitle.toLowerCase().trim();
			
			String searchBy = GET_CUSTOMER_QUERY +" WHERE "+searchField+ " LIKE '%"+searchTitles +"%' "+" LIMIT "+offset+","+recordsPerPage;
			
			try {
				
				 prepareStatement = connection.prepareStatement(searchBy);
				 res = prepareStatement.executeQuery();
				 
				 while(res.next())
				 {
					
					 	int id = res.getInt("id");
						String firstname = res.getString("firstname");
						String lastname = res.getString("lastname");
						String street = res.getString("street");
						String address = res.getString("address");
						String city = res.getString("city");
						String state = res.getString("state");
						String email = res.getString("email");
						String phone = res.getString("phone");
						
						Customer c = new Customer(id,firstname,lastname,street,address,city,state,email,phone);
						
						list.add(c);
				 }
				 return list;
				 
			}
			catch (Exception e) {
            e.printStackTrace();
			}
			return list;
		}

//for GetAll
				@Override
				public int getTotalRecords() {
			        int totalRecords = 0;
			        
			        try {
			        	prepareStatement = connection. prepareStatement(COUNT_RECORDS);
			            res = prepareStatement.executeQuery();
			            if (res.next()) {
			                totalRecords = res.getInt(1);
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        return totalRecords;
			    }
 
//for SearchBy
		
		@Override
		public int getTotalRecords(String searchField, String searchTitle) {
			
			int totalRecords = 0;
			String searchTitles = searchTitle.toLowerCase().trim();
			
			String searchBy = COUNT_RECORDS +" WHERE "+searchField+ " LIKE '%"+searchTitles +"%'";
			
	        try {
	        	prepareStatement = connection. prepareStatement(searchBy);
	            res = prepareStatement.executeQuery();
	            if (res.next()) {
	                totalRecords = res.getInt(1);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//	        System.out.println("totalRecords"+totalRecords);
	        return totalRecords;
		}
		
//for api to insert data 
		
		public int getTotalRecords(String uuid) {
	        int totalRecords = 0;
	        String query = COUNT_RECORDS+" WHERE `uuid` = ?";
	        
	        
	        try {
	        	prepareStatement = connection. prepareStatement(query);
	        	prepareStatement.setString(1, uuid);
	            res = prepareStatement.executeQuery();
	            if (res.next()) {
	                totalRecords = res.getInt(1);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return totalRecords;
	    }
		
//-------------------------------------------------------------------------------------------

//		@Override
//		public List<Customer> getAll() {
//				ArrayList<Customer> list = new ArrayList<Customer>();
//				
//				try {
//					statement = connection.createStatement();
//					res= statement.executeQuery(GET_CUSTOMER_QUERY);
//					
//					while(res.next())
//					{
//						int id = res.getInt("id");
//						String firstname = res.getString("firstname");
//						String lastname = res.getString("lastname");
//						String street = res.getString("street");
//						String address = res.getString("address");
//						String city = res.getString("city");
//						String state = res.getString("state");
//						String email = res.getString("email");
//						String phone = res.getString("phone");
//						
//						Customer c = new Customer(id,firstname,lastname,street,address,city,state,email,phone);
//						
//						list.add(c);
//						
//					}
//					
//					return list;
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				
//				return null;
//			}
			
//		@Override
//		public List<Customer> getPage1() {
//				ArrayList<Customer> list = new ArrayList<Customer>();
//				
//				try {
//					statement = connection.createStatement();
//					res= statement.executeQuery(GET_CUSTOMER_QUERY);
//					
//					while(res.next())
//					{
//						int id = res.getInt("id");
//						String firstname = res.getString("firstname");
//						String lastname = res.getString("lastname");
//						String street = res.getString("street");
//						String address = res.getString("address");
//						String city = res.getString("city");
//						String state = res.getString("state");
//						String email = res.getString("email");
//						String phone = res.getString("phone");
//						
//						Customer c = new Customer(id,firstname,lastname,street,address,city,state,email,phone);
//						
//						list.add(c);
//						
//					}
//					
//					return list;
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				
//				return null;
//			}

	

}
