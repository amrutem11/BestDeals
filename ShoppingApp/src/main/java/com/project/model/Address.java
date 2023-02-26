package com.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@NotEmpty(message = "Street number should not be empty")
	@NotNull(message = "Street number should not be empty")
	private String streetNo;
	
	@NotBlank(message = "please enter a valid building name")
	@NotEmpty(message = "please enter a valid building name")
	@NotNull(message = "please enter a valid building name")
	private String buildingName;
	
	@NotBlank(message = "please enter a valid city")
	@NotEmpty(message = "please enter a valid city")
	@NotNull(message = "please enter a valid city")
	private String city;
	
	@NotBlank(message = "please enter a state")
	@NotEmpty(message = "please enter a state")
	@NotNull(message = "please enter a state")
	private String state;
	
	@NotBlank(message = "please enter a valid country name")
	@NotEmpty(message = "please enter a valid country name")
	@NotNull(message = "please enter a valid country name")
	private String country;
	
	@NotBlank(message = "please enter a valid pincode")
	@NotEmpty(message = "please enter a valid pincode")
	@NotNull(message = "please enter a valid pincode")
	@Size(min = 6, max = 6, message = "Pincode should be of 6 digit only")
	private String pincode;
	
}
