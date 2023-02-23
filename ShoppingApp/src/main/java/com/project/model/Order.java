package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Order {

	@Id
	private Integer orderId;
}
