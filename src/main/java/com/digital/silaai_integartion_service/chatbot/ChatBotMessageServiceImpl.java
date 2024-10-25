package com.digital.silaai_integartion_service.chatbot;

import com.digital.silaai_integartion_service.chat.ChatMessageService;
import com.digital.silaai_integartion_service.chatbot.requests.NewDefaultUserMessageRequest;
import com.digital.silaai_integartion_service.chatbot.responses.ChatBotMessageResponse;
import com.digital.silaai_integartion_service.clients.llm.LlmService;
import com.digital.silaai_integartion_service.clients.llm.NewLlmResponse;
import com.digital.silaai_integartion_service.clients.speechkit.SpeechKitResponse;
import com.digital.silaai_integartion_service.clients.speechkit.SpeechKitService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatBotMessageServiceImpl implements ChatBotMessageService {

    private final LlmService llmService;
    private final SpeechKitService speechKitService;
    private final ChatMessageService chatMessageService;


    @Nonnull
    @Override
    public ChatBotMessageResponse createTextAnswer(
            @Nonnull NewDefaultUserMessageRequest newDefaultUserMessageRequest,
            @Nonnull UUID userId
    ) {
        NewLlmResponse newLlmResponse = llmService.getResponse(
                userId.toString(),
                newDefaultUserMessageRequest.content()
        );
        chatMessageService.saveQuestionAndAnswer(userId, newDefaultUserMessageRequest, newLlmResponse);
        return ChatBotMessageResponse.builder()
                                     .content(newLlmResponse.content())
                                     .build();
    }

    @Nonnull
    @Override
    public ChatBotMessageResponse createAudioAnswer(
            @Nonnull NewDefaultUserMessageRequest newDefaultUserMessageRequest,
            @Nonnull UUID userId
    ) {
        String message = recognisedMessageFromRequest(newDefaultUserMessageRequest);
        NewLlmResponse newLlmResponse = llmService.getResponse(
                userId.toString(),
                message
        );
        chatMessageService.saveQuestionAndAnswer(userId, newDefaultUserMessageRequest, newLlmResponse);
        return ChatBotMessageResponse.builder()
                                     .content(newLlmResponse.content())
                                     .build();
    }

    private String recognisedMessageFromRequest(NewDefaultUserMessageRequest userMessageRequest) {
        log.info(userMessageRequest.content());
        byte[] audioMessageFromBase64 = Base64.getMimeDecoder()
                                              .decode(userMessageRequest.content());
        SpeechKitResponse speechKitResponse = speechKitService.getResponse(audioMessageFromBase64);

        return speechKitResponse.result().substring(0,speechKitResponse.result().length()/2+1);
    }
}

