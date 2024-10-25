package com.digital.silaai_integartion_service.exceptions.advices;

import com.digital.silaai_integartion_service.exceptions.TokenRefreshException;
import com.digital.silaai_integartion_service.exceptions.error_messages.DefaultErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<DefaultErrorMessage> handleException(TokenRefreshException e) {
        DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
