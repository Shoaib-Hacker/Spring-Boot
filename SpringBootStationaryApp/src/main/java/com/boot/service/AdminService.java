package com.boot.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.boot.model.Admin;


public interface AdminService {

	public Admin login(String username, String password);
	
}
