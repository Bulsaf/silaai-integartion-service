package com.digital.silaai_integartion_service.storage.repositories;

import com.digital.silaai_integartion_service.storage.entities.RefreshTokenEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    Optional<RefreshTokenEntity> findByRefreshToken(@Nonnull String refreshToken);

    Optional<RefreshTokenEntity> findByUsername(@Nonnull String username);

    void deleteByUsername(@Nonnull String username);

}
