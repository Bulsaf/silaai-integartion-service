package com.digital.silaai_integartion_service.user;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GetUserUUIDResponse(
        @Nonnull UUID id
) {
}
