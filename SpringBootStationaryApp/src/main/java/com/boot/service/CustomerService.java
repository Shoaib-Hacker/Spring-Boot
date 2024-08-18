package com.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.model.Customer;



public interface CustomerService {
	public Customer saveCustomer(Customer customer);
	public Customer findById(int id);
	public void deleteVoter(int id);
	public Customer findByEmailAndPassword(String email, String password);
}
