package com.digital.silaai_integartion_service.chat_branch.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record GetChatBranchInfoResponse(
        UUID branchId,
        String lastQuestion
) {
}
