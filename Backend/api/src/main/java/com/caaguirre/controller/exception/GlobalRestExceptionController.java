package com.caaguirre.controller.exception;

import com.caaguirre.controller.rest.UserRestController;
import com.caaguirre.model.exception.ApiException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

//@EnableAutoConfiguration(exclude = { WebMvcAutoConfiguration.class })
//@RestControllerAdvice(basePackages = {"com.caaguirre.controller.rest"})
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice()
public class GlobalRestExceptionController extends ResponseEntityExceptionHandler {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, ServletWebRequest webRequest){
        ApiMessage apiMessage = new ApiMessage();
        apiMessage.setCode(HttpStatus.BAD_REQUEST.value());
        apiMessage.setMessage(e.getMessage());
        apiMessage.setDebugMessage(e.getLocalizedMessage());
        apiMessage.setStatus(HttpStatus.BAD_REQUEST);
        apiMessage.setMethod(webRequest.getHttpMethod().name());
        apiMessage.setPath(((ServletWebRequest) webRequest).getRequest().getRequestURI().toString());
        apiMessage.setMessage("Validation Failed");
        //apiError.setSubErrors(details);
        return ResponseEntity.status(apiMessage.getStatus()).body(apiMessage);
    }

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(code = HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
    //@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiMessage apiMessage = new ApiMessage();
        apiMessage.setCode(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value());
        apiMessage.setMessage(ex.getMessage());
        apiMessage.setDebugMessage(ex.getLocalizedMessage());
        apiMessage.setStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        apiMessage.setMethod(((ServletWebRequest) request).getHttpMethod().name());
        apiMessage.setPath((((ServletWebRequest) request).getRequest().getRequestURI().toString()));
        return ResponseEntity.status(apiMessage.getStatus()).body(apiMessage);
    }

    @ExceptionHandler({ApiException.class})
    protected ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request) {
        ApiMessage apiMessage = new ApiMessage();
        apiMessage.setCode(ex.getStatus().value());
        apiMessage.setMessage(ex.getMessage());
        apiMessage.setDebugMessage(ex.getLocalizedMessage());
        apiMessage.setStatus(ex.getStatus());
        apiMessage.setMethod(((ServletWebRequest) request).getHttpMethod().name());
        apiMessage.setPath((((ServletWebRequest) request).getRequest().getRequestURI().toString()));
        return ResponseEntity.status(apiMessage.getStatus()).body(apiMessage);
    }

   /* @Order(Ordered.LOWEST_PRECEDENCE)
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Server Error", ex);
        apiError.setPath(((ServletWebRequest) webRequest).getRequest().getRequestURI().toString());
        apiError.setMethod(((ServletWebRequest) webRequest).getHttpMethod().name());
        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ApiMessage apiMessage = new ApiMessage();
        apiMessage.setMessage("Validation Failed");
        apiMessage.setDebugMessage(ex.getLocalizedMessage());
        apiMessage.setSubErrors(details);
        return new ResponseEntity(apiMessage, HttpStatus.BAD_REQUEST);
    }

}
