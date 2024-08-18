package com.boot.service.implementation;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.model.Customer;
import com.boot.repository.CustomerRepository;
import com.boot.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	

	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepo.save(customer);
	}

	

	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		 Optional<Customer> findById=customerRepo.findById(id) ;
			if(findById.isPresent()) {
				Customer customer=findById.get();
				return customer;
			}else {
				return null;
			}	
	}

	

	@Override
	public void deleteVoter(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer findByEmailAndPassword(String email, String password) {
		 return customerRepo.findByEmailAndPassword(email, password);
	}

}
