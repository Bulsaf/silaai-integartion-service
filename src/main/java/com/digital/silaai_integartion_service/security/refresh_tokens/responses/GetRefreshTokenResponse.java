package com.digital.silaai_integartion_service.security.refresh_tokens.responses;

import com.digital.silaai_integartion_service.storage.entities.RefreshTokenEntity;
import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record GetRefreshTokenResponse(
        UUID id,
        String username,
        String refreshToken,
        Instant expiresAt
) {
    public static GetRefreshTokenResponse mapRecordToResponse(@Nonnull RefreshTokenEntity refreshTokenEntity) {
        return GetRefreshTokenResponse.builder()
                .id(refreshTokenEntity.getId())
                .username(refreshTokenEntity.getUsername())
                .refreshToken(refreshTokenEntity.getRefreshToken())
                .expiresAt(refreshTokenEntity.getExpiresAt())
                .build();
    }
}
