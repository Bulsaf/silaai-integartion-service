package com.digital.silaai_integartion_service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/user-uuid")
@RequiredArgsConstructor
public class UserUUIDController {

    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserUUIDResponse> fetchUserUUID() {
        GetUserUUIDResponse newUserUUID = userService.createUserUUID();
        return ResponseEntity.ok()
                             .body(newUserUUID);
    }
}
