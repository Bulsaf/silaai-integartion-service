package com.digital.silaai_integartion_service.security.authentication;

import com.digital.silaai_integartion_service.security.authentication.requests.SignInRequest;
import com.digital.silaai_integartion_service.security.authentication.requests.SignUpRequest;
import com.digital.silaai_integartion_service.security.authentication.responses.SignInWithTokensResponse;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {

    @Transactional
    @Nonnull
    SignInWithTokensResponse signInUser(@Nonnull SignInRequest signInRequest);

    @Transactional
    void registerUser(@Nonnull SignUpRequest signUpRequest);

    String logoutUser();

    @Nonnull
    String getNewAccessToken(HttpServletRequest request);
}
