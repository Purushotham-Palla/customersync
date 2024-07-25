package com.sync.models;

public class Users {

	private int id;
	private String login_id,password;
	
	
	public Users(int id, String login_id, String password) {
		super();
		this.id = id;
		this.login_id = login_id;
		this.password = password;
	}


	//for Login
	public Users(String login_id, String password) {
		super();
		this.login_id = login_id;
		this.password = password;
	}
	
	
	public Users() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLogin_id() {
		return login_id;
	}


	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	

}
