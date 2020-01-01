package com.examle.democicd.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler  {
	
	@ExceptionHandler(value = {PersonNotFoundException.class})
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public ErrorDetails handleNotFoundException(Exception e) {
		return ErrorDetails
				.builder()
				.localDateTime(LocalDateTime.now())
				.message(e.getMessage())
				.build();
		
	}

}
