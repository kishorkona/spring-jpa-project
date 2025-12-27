package com.work.controllers;

import com.work.constants.MyConstants;
import com.work.data.ApiResponse;
import com.work.exception.DBException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class MyExceptionController extends  ResponseEntityExceptionHandler {

    /*
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        System.out.println("####### handleExceptionInternal ######"+ex);
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setCode(MyConstants.FAILED_ERROR);
        errorResponse.setStatus(MyConstants.ErrorMessage);
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessage = "Invalid input type for parameter: " + ex.getPropertyName() + ". Expected type: " + ex.getRequiredType().getSimpleName();
        System.out.println("####### handleTypeMismatch ######"+errorMessage);
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setCode(MyConstants.FAILED_ERROR);
        errorResponse.setStatus(MyConstants.ErrorMessage);
        errorResponse.setMessage(errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    */

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public DBException handleNoSuchElementException(NoSuchElementException ex) {
        return new DBException("5000", "Database Error", ex.getMessage(), ex);
    }
}
