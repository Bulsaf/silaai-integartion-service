package com.digital.silaai_integartion_service.users.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record NewUserResponse(
        UUID id,
        String username
) {
}
