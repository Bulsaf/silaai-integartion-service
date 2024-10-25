package com.digital.silaai_integartion_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/heartbeat")
public class HeartbeatController {

    @PostMapping()
    public ResponseEntity<String> heartbeat() {
        return ResponseEntity.ok("Heartbeat OK!");
    }

}
