package com.DeathTime.API.Spring.DeathTime.API.Spring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalFilterException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalFilterException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest req) {
        logger.error("Error Occurred: {}", ex.getMessage(), ex);

        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Error Server");
        body.put("message", ex.getMessage());
        body.put("push", req.getDescription(false));

        return  new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
