package dev.himel.log_anomaly_summarizer.exception;

import com.google.genai.errors.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    public static final String MESSAGE_KEY = "message";

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentException(MethodArgumentNotValidException me) {

        Map<String,Object> response = new HashMap<>();
        for (FieldError fe : me.getFieldErrors()) {
            response.put(fe.getField(), fe.getDefaultMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException me) {

        Map<String,Object> response = new HashMap<>();
        response.put(MESSAGE_KEY, me.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MissingServletRequestParameterException me) {

        Map<String,Object> response = new HashMap<>();
        response.put(MESSAGE_KEY, me.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler({ServerException.class})
    public ResponseEntity<Object> handleValidationExceptions(ServerException ve) {
        Map<String,Object> response = new HashMap<>();
        response.put(MESSAGE_KEY, ve.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleValidationExceptions(HttpMessageNotReadableException ve) {
        Map<String,Object> response = new HashMap<>();
        response.put(MESSAGE_KEY, ve.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleValidationExceptions(RuntimeException ve) {
        Map<String,Object> response = new HashMap<>();
        response.put(MESSAGE_KEY, ve.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}