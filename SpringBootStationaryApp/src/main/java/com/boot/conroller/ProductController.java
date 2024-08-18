package com.boot.conroller;

import java.io.FileInputStream;
import java.io.IOException;
import  java.io.InputStream;
import java.nio.file.Path;
import java.net.URI;
import java.nio.file.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.model.Product;
import com.boot.model.ProductDto;
import com.boot.repository.ProductRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductRepository repo;

	@GetMapping({ "", "/" })
	public String showProductListAdmin(Model model) {
		List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("products", products);
		return "products/product";
	}
    
	@GetMapping("/show")
	public String showProductListCustomer(Model model) {
		List<Product> products = repo.findAll();
		model.addAttribute("products", products);
		return "products/showProducts";
	}

	@GetMapping("/create")
	public String showCreateProductPage(Model model) {
		ProductDto productDto = new ProductDto();
		model.addAttribute("productDto" ,productDto);
		return "products/createProduct";
	}
	
	@PostMapping("/creat")
	public String createProduct(
			@Valid @ModelAttribute ProductDto productDto,
			BindingResult result) {
		
		if(productDto.getImageFile().isEmpty()) {
			result.addError(new FieldError("productDto" , "imageFile" , "The image file is required!"));
		}
		if (result.hasErrors()) {
			return "/products/createProduct";
		}
		
		//save image file
		MultipartFile image = productDto.getImageFile();
		String storageFileName = image.getOriginalFilename();
		 
		try {
			String uploadDir = "public/shop/";
			Path uploadPath = Paths.get(uploadDir);
			
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try(InputStream inputStream = image.getInputStream()){
				Files.copy(inputStream, Paths.get(uploadDir+storageFileName),
				StandardCopyOption.REPLACE_EXISTING);
			  }
			} catch (Exception e) {
				System.out.println("Exception" +e.getMessage());
			}
		Product product = new Product();
		product.setName(productDto.getName());
		product.setBrand(productDto.getBrand());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		product.setImageFileName(storageFileName);
        repo.save(product);
		return "redirect:/products/create";
	}
	
	@GetMapping("/edit")
	public String showEditPage(Model model , @RequestParam int id) {
	     
		try {
			Product product = repo.findById(id).get();
			model.addAttribute("product", product);
			
			ProductDto productDto = new ProductDto();
			productDto.setName(product.getName());
			productDto.setBrand(product.getBrand());
			productDto.setDescription(product.getDescription());
			productDto.setPrice(product.getPrice());
			
			model.addAttribute("productDto",productDto);
			
			
			
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
			return "redirect:/products";
		}
		
		
		return "products/EditProduct";
	}
	
	
	@PostMapping("/edit")
	public String updateProduct(Model model , @RequestParam("id") int id ,@Valid @ModelAttribute ProductDto productDto, BindingResult result) {
	     
		try {
			Product product = repo.findById(id).get();
			model.addAttribute("product", product);
			
			if (result.hasErrors()) {
				return "products/EditProduct";
			}
			
			if (!productDto.getImageFile().isEmpty()) {
				
			 //Delete old image
			String uploadDir ="public/shop/";
			Path oldImagePath =Paths.get(uploadDir+ product.getImageFileName());
			
			try {
				Files.delete(oldImagePath);
			} catch (Exception e) {
				
				System.out.println("Exception:" + e.getMessage());
			}
				
		 // save new  image file
		MultipartFile image =productDto.getImageFile();
		String storageFileName = image.getOriginalFilename();
		
		try(InputStream inputStream = image.getInputStream()) {
			Files.copy(inputStream, Paths.get(uploadDir+storageFileName) , StandardCopyOption.REPLACE_EXISTING);
			
			}
		  product.setImageFileName(storageFileName);
		}
			product.setName(productDto.getName());
			product.setBrand(productDto.getBrand());
			product.setDescription(productDto.getDescription());
			product.setPrice(productDto.getPrice());
			
			repo.save(product);
			
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
			
		}
		
		
		return "redirect:/products";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam int id) {
			
		try {
			Product product = repo.findById(id).get();
			
			//delete product image
			Path imagePath = Paths.get("public/shop/"+product.getImageFileName());
			try {
				Files.delete(imagePath);
			} catch (Exception ex) {
				System.out.println("Exception:" + ex.getMessage());
			}
			
			//delete product
			repo.delete(product);
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
		
		return "redirect:/products";
		
	}
	
	
	

}
