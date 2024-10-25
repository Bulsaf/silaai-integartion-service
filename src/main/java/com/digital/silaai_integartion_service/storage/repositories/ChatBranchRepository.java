package com.digital.silaai_integartion_service.storage.repositories;

import com.digital.silaai_integartion_service.chat_branch.LastBranchQuestionProjection;
import com.digital.silaai_integartion_service.storage.entities.ChatBranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChatBranchRepository extends JpaRepository<ChatBranchEntity, UUID> {

    @Query("select cb.id, cm.content from ChatBranchEntity cb " +
            "inner join ChatMessageEntity cm " +
            "on cm.recipientId = :userId " +
            "order by cm.createdAt desc " +
            "limit 5"
    )
    List<LastBranchQuestionProjection> findLastBranchMessagesByUserId(UUID userId);
}
