package com.digital.silaai_integartion_service.storage.repositories;

import com.digital.silaai_integartion_service.storage.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
