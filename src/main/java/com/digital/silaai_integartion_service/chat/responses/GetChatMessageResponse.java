package com.digital.silaai_integartion_service.chat.responses;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.util.Map;
import java.util.UUID;

@Builder
public record GetChatMessageResponse(
        @Nonnull UUID senderId,
        @Nonnull Map<String, Object> message
) {
}
