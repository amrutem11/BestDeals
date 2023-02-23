package com.project.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> adminExceptionHandler(AdminException ie, WebRequest req)  {
		
		ErrorDetails err= new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> customerExceptionHandler(CustomerException ie, WebRequest req)  {
		
		ErrorDetails err= new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorDetails> loginExceptionHandler(LoginException ie, WebRequest req)  {
		
		ErrorDetails err= new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> productExceptionHandler(ProductException ie, WebRequest req)  {
		
		ErrorDetails err= new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> UserExceptionHandler(UserException ie, WebRequest req)  {
		
		ErrorDetails err= new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	
@ExceptionHandler(NoHandlerFoundException.class)
public ResponseEntity<ErrorDetails> noHandlerFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {
		

ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));

	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
				
}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(Exception e,WebRequest req)  {
	

		ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

	}
}
