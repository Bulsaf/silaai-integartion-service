package com.digital.silaai_integartion_service.users.responses;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record GetUserResponse (
        UUID id,
        String username,
        String role
) {
}
