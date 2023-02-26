package com.project.exceptions;

public class OrderExcetion extends RuntimeException {
          
	public OrderExcetion()
	{
		
	}
	
	public OrderExcetion(String mess)
	{
		super(mess);
	}
}
