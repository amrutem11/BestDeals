package com.project.dto;

import com.project.model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
   
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	
private Integer productId;
	
	private String prodcutName;
	
     private Double price;
	
	
	private String color;
	

	private String dimension;
	
	
	private String manufacture;
	
	private Integer quantity;

	public ProductDto(Integer productId, String prodcutName, Double price, String color, String dimension,
			String manufacture, Integer quantity) {
		super();
	
		this.productId = productId;
		this.prodcutName = prodcutName;
		this.price = price;
		this.color = color;
		this.dimension = dimension;
		this.manufacture = manufacture;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProdcutName() {
		return prodcutName;
	}

	public void setProdcutName(String prodcutName) {
		this.prodcutName = prodcutName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
