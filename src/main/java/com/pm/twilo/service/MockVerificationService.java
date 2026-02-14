package com.pm.twilo.service;

import com.pm.twilo.dto.VerificationCheckRequestDTO;
import com.pm.twilo.dto.VerificationRequestDTO;
import com.pm.twilo.dto.VerificationResponseDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Profile("dev")
public class MockVerificationService implements VerificationServiceInterface {

    // Store OTPs in memory for verification (phone -> code)
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();
    private static final String MOCK_OTP = "123456";

    @Override
    public VerificationResponseDTO sendOtp(VerificationRequestDTO request) {
        String phoneNumber = request.getPhoneNumber();

        // Store mock OTP
        otpStore.put(phoneNumber, MOCK_OTP);

        System.out.println("[MOCK] OTP sent to " + phoneNumber + ": " + MOCK_OTP);

        return VerificationResponseDTO.builder()
                .phoneNumber(phoneNumber)
                .status("pending")
                .valid(false)
                .message("[DEV MODE] Mock OTP sent. Use code: " + MOCK_OTP)
                .build();
    }

    @Override
    public VerificationResponseDTO verifyOtp(VerificationCheckRequestDTO request) {
        String phoneNumber = request.getPhoneNumber();
        String code = request.getCode();

        String storedOtp = otpStore.get(phoneNumber);
        boolean isValid = storedOtp != null && storedOtp.equals(code);

        if (isValid) {
            otpStore.remove(phoneNumber);
        }

        return VerificationResponseDTO.builder()
                .phoneNumber(phoneNumber)
                .status(isValid ? "approved" : "pending")
                .valid(isValid)
                .message(isValid ? "[DEV MODE] OTP verified successfully" : "[DEV MODE] Invalid OTP code")
                .build();
    }
}