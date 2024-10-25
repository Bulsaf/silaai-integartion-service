package com.digital.silaai_integartion_service.exceptions.error_messages;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DefaultErrorMessage {

    private String message;

    public DefaultErrorMessage(String message) {
        this.message = message;
    }
}
