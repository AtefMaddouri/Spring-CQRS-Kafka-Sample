package tn.esprit.msproductquery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {


    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleIException(IllegalArgumentException exception){
        Map<String, String> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return map;
    }

}
