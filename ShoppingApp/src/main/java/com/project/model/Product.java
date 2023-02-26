package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class Product {
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int productId;
	
	@Column(name = "name")
	@NotBlank(message = "Please Enter the product Name")
	private String productName;


	@Column(name = "specification")
    @JsonIgnore
    @NotBlank(message = "Please enter the product specification")
    private String specification;
	
	@Column(name = "quantity")
    private int quantity;
	
	@ManyToOne
    @JsonIgnore
    private Category category;
	
	
	@Column(name = "color")
	@NotBlank(message = "Please enter the color")
	private String color;
	
	@Column(name = "dimension")
	@NotBlank(message = "Please Enter the dimension")
	private String dimension;
	
	@Column(name = "manufacturer")
	@NotBlank(message = "Please Enter manufacturer")
	private String manufacturer;

	public Double getPrice() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
