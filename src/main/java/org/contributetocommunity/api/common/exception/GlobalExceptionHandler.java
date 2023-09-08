package org.contributetocommunity.api.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleGlobalException(Exception e) {
        log.error("There was an error during the request: {}", e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
