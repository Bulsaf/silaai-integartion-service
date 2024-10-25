package com.digital.silaai_integartion_service.clients.llm;

import jakarta.annotation.Nonnull;

public record NewLlmResponse(
    @Nonnull String content
) {
}
