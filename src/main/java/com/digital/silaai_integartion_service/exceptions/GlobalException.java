package com.digital.silaai_integartion_service.exceptions;

import java.io.Serializable;

public abstract class GlobalException extends RuntimeException implements Serializable {

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
