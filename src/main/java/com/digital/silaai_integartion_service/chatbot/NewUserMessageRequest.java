package com.digital.silaai_integartion_service.chatbot;

import jakarta.annotation.Nonnull;

import java.util.UUID;

public record NewUserMessageRequest(
    @Nonnull UUID userId,
    @Nonnull String content
) {
}
