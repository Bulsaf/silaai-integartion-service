package com.digital.silaai_integartion_service.chat_branch;

import com.digital.silaai_integartion_service.chat_branch.responses.GetChatBranchInfoResponse;
import com.digital.silaai_integartion_service.chat_branch.responses.NewChatBranchResponse;
import com.digital.silaai_integartion_service.storage.entities.ChatBranchEntity;
import com.digital.silaai_integartion_service.storage.repositories.ChatBranchRepository;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatBranchServiceImpl implements ChatBranchService {

    private final ChatBranchRepository chatBranchRepository;
    private final TimeBasedEpochRandomGenerator timeBasedEpochUUIDRandomGenerator;

    @Nonnull
    @Override
    public NewChatBranchResponse createBranch(@Nonnull UUID userId) {
        ChatBranchEntity newBranchEntity = ChatBranchEntity.builder()
                                                           .id(timeBasedEpochUUIDRandomGenerator.generate())
                                                           .userId(userId)
                                                           .build();
        chatBranchRepository.save(newBranchEntity);
        return NewChatBranchResponse.builder()
                                    .branchId(newBranchEntity.getId())
                                    .build();
    }

    @Override
    public List<GetChatBranchInfoResponse> getUserLastBranches(@Nonnull UUID userId) {
        List<LastBranchQuestionProjection> lastBranchQuestionProjections =
                chatBranchRepository.findLastBranchMessagesByUserId(userId);
        return lastBranchQuestionProjections.stream()
                .map(it -> {
                    if (it.content().containsKey("message")){
                        return GetChatBranchInfoResponse.builder()
                                                        .branchId(it.branchId())
                                                        .lastQuestion((
                                                                (String)it.content().get("message")
                                                        ).substring(0, 10))
                                                        .build();
                    }
                    return null;
                })
                .toList();
    }

}
