package com.digital.silaai_integartion_service.chat;

import lombok.Builder;

import java.util.UUID;

@Builder
public record GetChatMessageResponse(
        UUID senderId,
        String message
) {
}
