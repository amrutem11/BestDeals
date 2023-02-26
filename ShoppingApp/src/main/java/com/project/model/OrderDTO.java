package com.project.model;

import java.time.LocalDate;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
private Integer orderId;
	
	private String customerName;
	
	private List<Product> list;
	
	private LocalDate localdate;
	
	private Double totalAmount;

}
