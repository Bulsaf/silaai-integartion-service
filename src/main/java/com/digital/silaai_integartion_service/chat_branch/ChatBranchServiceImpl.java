package com.digital.silaai_integartion_service.chat_branch;

import com.digital.silaai_integartion_service.chat_branch.responses.NewChatBranchResponse;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatBranchServiceImpl implements ChatBranchService {

    @Nonnull
    @Override
    public NewChatBranchResponse createBranch(@Nonnull UUID userId) {
        return null;
    }

}
