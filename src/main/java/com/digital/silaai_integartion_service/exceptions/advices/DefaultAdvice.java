package com.digital.silaai_integartion_service.exceptions.advices;

import com.digital.silaai_integartion_service.exceptions.BadRequestException;
import com.digital.silaai_integartion_service.exceptions.NotFoundException;
import com.digital.silaai_integartion_service.exceptions.error_messages.DefaultErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<DefaultErrorMessage> handleException(BadRequestException e) {
        DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<DefaultErrorMessage> handleException(NotFoundException e) {
        DefaultErrorMessage response = new DefaultErrorMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
