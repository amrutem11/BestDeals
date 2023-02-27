package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int productId;
	
	@Column(name = "name")
	@NotNull(message = "Product name should not be null")
	@NotBlank(message = "Please Enter the product Name")
	private String productName;


	@Column(name = "specification")
	@NotNull(message = "specification should not be null")
    @NotBlank(message = "Please enter the product specification")
    private String specification;
	
	
	@NotNull(message = "quantity should not be null")
    @NotBlank(message = "Please enter the product quantity")
	@Column(name = "quantity")
    private int quantity;
	
	@ManyToOne
    @JsonIgnore
    private Category category;
	
	@NotNull(message = "price should not be null")
	@Min(value = 1, message = "Please Enter a valid price")
	private Double price;
	
	@Column(name = "color")
	@NotNull(message = "quantity should not be null")
	@NotBlank(message = "Please enter the color")
	private String color;
	
	@Column(name = "dimension")
	@NotNull(message = "dimensions should not be null")
	@NotBlank(message = "Please Enter the dimension")
	private String dimension;
	
	@Column(name = "manufacturer")
	@NotBlank(message = "Please Enter manufacturer")
	private String manufacturer;
}
