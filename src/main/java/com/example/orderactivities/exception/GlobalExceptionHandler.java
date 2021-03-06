package com.example.orderactivities.exception;

import com.example.orderactivities.dto.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
            NoResultStatusException.class, ConstraintViolationException.class})
    public final ResponseEntity<ApiError> invalidCoordinates(Exception ex, WebRequest request) {
        HttpHeaders header = new HttpHeaders();
        if (ex instanceof HttpMessageNotReadableException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            HttpMessageNotReadableException hnre = (HttpMessageNotReadableException) ex;
            String errorMessage = "Coordinates can only be STRING";
            return handleException(hnre, null, header, status, request);
        } else if (ex instanceof MethodArgumentNotValidException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            MethodArgumentNotValidException manvEx = (MethodArgumentNotValidException) ex;
            String errorMessage = "Coordinates cannot be null or empty";
            return handleArgumentAllowedException(manvEx, header, status, request);
        } else if (ex instanceof  ConstraintViolationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ConstraintViolationException constraintEx = (ConstraintViolationException) ex;
            return handleConstraintViolation(constraintEx, header, status, request);
        } else if (ex instanceof NoResultStatusException) {
            HttpStatus status = HttpStatus.NO_CONTENT;
            NoResultStatusException nsEx = (NoResultStatusException) ex;
            return handleException(nsEx, new ApiError(nsEx.getMessage()), header, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleException(ex, null, header, status, request);
        }

    }

    protected ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex.getConstraintViolations()
                .stream()
                .map(contentError ->  contentError.getMessage())
                .collect(Collectors.toList());

        return handleException(ex, new ApiError(errorMessages.get(0)), headers, status, request);
    }
    protected ResponseEntity<ApiError> handleArgumentAllowedException(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(contentError ->  contentError.getDefaultMessage())
                .collect(Collectors.toList());

        return handleException(ex, new ApiError(errorMessages.get(0)), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleException(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex,WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
