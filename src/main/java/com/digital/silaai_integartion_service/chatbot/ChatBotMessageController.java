package com.digital.silaai_integartion_service.chatbot;

import com.digital.silaai_integartion_service.chatbot.requests.NewDefaultUserMessageRequest;
import com.digital.silaai_integartion_service.chatbot.responses.ChatBotMessageResponse;
import com.digital.silaai_integartion_service.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatBotMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatBotMessageService chatMessageService;

    @MessageMapping("/chat.text")
    public void processTextMessage(
            @Payload final NewDefaultUserMessageRequest userMessageRequest,
            @AuthenticationPrincipal final UserDetailsImpl userDetails
    ) {
        ChatBotMessageResponse chatBotMessageResponse = chatMessageService.createTextAnswer(
                userMessageRequest, userDetails.getId()
        );
        messagingTemplate.convertAndSendToUser(
                userDetails.getId().toString(),
                "/queue/messages",
                chatBotMessageResponse
        );
    }

    @MessageMapping("/chat.audio")
    public void processAudioMessage(
            @Payload final NewDefaultUserMessageRequest userMessageRequest,
            @AuthenticationPrincipal final UserDetailsImpl userDetails
    ) {
        ChatBotMessageResponse chatBotMessageResponse = chatMessageService.createAudioAnswer(
                userMessageRequest, userDetails.getId()
        );
        messagingTemplate.convertAndSendToUser(
                userDetails.getId().toString(),
                "/queue/messages",
                chatBotMessageResponse
        );
    }

    @MessageMapping("/chat.capture")
    public void processCaptureMessage(
            @Payload final NewDefaultUserMessageRequest userMessageRequest,
            @AuthenticationPrincipal final UserDetailsImpl userDetails
    ) {
        ChatBotMessageResponse chatBotMessageResponse = chatMessageService.createAudioAnswer(
                userMessageRequest, userDetails.getId()
        );
        messagingTemplate.convertAndSendToUser(
                userDetails.getId().toString(),
                "/queue/messages",
                chatBotMessageResponse
        );
    }
}
