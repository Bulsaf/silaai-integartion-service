package com.digital.silaai_integartion_service.chatbot;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.util.Map;

@Builder
public record ChatBotMessageResponse(
        @Nonnull Map<String, Object> content
) {
}
