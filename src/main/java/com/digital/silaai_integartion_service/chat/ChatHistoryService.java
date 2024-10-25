package com.digital.silaai_integartion_service.chat;

import java.util.List;
import java.util.UUID;

public interface ChatHistoryService {

    List<GetChatMessageResponse> getChatHistoryByUserId(UUID userId);

}
