package com.boot.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.model.Admin;
import com.boot.repository.AdminRepository;
import com.boot.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired(required=true)

	private AdminRepository adminRepo;

	@Override
	public Admin login(String username, String password) {
		Admin adminLogin =adminRepo.findByUsername(username);
        if (adminLogin != null && adminLogin.getPassword().equals(password)) {
        	Admin admin = adminRepo.findByUsername(username);
            return adminLogin;
        }
        return null;
		
	}

}
