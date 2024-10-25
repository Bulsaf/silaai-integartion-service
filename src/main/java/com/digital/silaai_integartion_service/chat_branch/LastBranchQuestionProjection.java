package com.digital.silaai_integartion_service.chat_branch;

import java.util.Map;
import java.util.UUID;

public record LastBranchQuestionProjection(
        UUID branchId,
        Map<String, Object> content
) {
}
