package com.project.model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.project.dto.AddressDto;
import com.project.dto.ProductDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer orderId;
    private LocalDate orderDate;
    private String status;
    private Double total;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_order",joinColumns = @JoinColumn(name="order_id", referencedColumnName = "orderId"))
    private Customer customers;   
    
    @ElementCollection
	@CollectionTable(name="ordered_Product", joinColumns = @JoinColumn(name="order_id", referencedColumnName = "orderId"))
	private List<ProductDto> pList = new ArrayList<>();
	
	@Embedded
	private AddressDto orderAddress;
	
}
