package com.digital.silaai_integartion_service.security.refresh_tokens;

import com.digital.silaai_integartion_service.security.refresh_tokens.responses.GetRefreshTokenResponse;
import jakarta.annotation.Nonnull;

public interface RefreshTokenService {

    GetRefreshTokenResponse getByRefreshToken(@Nonnull String refreshToken);

    GetRefreshTokenResponse createRefreshToken(@Nonnull String username);

    GetRefreshTokenResponse verifyExpiration(String refreshToken);

    void deleteByUsername(@Nonnull String username);
}
