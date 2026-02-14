package com.pm.twilo.service;

import com.pm.twilo.configuration.TwilioConfig;
import com.pm.twilo.dto.VerificationCheckRequestDTO;
import com.pm.twilo.dto.VerificationRequestDTO;
import com.pm.twilo.dto.VerificationResponseDTO;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class VerificationService implements VerificationServiceInterface {
    private static final String WHATSAPP_CHANNEL = "whatsapp";
    private final TwilioConfig twilioConfig;

    public VerificationResponseDTO sendOtp(VerificationRequestDTO request) {
        Verification verification = Verification.creator(
                twilioConfig.getVerifyServiceSid(),
                request.getPhoneNumber(),
                WHATSAPP_CHANNEL
        ).create();

        return VerificationResponseDTO.builder()
                .phoneNumber(request.getPhoneNumber())
                .status(verification.getStatus())
                .valid(false)
                .message("OTP sent via WhatsApp")
                .build();
    }

    public VerificationResponseDTO verifyOtp(VerificationCheckRequestDTO request) {
        VerificationCheck verificationCheck = VerificationCheck.creator(
                twilioConfig.getVerifyServiceSid()
        )
                .setTo(request.getPhoneNumber())
                .setCode(request.getCode())
                .create();

        boolean isApproved = "approved".equals(verificationCheck.getStatus());

        return VerificationResponseDTO.builder()
                .phoneNumber(request.getPhoneNumber())
                .status(verificationCheck.getStatus())
                .valid(isApproved)
                .message(isApproved ? "OTP verified successfully" : "Invalid OTP code")
                .build();
    }
}