package com.digital.silaai_integartion_service.chat;

import com.digital.silaai_integartion_service.chatbot.NewUserMessageRequest;
import com.digital.silaai_integartion_service.clients.llm.NewLlmResponse;
import jakarta.annotation.Nonnull;

public interface ChatMessageService {

    void saveQuestionAndAnswer(@Nonnull NewUserMessageRequest userMessageRequest,@Nonnull NewLlmResponse newLlmResponse);

}
