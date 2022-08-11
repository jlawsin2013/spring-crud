package com.project.self.crud.exceptionHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.self.crud.apierror.ApiErrorResponse;
import com.project.self.crud.exception.UserNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Value("${api.version}")
	private String currentApiVersion;
	
	@Override
    protected ResponseEntity<Object> handleMissingPathVariable(
    		MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                currentApiVersion,
                BAD_REQUEST,
                ex.getParameter() + " path variable is missing",
                ex.getClass().getSimpleName()
        );
        
        return new ResponseEntity<Object>(error, BAD_REQUEST);
    }
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiErrorResponse error = new ApiErrorResponse(
                currentApiVersion,
                NOT_FOUND,
                "Sorry, this URL does not exist or is no longer available",
                ex.getClass().getSimpleName()
        );
        
        return new ResponseEntity<Object>(error, NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiErrorResponse error = new ApiErrorResponse(
                currentApiVersion,
                BAD_REQUEST,
                "Malformed JSON in request body",
                ex.getClass().getSimpleName()
        );
        
        return new ResponseEntity<Object>(error, BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiErrorResponse error = new ApiErrorResponse(
                currentApiVersion,
                METHOD_NOT_ALLOWED,
                "The HTTP method in the request is not allowed on the resource",
                ex.getClass().getSimpleName()
        );
        
        return new ResponseEntity<Object>(error, METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleNonExistingUser(UserNotFoundException ex, WebRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                currentApiVersion,
                NOT_FOUND,
                ex.getMessage(),
                ex.getClass().getSimpleName()
        );
        
        return new ResponseEntity<Object>(error, NOT_FOUND);
    }
}
