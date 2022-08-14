package com.project.self.crud.apierror;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDetails {
	private HttpStatus statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String reason;
    private String message;
	private List<SubError> subErrors;
	
	public ErrorDetails(HttpStatus statusCode, String message, String reason) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.reason = reason;
        this.message = message;
    }
    
    public ErrorDetails(HttpStatus statusCode, String message, String reason, List<SubError> subError) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.reason = reason;
        this.message = message;
        this.subErrors = subError;
    }

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SubError> getSubErrors() {
		return subErrors;
	}

	public void setSubErrors(List<SubError> subErrors) {
		this.subErrors = subErrors;
	}
    
}
