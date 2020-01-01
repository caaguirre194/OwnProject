package com.caaguirre.model.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {
    HttpStatus status;

    public ApiException(String exception) {
        super(exception);
    }

    public ApiException(HttpStatus status, String exception) {
        super(exception);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
