package com.project.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	
	private LocalDateTime timestamp;
	private String message;
	private String details;

}
