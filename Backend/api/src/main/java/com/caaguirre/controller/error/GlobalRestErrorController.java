package com.caaguirre.controller.error;

import com.caaguirre.exception.web.ApiError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = {"com.caaguirre.controller.rest"})
public class GlobalRestErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> getEmptyResultDataAccessException(ConstraintViolationException e, ServletWebRequest webRequest){
        ApiError apiError = new ApiError();
        apiError.setCode(e.getErrorCode());
        apiError.setMessage(e.getMessage());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMethod(webRequest.getHttpMethod().name());
        //apiError.setSubErrors();
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}
