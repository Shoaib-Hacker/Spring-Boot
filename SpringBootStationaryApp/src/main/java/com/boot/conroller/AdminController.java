package com.boot.conroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.model.Admin;
import com.boot.model.Product;
import com.boot.service.AdminService;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	ProductController pc ;

	@GetMapping("/admin")
	public String showLoginForm() {
		return "admin/AdminLogin";
	}
    
	@PostMapping("/admin/login")
	public String login(@RequestParam("username") String Username, @RequestParam("password") String Password,
			Model model) {
		Admin adminLogin = adminService.login(Username, Password);
		if (adminLogin != null) {
			
			model.addAttribute("loginAdmin", adminLogin);
			return "/admin/AdminDashboard"; // redirect to a dashboard or home page
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "admin/AdminLogin";
		}
	}

}
