package com.digital.silaai_integartion_service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private static final String CHAT_HISTORY = "/{userId}";

    @GetMapping(value = CHAT_HISTORY,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GetChatMessageResponse>> fetchChatHistory(){
        List<GetChatMessageResponse> chatHistoryList = List.of();
        return ResponseEntity.ok(chatHistoryList);
    }
}
