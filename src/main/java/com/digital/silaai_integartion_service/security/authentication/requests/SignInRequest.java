package com.digital.silaai_integartion_service.security.authentication.requests;

import jakarta.annotation.Nonnull;
import lombok.Builder;

@Builder
public record SignInRequest(
        @Nonnull String username,
        @Nonnull String password
) {
}
