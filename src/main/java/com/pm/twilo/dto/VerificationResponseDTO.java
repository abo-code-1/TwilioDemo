package com.pm.twilo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationResponseDTO {
    private String phoneNumber;
    private String status;
    private boolean valid;
    private String message;
}