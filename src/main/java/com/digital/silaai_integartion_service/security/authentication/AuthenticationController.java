package com.digital.silaai_integartion_service.security.authentication;

import com.digital.silaai_integartion_service.security.authentication.requests.SignInRequest;
import com.digital.silaai_integartion_service.security.authentication.requests.SignUpRequest;
import com.digital.silaai_integartion_service.security.authentication.responses.SignInResponse;
import com.digital.silaai_integartion_service.security.authentication.responses.SignInWithTokensResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        SignInWithTokensResponse signInWithTokensResponse = authenticationService.signInUser(signInRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, signInWithTokensResponse.refreshToken())
                .header("Authorization", "Bearer " + signInWithTokensResponse.accessToken())
                .body(signInWithTokensResponse.signInResponse());
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        authenticationService.registerUser(signUpRequest);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> logoutUser() {
        String refreshToken = authenticationService.logoutUser();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshToken)
                .build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshtoken(HttpServletRequest request) {
        String accessToken = authenticationService.getNewAccessToken(request);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + accessToken)
                .build();
    }

}