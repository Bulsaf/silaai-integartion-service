package com.digital.silaai_integartion_service.security.authentication.responses;

import jakarta.annotation.Nonnull;
import lombok.Builder;

@Builder
public record SignInWithTokensResponse(
        @Nonnull SignInResponse signInResponse,
        @Nonnull String accessToken,
        @Nonnull String refreshToken
) {
}
