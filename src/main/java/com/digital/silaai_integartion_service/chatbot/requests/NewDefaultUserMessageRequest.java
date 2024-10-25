package com.digital.silaai_integartion_service.chatbot.requests;

import jakarta.annotation.Nonnull;

import java.util.UUID;

public record NewDefaultUserMessageRequest(
        @Nonnull UUID branchId,
        @Nonnull String content
) {
}
