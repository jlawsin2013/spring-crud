package com.project.self.crud.apierror;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {
	private String apiVersion;
	private ApiErrorDetails error;
	
	public ApiErrorResponse(String apiVersion, HttpStatus statusCode, String message, String reason) {
				this.apiVersion = apiVersion;
				this.error = new ApiErrorDetails(statusCode, message, reason);
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public ApiErrorDetails getError() {
		return error;
	}

	public void setError(ApiErrorDetails error) {
		this.error = error;
	}
}
