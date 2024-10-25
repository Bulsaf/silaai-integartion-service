package com.digital.silaai_integartion_service.security.authentication.requests;

import jakarta.annotation.Nonnull;

public record SignUpRequest(
    @Nonnull String username,
    @Nonnull String password
) {
}
