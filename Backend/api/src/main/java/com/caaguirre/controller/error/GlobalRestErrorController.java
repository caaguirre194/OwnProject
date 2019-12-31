package com.caaguirre.controller.error;

import com.caaguirre.exception.web.ApiError;
import com.caaguirre.exception.web.ApiSubError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

//@EnableAutoConfiguration(exclude = { WebMvcAutoConfiguration.class })
@RestControllerAdvice(basePackages = {"com.caaguirre.controller.rest"})
public class GlobalRestErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleFormException(ConstraintViolationException e, ServletWebRequest webRequest){
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.BAD_REQUEST.value());
        apiError.setMessage(e.getMessage());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMethod(webRequest.getHttpMethod().name());
        apiError.setPath(((ServletWebRequest) webRequest).getRequest().getRequestURI().toString());
      /*  List<String> details = new ArrayList<>();
        for(ObjectError error : e.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
       /* for(ObjectError error : e.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);*/
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    /*@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<Object> getHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value());
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        apiError.setMethod(((ServletWebRequest) request).getHttpMethod().name());
        apiError.setPath((((ServletWebRequest) request).getRequest().getRequestURI().toString()));
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }**/

    /*@ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }*/

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Server Error", ex);
        apiError.setPath(((ServletWebRequest) webRequest).getRequest().getRequestURI().toString());
        apiError.setMethod(((ServletWebRequest) webRequest).getHttpMethod().name());
        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    //@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value());
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        apiError.setMethod(((ServletWebRequest) request).getHttpMethod().name());
        apiError.setPath((((ServletWebRequest) request).getRequest().getRequestURI().toString()));
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ApiError apiError = new ApiError();
        apiError.setMessage("Validation Failed");
        apiError.setSubErrors(details);
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

}
