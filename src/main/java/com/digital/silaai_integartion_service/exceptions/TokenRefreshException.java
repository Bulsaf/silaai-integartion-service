package com.digital.silaai_integartion_service.exceptions;

public class TokenRefreshException extends GlobalException{

    public TokenRefreshException(String message) {
        super(message);
    }

    public TokenRefreshException(String token, String message) {
        super("Failed for [" + token + "]: "+ message);
    }
}
