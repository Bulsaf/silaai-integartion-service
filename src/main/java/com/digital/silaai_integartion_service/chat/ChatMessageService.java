package com.digital.silaai_integartion_service.chat;

import com.digital.silaai_integartion_service.chatbot.requests.NewDefaultUserMessageRequest;
import com.digital.silaai_integartion_service.clients.llm.NewLlmResponse;
import jakarta.annotation.Nonnull;

import java.util.UUID;

public interface ChatMessageService {

    void saveQuestionAndAnswer(@Nonnull UUID userId, @Nonnull NewDefaultUserMessageRequest userMessageRequest,@Nonnull NewLlmResponse newLlmResponse);

}
