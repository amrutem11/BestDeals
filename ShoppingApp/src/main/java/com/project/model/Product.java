package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class Product {


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
	@Min(value = 1, message = "Please Enter a valid price")
	private Double price;
	
	@Column(name = "color")
	@NotBlank(message = "Please enter the color")
	private String color;
	
	@Column(name = "dimension")
	@NotBlank(message = "Please Enter the dimension")
	private String dimension;
	
	@Column(name = "manufacturer")
	@NotBlank(message = "Please Enter manufacturer")
	private String manufacturer;
}
