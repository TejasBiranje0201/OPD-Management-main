package com.opd_management.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.opd_management.responce.ErrorResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
    // ✅ 1. VALIDATION ERRORS (@Valid)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity< Map<String, String>> handleValidationexception(MethodArgumentNotValidException ex ){

		 Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult()
	          .getFieldErrors()
	          .forEach(error ->
	              errors.put(error.getField(), error.getDefaultMessage())
	          );

	        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
	}
	// ✅ 2. RESOURCE NOT FOUND
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponce> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		ErrorResponce errorResponce = new ErrorResponce(
				
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage());
		
		
		return new ResponseEntity<>(errorResponce, HttpStatus.NOT_FOUND);
		
	}
	
// ✅ 3. DATABASE ERRORS
	@ExceptionHandler(DatabaceException.class)
    public ResponseEntity<ErrorResponce> handleDatabaseException(
            DatabaceException ex) {

    	ErrorResponce error = new ErrorResponce(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	 
    // ✅ 4.  Duplicate ResourceEXCEPTION (ALWAYS KEEP AT BOTTOM)
    @ExceptionHandler(DuplicateValuesException.class)
    public ResponseEntity<ErrorResponce>handleDuplicate(DuplicateValuesException ex){
    	ErrorResponce error = new ErrorResponce(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
    	return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
	
	// ✅ 5. FALLBACK EXCEPTION (ALWAYS KEEP AT BOTTOM)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponce> handleGenericException(Exception ex) {

    	ErrorResponce error = new ErrorResponce(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong"
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	
	

}
