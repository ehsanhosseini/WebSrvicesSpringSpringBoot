package com.webservice.springboot.webservice.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.webservice.springboot.webservice.user.UserNotFoundException;

// extended ResponseEntityExceptionHandler-> it provide centrlize exception hadeling cross of all different exception handler methods. We can use it to provide some default exception
@ControllerAdvice // applicable across all other controller. when we have multiple controller
@RestController // I need to apply it to all other controller we have defind like
				// UserResource.class.
// ** We may not need to create this Exceptionhandeler if default structure
// which provided by Spring MVC is good enough
public class CustomizedResponseEntityExceptionHandelling extends ResponseEntityExceptionHandler {

	// ** This Exception is common Exception
	@ExceptionHandler(Exception.class) // We need to override this because we got this method from
										// ResponseEntityExceptionHandler. For now we want to hadle all exception so
										// Exception.class
	public final ResponseEntity<Object> handleAllException // copy it from ResponseEntityExceptionHandler
	(Exception ex, WebRequest request) {

		// we need to create a new instance of our specific bean. We want
		// ExceptonResponse back
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// ** This Exception is Customize Exception for UserNotFoundException class
	@ExceptionHandler(UserNotFoundException.class) // I can customize it for UserNotFoundException class

	public final ResponseEntity<Object> handleUserNotFoundExceptionException(UserNotFoundException ex,
			WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

	}
	



	// We copy this from ResponseEntityExceptionHandler and we need to override it.
	// We use this method when finding for specific argument failed
	// for example when we just use one charecter for creating a user. Here we creat
	// our own exception with proper message to user
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Faild",
				ex.getBindingResult().toString()); // getBindingResult check what went wrong.

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
