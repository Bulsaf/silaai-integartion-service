package com.digital.silaai_integartion_service.chat;

import com.digital.silaai_integartion_service.chat.responses.GetChatMessageResponse;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

public interface ChatHistoryService {

    List<GetChatMessageResponse> getChatHistoryByBranchId(@Nonnull UUID branchId);

}
