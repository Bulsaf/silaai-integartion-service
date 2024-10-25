package com.digital.silaai_integartion_service.chat_branch;

import com.digital.silaai_integartion_service.chat_branch.responses.GetChatBranchInfoResponse;
import com.digital.silaai_integartion_service.chat_branch.responses.NewChatBranchResponse;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

public interface ChatBranchService {

    @Nonnull
    NewChatBranchResponse createBranch(@Nonnull UUID userId);

    List<GetChatBranchInfoResponse> getUserLastBranches(@Nonnull UUID userId);
}
