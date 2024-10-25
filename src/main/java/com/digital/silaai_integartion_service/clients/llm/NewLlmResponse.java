package com.digital.silaai_integartion_service.clients.llm;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.util.Map;

@Builder
public record NewLlmResponse(
    @Nonnull Map<String, Object> content
) {
}
