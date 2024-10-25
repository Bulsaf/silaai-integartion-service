package com.digital.silaai_integartion_service.chatbot;

import com.digital.silaai_integartion_service.clients.llm.LlmService;
import com.digital.silaai_integartion_service.clients.llm.NewLlmResponse;
import com.digital.silaai_integartion_service.clients.speechkit.SpeechKitResponse;
import com.digital.silaai_integartion_service.clients.speechkit.SpeechKitService;
import com.digital.silaai_integartion_service.storage.entities.ChatMessageEntity;
import com.digital.silaai_integartion_service.storage.repositories.ChatMessageRepository;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final LlmService llmService;
    private final SpeechKitService speechKitService;

    private final ChatMessageRepository chatMessageRepository;
    private final TimeBasedEpochRandomGenerator timeBasedEpochRandomGenerator;

    @Nonnull
    @Override
    public ChatBotMessageResponse createTextAnswer(NewUserMessageRequest newUserMessageRequest) {
        NewLlmResponse newLlmResponse = llmService.getResponse(
                newUserMessageRequest.userId().toString(),
                newUserMessageRequest.content()
        );
        saveTwoMessages(newUserMessageRequest, newLlmResponse);
        return ChatBotMessageResponse.builder()
                                     .content(newLlmResponse.content())
                                     .build();
    }

    @Nonnull
    @Override
    public ChatBotMessageResponse createAudioAnswer(NewUserMessageRequest newUserMessageRequest) {
        String message = recognisedMessageFromRequest(newUserMessageRequest);
        NewLlmResponse newLlmResponse = llmService.getResponse(
                newUserMessageRequest.userId().toString(),
                message
        );
        saveTwoMessages(newUserMessageRequest, newLlmResponse);
        return ChatBotMessageResponse.builder()
                                     .content(newLlmResponse.content())
                                     .build();
    }

    private void saveTwoMessages(NewUserMessageRequest newUserMessageRequest, NewLlmResponse newLlmResponse) {
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

    private String recognisedMessageFromRequest(NewUserMessageRequest userMessageRequest) {
        log.info(userMessageRequest.content());
        byte[] audioMessageFromBase64 = Base64.getMimeDecoder()
                                              .decode(userMessageRequest.content());
        SpeechKitResponse speechKitResponse = speechKitService.getResponse(audioMessageFromBase64);

        return speechKitResponse.result().substring(0,speechKitResponse.result().length()/2+1);
    }
}

