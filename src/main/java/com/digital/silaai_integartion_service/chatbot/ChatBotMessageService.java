package com.digital.silaai_integartion_service.chatbot;

import com.digital.silaai_integartion_service.chatbot.requests.NewDefaultUserMessageRequest;
import com.digital.silaai_integartion_service.chatbot.responses.ChatBotMessageResponse;
import jakarta.annotation.Nonnull;

import java.util.UUID;

public interface ChatBotMessageService {

    @Nonnull
    ChatBotMessageResponse createTextAnswer(
            @Nonnull NewDefaultUserMessageRequest newDefaultUserMessageRequest,
            @Nonnull UUID userId
    );

    @Nonnull
    ChatBotMessageResponse createAudioAnswer(
            @Nonnull NewDefaultUserMessageRequest newDefaultUserMessageRequest,
            @Nonnull UUID userId
    );

}
