package com.sync.dao;

import java.util.List;

import com.sync.models.Customer;

public interface CustomerBo {
	
	int save(Customer c);
	
	int update(Customer c);
	
	int delete(int id);
	
	Customer  getOne(int id);
	
//	List<Customer > getAll();
	
	List<Customer>  getAll(int offset, int noOfRecords);
	
	List<Customer> getSearchBy(String searchField, String searchTitle);
	
	int getTotalRecords(); 
	
	List<Customer> getSearchBy(String searchField, String searchTitle, int offset, int recordsPerPage) ;

	int getTotalRecords(String searchField, String searchTitle) ;
	

}
