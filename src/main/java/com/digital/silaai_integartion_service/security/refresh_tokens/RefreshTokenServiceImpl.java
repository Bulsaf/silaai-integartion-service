package com.digital.silaai_integartion_service.security.refresh_tokens;

import com.digital.silaai_integartion_service.exceptions.NotFoundException;
import com.digital.silaai_integartion_service.exceptions.TokenRefreshException;
import com.digital.silaai_integartion_service.security.refresh_tokens.responses.GetRefreshTokenResponse;
import com.digital.silaai_integartion_service.storage.entities.RefreshTokenEntity;
import com.digital.silaai_integartion_service.storage.repositories.RefreshTokenRepository;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refresh-token-expiration-ms}")
    private Long refreshTokenDurationMs;
    private final RefreshTokenRepository refreshTokenRepository;

    private final TimeBasedEpochRandomGenerator timeBasedEpochUUIDRandomGenerator;

    @Override
    public GetRefreshTokenResponse getByRefreshToken(@Nonnull String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken)
                                                                      .orElseThrow(() -> new NotFoundException(refreshToken + " not found"));
        return GetRefreshTokenResponse.mapRecordToResponse(refreshTokenEntity);
    }

    @Override
    @Transactional
    public GetRefreshTokenResponse createRefreshToken(@Nonnull String username) {
        Optional<RefreshTokenEntity> refreshToken = refreshTokenRepository.findByUsername(username);
        if (refreshToken.isPresent()) {
            return GetRefreshTokenResponse.mapRecordToResponse(refreshToken.get());
        }

        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
                                                                  .id(timeBasedEpochUUIDRandomGenerator.generate())
                                                                  .username(username)
                                                                  .refreshToken(timeBasedEpochUUIDRandomGenerator.generate().toString())
                                                                  .expiresAt(Instant.now().plusMillis(refreshTokenDurationMs))
                                                                  .build();
        refreshTokenRepository.save(refreshTokenEntity);
        return GetRefreshTokenResponse.mapRecordToResponse(refreshTokenEntity);
    }

    @Override
    public GetRefreshTokenResponse verifyExpiration(String refreshToken) {
        GetRefreshTokenResponse foundRefreshToken = this.getByRefreshToken(refreshToken);
        if (foundRefreshToken.expiresAt().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.deleteById(foundRefreshToken.id());
            throw new TokenRefreshException(refreshToken, " Refresh token was expired. Please make a new signing request");
        }
        return foundRefreshToken;
    }

    @Override
    @Transactional
    public void deleteByUsername(@Nonnull String username) {
        refreshTokenRepository.deleteByUsername(username);
    }

}
