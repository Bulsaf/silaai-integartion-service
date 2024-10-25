package com.digital.silaai_integartion_service.storage.repositories;

import com.digital.silaai_integartion_service.storage.entities.ChatBranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatBranchRepository extends JpaRepository<ChatBranchEntity, UUID> {
}
