package com.digital.silaai_integartion_service.chatbot;

import com.digital.silaai_integartion_service.chat.ChatMessageService;
import com.digital.silaai_integartion_service.clients.llm.LlmService;
import com.digital.silaai_integartion_service.clients.llm.NewLlmResponse;
import com.digital.silaai_integartion_service.clients.speechkit.SpeechKitResponse;
import com.digital.silaai_integartion_service.clients.speechkit.SpeechKitService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatBotMessageServiceImpl implements ChatBotMessageService {

    private final LlmService llmService;
    private final SpeechKitService speechKitService;
    private final ChatMessageService chatMessageService;


    @Nonnull
    @Override
    public ChatBotMessageResponse createTextAnswer(NewUserMessageRequest newUserMessageRequest) {
        NewLlmResponse newLlmResponse = llmService.getResponse(
                newUserMessageRequest.userId().toString(),
                newUserMessageRequest.content()
        );
        chatMessageService.saveQuestionAndAnswer(newUserMessageRequest, newLlmResponse);
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
        chatMessageService.saveQuestionAndAnswer(newUserMessageRequest, newLlmResponse);
        return ChatBotMessageResponse.builder()
                                     .content(newLlmResponse.content())
                                     .build();
    }

    private String recognisedMessageFromRequest(NewUserMessageRequest userMessageRequest) {
        log.info(userMessageRequest.content());
        byte[] audioMessageFromBase64 = Base64.getMimeDecoder()
                                              .decode(userMessageRequest.content());
        SpeechKitResponse speechKitResponse = speechKitService.getResponse(audioMessageFromBase64);

        return speechKitResponse.result().substring(0,speechKitResponse.result().length()/2+1);
    }
}

