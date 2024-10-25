package com.digital.silaai_integartion_service.chatbot;

import jakarta.annotation.Nonnull;
import lombok.Builder;

@Builder
public record ChatBotMessageResponse(
        @Nonnull String content
) {
}
