package com.project.model;

import java.util.ArrayList;
import java.util.List;

import com.project.dto.ProductDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",referencedColumnName = "customerId")
    private Customer customer;
     
    @OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "carts_products", joinColumns = @JoinColumn(name ="cart_id", referencedColumnName = "cartId"))
	private List<ProductDto> products = new ArrayList<>();



}