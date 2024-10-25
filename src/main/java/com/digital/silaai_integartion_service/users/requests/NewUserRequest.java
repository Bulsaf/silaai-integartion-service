package com.digital.silaai_integartion_service.users.requests;

import jakarta.annotation.Nonnull;
import lombok.Builder;

@Builder
public record NewUserRequest(
        @Nonnull String username,
        @Nonnull String password
) {
}
