package com.digital.silaai_integartion_service.storage.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "chat_messages")
public class ChatMessageEntity {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "sender_id", nullable = false)
    private UUID senderId;

    @Column(name = "recepient_id", nullable = false)
    private UUID recipientId;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> content;

    @Builder.Default
    @Column(name = "created_at")
    private Instant createdAt = Instant.now(Clock.system(ZoneId.of("Europe/Moscow")));

}
