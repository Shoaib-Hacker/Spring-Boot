package com.boot.conroller;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.model.Admin;
import com.boot.model.Customer;
import com.boot.model.RegisterDto;
import com.boot.repository.CustomerRepository;
import com.boot.service.CustomerService;

import jakarta.validation.Valid;

@Controller
public class CustomerController {
	@Autowired
    private CustomerRepository repo;
	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer/register")
	public String customerRegister(Model model) {
		RegisterDto registerDto = new RegisterDto();
		model.addAttribute(registerDto);
		model.addAttribute("success", false);

		return "/customer/register";
	}

	@PostMapping("/customer/register")
	public String customerRegister(Model model ,@Valid @ModelAttribute RegisterDto  registerDto, BindingResult result) {
		if (!registerDto.getPassword().equals(registerDto.getConfirmpassword())) {
			result.addError(
					new FieldError("registerDto", "cofirmpassword", "Password and Confirm Password do not Match")
					);
		}
		
		Customer customer = repo.findByEmail(registerDto.getEmail());
		if (customer != null) {
			result.addError(
					new FieldError("registerDto", "email", "Email address is already used") 
					);
		}
		
		if(result.hasErrors()) {
			return "/customer/register";
		}
		
		try {
			
			Customer newcustomer = new Customer();
			newcustomer.setFname(registerDto.getFname());
			newcustomer.setLname(registerDto.getLname());
			newcustomer.setEmail(registerDto.getEmail());
			newcustomer.setAddress(registerDto.getAddress());
			newcustomer.setPhone(registerDto.getPhone());
			newcustomer.setPassword(registerDto.getPassword());
			
			
			repo.save(newcustomer);
			
			
			model.addAttribute("registerDto", new RegisterDto());
			model.addAttribute("success", true);
					
			
		} catch (Exception e) {
			
			result .addError(
				new FieldError("registerDto", "fname", e.getMessage())
					
					);
		}
		
		
		
		return "/customer/register";
	}
	@GetMapping("/customer/login")
	public String customerLogin(
			Model model) {
			return "/customer/login";
	}

	@PostMapping("/customer/login")
	public String customerLogin(@RequestParam("email") String email, @RequestParam("password") String Password,
			Model model) {
		
		Customer login = customerService.findByEmailAndPassword(email, Password);
		if (login != null) {
			
			model.addAttribute("login", login);
			return "/customer/successlogin";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "/customer/login";
		}
		

	}
}
