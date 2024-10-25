package com.digital.silaai_integartion_service.chat;

import com.digital.silaai_integartion_service.chat.responses.GetChatMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private static final String CHAT_HISTORY = "/{branchId}";

    private final ChatHistoryService chatHistoryService;

    @GetMapping(value = CHAT_HISTORY,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GetChatMessageResponse>> fetchChatHistory(@PathVariable("branchId") UUID branchId){
        List<GetChatMessageResponse> chatHistoryList = chatHistoryService.getChatHistoryByBranchId(branchId);
        return ResponseEntity.ok(chatHistoryList);
    }
}
