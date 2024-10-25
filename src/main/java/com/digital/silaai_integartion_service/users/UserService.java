package com.digital.silaai_integartion_service.users;

import com.digital.silaai_integartion_service.users.requests.NewUserRequest;
import com.digital.silaai_integartion_service.users.responses.GetUserResponse;
import com.digital.silaai_integartion_service.users.responses.NewUserResponse;
import jakarta.annotation.Nonnull;

public interface UserService {

    NewUserResponse createUser(@Nonnull NewUserRequest user);

    GetUserResponse getUserByUsername(@Nonnull String username);

    boolean checkUser(@Nonnull String username, @Nonnull String password);
}
