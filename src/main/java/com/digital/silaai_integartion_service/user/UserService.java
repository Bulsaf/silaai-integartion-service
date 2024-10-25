package com.digital.silaai_integartion_service.user;

import jakarta.annotation.Nonnull;

public interface UserService {

    @Nonnull GetUserUUIDResponse createUserUUID();

}
