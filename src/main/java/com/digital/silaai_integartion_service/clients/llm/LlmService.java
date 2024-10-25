package com.digital.silaai_integartion_service.clients.llm;

import jakarta.annotation.Nonnull;

public interface LlmService {

    @Nonnull
    NewLlmResponse getResponse(@Nonnull String userId, @Nonnull String request);

}
