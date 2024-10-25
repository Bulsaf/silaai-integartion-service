package com.digital.silaai_integartion_service.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatBotMessageController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/text")
    public void processTextMessage(@Payload final NewUserMessageRequest userMessageRequest) {
        ChatBotMessageResponse chatBotMessageResponse = new ChatBotMessageResponse(
                "sdfds"
        );
        messagingTemplate.convertAndSendToUser(
                userMessageRequest.userId().toString(),
                "/queue/messages",
                chatBotMessageResponse
        );
    }

    @MessageMapping("/chat/audio")
    public void processAudioMessage(@Payload final NewUserMessageRequest userMessageRequest) {
        ChatBotMessageResponse chatBotMessageResponse = new ChatBotMessageResponse(
                "sdfds"
        );
        messagingTemplate.convertAndSendToUser(
                userMessageRequest.userId().toString(),
                "/queue/messages",
                chatBotMessageResponse
        );
    }
}
