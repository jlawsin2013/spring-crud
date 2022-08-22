package com.project.self.crud.apierror;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
	private String apiVersion;
	private ErrorDetails error;
	
	public ErrorResponse(String apiVersion, HttpStatus statusCode, String message, String reason) {
		this.apiVersion = apiVersion;
		this.error = new ErrorDetails(statusCode, message, reason);
	}
	
	public ErrorResponse(String apiVersion, HttpStatus statusCode, String message, String reason, List<SubError> error) {
		this.apiVersion = apiVersion;
		this.error = new ErrorDetails(statusCode, message, reason, error);
	}
}
