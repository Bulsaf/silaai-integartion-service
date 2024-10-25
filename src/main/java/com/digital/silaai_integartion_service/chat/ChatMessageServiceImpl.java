package com.digital.silaai_integartion_service.chat;

import com.digital.silaai_integartion_service.chatbot.requests.NewDefaultUserMessageRequest;
import com.digital.silaai_integartion_service.clients.llm.NewLlmResponse;
import com.digital.silaai_integartion_service.storage.entities.ChatMessageEntity;
import com.digital.silaai_integartion_service.storage.repositories.ChatMessageRepository;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final TimeBasedEpochRandomGenerator timeBasedEpochRandomGenerator;

    @Override
    @Transactional
    public void saveQuestionAndAnswer(@Nonnull UUID userId, @Nonnull NewDefaultUserMessageRequest newDefaultUserMessageRequest, @Nonnull NewLlmResponse newLlmResponse) {
        ChatMessageEntity newUserMessageEntity = ChatMessageEntity.builder()
                                                                  .id(timeBasedEpochRandomGenerator.generate())
                                                                  .senderId(userId)
                                                                  .recipientId(UUID.fromString("silaAi"))
                                                                  .branchId(newDefaultUserMessageRequest.branchId())
                                                                  .content(Map.of("content", newDefaultUserMessageRequest.content()))
                                                                  .build();
        ChatMessageEntity newChatBotMessageEntity = ChatMessageEntity.builder()
                                                                     .id(timeBasedEpochRandomGenerator.generate())
                                                                     .senderId(UUID.fromString("silaAi"))
                                                                     .recipientId(userId)
                                                                     .branchId(newDefaultUserMessageRequest.branchId())
                                                                     .content(Map.of("content", newLlmResponse.content()))
                                                                     .build();
        chatMessageRepository.saveAll(List.of(newUserMessageEntity, newChatBotMessageEntity));
    }
}
