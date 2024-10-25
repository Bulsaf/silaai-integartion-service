package com.digital.silaai_integartion_service.chatbot;

import jakarta.annotation.Nonnull;

public interface ChatMessageService {

    @Nonnull
    ChatBotMessageResponse createTextAnswer(NewUserMessageRequest newUserMessageRequest);

    @Nonnull
    ChatBotMessageResponse createAudioAnswer(NewUserMessageRequest newUserMessageRequest);

}
