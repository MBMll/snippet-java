package com.github.mbmll.snippt.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 *
 */
@RestControllerAdvice
public class ResBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ResponseEntity || o instanceof Res) {
            return o;
        }
        if (o instanceof String) {
            try {
                return new ObjectMapper().writeValueAsString(Res.builder().data(o).build());
            } catch (JsonProcessingException e) {
                return ResponseEntity.badRequest().body(o).toString();
            }
        }
        return Res.builder().data(o).build();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> tryException(Exception e) {
        return ResponseEntity.badRequest().build();
    }
}
