package com.pm.twilo.controller;

import com.pm.twilo.dto.VerificationCheckRequestDTO;
import com.pm.twilo.dto.VerificationRequestDTO;
import com.pm.twilo.dto.VerificationResponseDTO;
import com.pm.twilo.service.VerificationServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationServiceInterface verificationService;

    @PostMapping("/send")
    public ResponseEntity<VerificationResponseDTO> sendOtp(
            @Valid @RequestBody VerificationRequestDTO request) {
        VerificationResponseDTO response = verificationService.sendOtp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<VerificationResponseDTO> verifyOtp(
            @Valid @RequestBody VerificationCheckRequestDTO request) {
        VerificationResponseDTO response = verificationService.verifyOtp(request);
        return ResponseEntity.ok(response);
    }
}