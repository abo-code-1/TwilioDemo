package com.pm.twilo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationCheckRequestDTO {
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Phone number must be in E.164 format (e.g., +77012345678)")
    private String phoneNumber;

    @NotBlank(message = "Verification code is required")
    @Pattern(regexp = "^\\d{4,8}$", message = "Code must be 4-8 digits")
    private String code;
}