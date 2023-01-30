package com.github.mbmll.snippet.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 *
 */
@RestControllerAdvice(basePackages = "com.github.mbmll.snippet")
public class ResBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        if (o instanceof String) {
//            try {
//                return new ObjectMapper().writeValueAsString(Res.builder().data(o).build());
//            } catch (JsonProcessingException e) {
//                return ResponseEntity.badRequest().body(o).toString();
//            }
//        }

        return Res.builder().data(o).build();
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> bodyType = returnType.getMethod().getReturnType();
        if (bodyType.isAssignableFrom(ResponseEntity.class) ||
                bodyType.isAssignableFrom(Res.class) ||
                converterType.isInstance(new StringHttpMessageConverter())) {
            return false;
        }
        return true;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> tryException(Exception e) {
        return ResponseEntity.badRequest().build();
    }
}
