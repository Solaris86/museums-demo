package com.museums.world.exception;

import com.museums.world.model.ErrorResponse;
import com.museums.world.model.InvalidField;
import com.museums.world.model.ValidationErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
public class MuseumsDemoExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String EXCEPTION_CAUGHT_MESSAGE = "{} caught, message: [{}]";

    private final Logger logger = LoggerFactory.getLogger(MuseumsDemoExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logExceptionCaughtErrorMessage(ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .id(UUID.randomUUID())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logExceptionCaughtErrorMessage(ex);

        List<InvalidField> invalidFields = ex.getFieldErrors().stream()
                .map(fieldError -> InvalidField.builder()
                        .name(fieldError.getField())
                        .reason(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .id(UUID.randomUUID())
                .message("Validation error of request body occurred.")
                .invalidFields(invalidFields)
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        logExceptionCaughtErrorMessage(ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .id(UUID.randomUUID())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private void logExceptionCaughtErrorMessage(Exception ex) {
        logger.error(EXCEPTION_CAUGHT_MESSAGE, ex.getClass().getSimpleName(), ex.getMessage(), ex);
    }
}
