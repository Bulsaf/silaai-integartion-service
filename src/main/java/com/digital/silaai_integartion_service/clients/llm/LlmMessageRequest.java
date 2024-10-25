package com.digital.silaai_integartion_service.clients.llm;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import lombok.Builder;

@Builder
public record LlmMessageRequest (
        @JsonProperty("user_id")
        @Nonnull String userId,
        @JsonProperty("user_input")
        @Nonnull String userInput
) {
}
