package com.jobmarket.common.exception;

import com.jobmarket.common.api.ApiError;
import com.jobmarket.common.api.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiError error = new ApiError("RESOURCE_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleConflict(ConflictException ex) {
        ApiError error = new ApiError("CONFLICT", ex.getMessage());
        return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessRule(BusinessRuleException ex) {
        ApiError error = new ApiError("BUSINESS_RULE_VIOLATION", ex.getMessage());
        return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ApiError.ErrorDetail> details = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ApiError.ErrorDetail(
                        fieldError.getField(),
                        "INVALID_FIELD",
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiError error = new ApiError("VALIDATION_ERROR", "Existem campos inválidos", details);
        return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception ex) {
        ApiError error = new ApiError("INTERNAL_SERVER_ERROR", "Ocorreu um erro interno no servidor");
        return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
