package com.sd.SpringRESTApi.util;

import com.sd.SpringRESTApi.model.MyErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//ControllerAdvice is similar to an intercepter or filter.
//It can pre-process request and post-process response.
@ControllerAdvice
public class ControllerAdviceIntercepter {


    //Define global exception handler method for any Exception on any controller with @ExceptionHandler annotation
    //This handler method will return a ResponseEntity
    //ResponseEntity is the wrapper for the custom response object
    @ExceptionHandler
    public ResponseEntity<MyErrorResponse> handleException(Exception exp){
        MyErrorResponse response = new MyErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exp.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
