package com.digital.silaai_integartion_service.clients.llm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record LlmMessageRequest (
        @JsonProperty("user_id")
        String userId,
        @JsonProperty("user_input")
        String userInput
) {
}
