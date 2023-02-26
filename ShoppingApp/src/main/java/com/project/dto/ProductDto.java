package com.project.dto;


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

}
