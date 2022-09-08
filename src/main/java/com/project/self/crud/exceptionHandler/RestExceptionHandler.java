package com.project.self.crud.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.self.crud.apierror.ErrorResponse;
import com.project.self.crud.apierror.SubError;
import com.project.self.crud.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${api.version}")
	private String currentApiVersion;
	
	@Override
    protected ResponseEntity<Object> handleMissingPathVariable(
    		MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		
		ErrorResponse error = new ErrorResponse(
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
		log.error(ex.getMessage());
		
		ErrorResponse error = new ErrorResponse(
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
		log.error(ex.getMessage());
		
		ErrorResponse error = new ErrorResponse(
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
		log.error(ex.getMessage());
		
		ErrorResponse error = new ErrorResponse(
                currentApiVersion,
                METHOD_NOT_ALLOWED,
                "The HTTP method in the request is not allowed on the resource",
                ex.getClass().getSimpleName()
        );
        
        return new ResponseEntity<Object>(error, METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		
		ErrorResponse error = new ErrorResponse(
                currentApiVersion,
                BAD_REQUEST,
                "Sorry, can't proceed processing the information provided. Please check your inputs again",
                ex.getClass().getSimpleName(),
                mapToSubError(ex)
        );
		
		return new ResponseEntity<Object>(error, BAD_REQUEST);
	}
	
	private List<SubError> mapToSubError(MethodArgumentNotValidException ex) {
		List<SubError> validationError = new ArrayList<SubError>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			validationError.add(new SubError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
	    }
		
		return validationError;
    }

	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleNonExistingUser(UserNotFoundException ex, WebRequest request) {
		log.error(ex.getMessage());
		
		ErrorResponse error = new ErrorResponse(
                currentApiVersion,
                NOT_FOUND,
                ex.getMessage(),
                ex.getClass().getSimpleName()
        );
        
        return new ResponseEntity<Object>(error, NOT_FOUND);
    }
}
