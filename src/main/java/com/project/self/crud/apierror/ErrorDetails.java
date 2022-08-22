package com.project.self.crud.apierror;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
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
}
