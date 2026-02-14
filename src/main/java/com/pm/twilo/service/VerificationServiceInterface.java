package com.pm.twilo.service;

import com.pm.twilo.dto.VerificationCheckRequestDTO;
import com.pm.twilo.dto.VerificationRequestDTO;
import com.pm.twilo.dto.VerificationResponseDTO;

public interface VerificationServiceInterface {
    VerificationResponseDTO sendOtp(VerificationRequestDTO request);
    VerificationResponseDTO verifyOtp(VerificationCheckRequestDTO request);
}