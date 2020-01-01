package com.examle.democicd.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {
	private LocalDateTime localDateTime;
	private String message;
}
