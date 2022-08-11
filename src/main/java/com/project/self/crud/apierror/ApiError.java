package com.project.self.crud.apierror;

public class ApiError {
    private String reason;
    private String message;

    public ApiError(String reason, String message) {
        this.reason = reason;
        this.message = message;
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
}
