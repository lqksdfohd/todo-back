package org.ahmed.todolist.advices;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", status.value());
        List<String> erreurs = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> String.format("%s: %s", x.getField(), x.getDefaultMessage()) )
                .collect(Collectors.toList());
        map.put("erreurs", erreurs);

        return new ResponseEntity<>(map, status);
    }
}
