package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	
	@NotBlank(message = "Street number should not be empty")
	private String streetNo;
	
	@NotBlank(message = "please enter a valid building name")
	private String buildingName;
	
	@NotBlank(message = "please enter a valid city")
	private String city;
	
	@NotBlank(message = "please enter a state")
	private String state;
	
	@NotBlank(message = "please enter a valid country name")
	private String country;
	
	@NotBlank(message = "please enter a valid pincode")
	private String pincode;

}
