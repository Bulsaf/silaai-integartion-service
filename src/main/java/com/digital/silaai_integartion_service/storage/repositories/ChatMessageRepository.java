package com.digital.silaai_integartion_service.storage.repositories;

import com.digital.silaai_integartion_service.storage.entities.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, UUID> {

    List<ChatMessageEntity> findAllBySenderIdOrRecipientIdOrderByCreatedAt(UUID senderId, UUID recipientId);

}
