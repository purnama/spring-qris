package id.purnama.qris.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class TestRestControllerAdvice {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ObjectError>> handleConstraintViolation(
            BindException ex, WebRequest request) {
        List<ObjectError> errors = new ArrayList<>(ex.getBindingResult().getAllErrors());
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
