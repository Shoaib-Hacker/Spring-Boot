package com.boot.model;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class ProductDto {
	
	@jakarta.validation.constraints.NotEmpty(message="The name is required")
	private String name;
	
	@jakarta.validation.constraints.NotEmpty(message="The name is required")
	private String brand;
	
	@Size(min =10,message ="The Description should be atleast 10 Characters")
	@Size(max =2000,message ="The Description cannot exceed 2000 Characters")
	private String description;
	
	@Min(0)
	private double price;
	
	private  MultipartFile imageFile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	
}
