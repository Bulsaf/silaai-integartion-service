package com.digital.silaai_integartion_service.chat_branch;

import com.digital.silaai_integartion_service.chat_branch.responses.GetChatBranchInfoResponse;
import com.digital.silaai_integartion_service.chat_branch.responses.NewChatBranchResponse;
import com.digital.silaai_integartion_service.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat-branch")
@RequiredArgsConstructor
public class ChatBranchController {

    private static final String LAST_BRANCHES = "/last";

    private final ChatBranchService chatBranchService;

    @GetMapping(value = LAST_BRANCHES)
    public ResponseEntity<List<GetChatBranchInfoResponse>> fetchLastFiveBranches(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<GetChatBranchInfoResponse> chatBranchInfoResponses =  chatBranchService.getUserLastBranches(userDetails.getId());
        return ResponseEntity.ok()
                             .body(chatBranchInfoResponses);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NewChatBranchResponse> createNewBranch(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        NewChatBranchResponse newChatBranch = chatBranchService.createBranch(userDetails.getId());
        return ResponseEntity.ok()
                             .body(newChatBranch);
    }
}
