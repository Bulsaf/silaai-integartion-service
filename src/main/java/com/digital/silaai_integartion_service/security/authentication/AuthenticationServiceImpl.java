package com.digital.silaai_integartion_service.security.authentication;

import com.digital.silaai_integartion_service.exceptions.BadRequestException;
import com.digital.silaai_integartion_service.security.JwtUtils;
import com.digital.silaai_integartion_service.security.UserDetailsImpl;
import com.digital.silaai_integartion_service.security.authentication.requests.SignInRequest;
import com.digital.silaai_integartion_service.security.authentication.requests.SignUpRequest;
import com.digital.silaai_integartion_service.security.authentication.responses.SignInResponse;
import com.digital.silaai_integartion_service.security.authentication.responses.SignInWithTokensResponse;
import com.digital.silaai_integartion_service.security.refresh_tokens.RefreshTokenService;
import com.digital.silaai_integartion_service.security.refresh_tokens.responses.GetRefreshTokenResponse;
import com.digital.silaai_integartion_service.users.UserService;
import com.digital.silaai_integartion_service.users.requests.NewUserRequest;
import com.digital.silaai_integartion_service.users.responses.GetUserResponse;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    @Nonnull
    public SignInWithTokensResponse signInUser(@Nonnull SignInRequest signInRequest) {
        if(!userService.checkUser(signInRequest.username(), signInRequest.password())){
            throw new BadRequestException("Username or password is incorrect");
        }

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(signInRequest.username(), signInRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        GetRefreshTokenResponse refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
        String accessToken = jwtUtils.generateAccessJwt(signInRequest.username());
        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.refreshToken());

        SignInResponse signInResponse = SignInResponse.builder()
                                                      .id(userDetails.getId().toString())
                                                      .username(userDetails.getUsername())
                                                      .roles(roles)
                                                      .build();
        return SignInWithTokensResponse.builder()
                .signInResponse(signInResponse)
                .accessToken(accessToken)
                .refreshToken(jwtRefreshCookie.toString())
                .build();
    }

    @Override
    @Transactional
    public void registerUser(@Nonnull SignUpRequest signUpRequest) {
        NewUserRequest newUserRequest = NewUserRequest.builder()
                                                      .username(signUpRequest.username())
                                                      .password(signUpRequest.password())
                                                      .build();
        userService.createUser(newUserRequest);
    }

    @Override
    public String logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(principle.toString(), "anonymousUser")) {
            String username = ((UserDetailsImpl) principle).getUsername();
            refreshTokenService.deleteByUsername(username);
        }

        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();
        return jwtRefreshCookie.toString();
    }

    @Nonnull
    @Override
    public String getNewAccessToken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new BadRequestException("Refresh token is incorrect");
        }

        GetRefreshTokenResponse verifyToken = refreshTokenService.verifyExpiration(refreshToken);
        GetUserResponse getUserResponse = userService.getUserByUsername(verifyToken.username());
        return jwtUtils.generateAccessJwt(getUserResponse.username());
    }


}
