package com.project.model;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderProduct {
    
	@Id
	private Integer Id;
	
    private Integer orderId;
	
	private Integer productId;
	
	private Integer quantity;


}
