package com.tr.getir.ReadingIsGood.Config;

import com.tr.getir.ReadingIsGood.Model.CustomExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
class ValidateParametersController {

    // request mapping method omitted

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<List<CustomExceptionModel>> handleConstraintViolationException(MethodArgumentNotValidException e) {
        List<CustomExceptionModel> response = new ArrayList<>();


        for (FieldError fError : e.getBindingResult().getFieldErrors()){
response.add(new CustomExceptionModel(fError.getField(),fError.getDefaultMessage()));
        }
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Map<String,String>> runtimeException(RuntimeException e) {
       Map<String,String> response = new HashMap<>();

       response.put("errorMessage",e.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}