package com.digital.silaai_integartion_service.chat;

import com.digital.silaai_integartion_service.chatbot.NewUserMessageRequest;
import com.digital.silaai_integartion_service.clients.llm.NewLlmResponse;
import com.digital.silaai_integartion_service.storage.entities.ChatMessageEntity;
import com.digital.silaai_integartion_service.storage.repositories.ChatMessageRepository;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final TimeBasedEpochRandomGenerator timeBasedEpochRandomGenerator;

    @Override
    public void saveQuestionAndAnswer(@Nonnull NewUserMessageRequest newUserMessageRequest, @Nonnull NewLlmResponse newLlmResponse) {
        ChatMessageEntity newUserMessageEntity = ChatMessageEntity.builder()
                                                                  .id(timeBasedEpochRandomGenerator.generate())
                                                                  .senderId(newUserMessageRequest.userId())
                                                                  .recipientId(UUID.fromString("silaAi"))
                                                                  .content(Map.of("content", newUserMessageRequest.content()))
                                                                  .build();
        ChatMessageEntity newChatBotMessageEntity = ChatMessageEntity.builder()
                                                                     .id(timeBasedEpochRandomGenerator.generate())
                                                                     .senderId(UUID.fromString("silaAi"))
                                                                     .recipientId(newUserMessageRequest.userId())
                                                                     .content(Map.of("content", newLlmResponse.content()))
                                                                     .build();
        chatMessageRepository.saveAll(List.of(newUserMessageEntity, newChatBotMessageEntity));
    }
}
