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
@Table(name = "chat_branches")
public class ChatBranchEntity {
    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Builder.Default
    @Column(name = "created_at")
    private Instant createdAt = Instant.now(Clock.system(ZoneId.of("Europe/Moscow")));
}
