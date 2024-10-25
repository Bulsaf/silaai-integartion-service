package com.digital.silaai_integartion_service.chat;

import com.digital.silaai_integartion_service.chat.responses.GetChatMessageResponse;
import com.digital.silaai_integartion_service.storage.entities.ChatMessageEntity;
import com.digital.silaai_integartion_service.storage.repositories.ChatMessageRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatHistoryServiceImpl implements ChatHistoryService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public List<GetChatMessageResponse> getChatHistoryByBranchId(@Nonnull UUID branchId) {
        List<ChatMessageEntity> chatMessages = chatMessageRepository
                .findAllByBranchIdOrderByCreatedAt(branchId);
        return chatMessages.stream()
                .map(it -> GetChatMessageResponse.builder()
                                                  .senderId(it.getSenderId())
                                                  .message(it.getContent())
                                                  .build()
                )
                .toList();
    }

}
