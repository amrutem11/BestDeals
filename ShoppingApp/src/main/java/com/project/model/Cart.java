package com.project.model;

import java.util.ArrayList;
import java.util.List;

import com.project.dto.ProductDto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
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

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	


}