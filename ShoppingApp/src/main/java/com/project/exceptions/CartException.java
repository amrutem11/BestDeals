package com.project.exceptions;

public class CartException  extends RuntimeException{
     
	
	public CartException()
	{
		
	}
	
	public CartException(String mess)
	{
		super(mess);
	}
}
