package com.digital.silaai_integartion_service.security.authentication.responses;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.util.List;

@Builder
public record SignInResponse(
        @Nonnull String id,
        @Nonnull String username,
        @Nonnull List<String> roles
) {
}
