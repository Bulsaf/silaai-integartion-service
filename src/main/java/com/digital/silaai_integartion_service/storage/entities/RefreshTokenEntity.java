package com.digital.silaai_integartion_service.storage.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private Instant expiresAt;

    @Builder.Default
    @Column(name = "created_at")
    private Instant createdAt = Instant.now(Clock.system(ZoneId.of("Europe/Moscow")));

}
