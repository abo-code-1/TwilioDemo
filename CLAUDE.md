# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build
./mvnw clean compile

# Run with dev profile (mock verification, no Twilio credentials needed)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Run with prod profile (real Twilio integration)
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=TwiloApplicationTests

# Package
./mvnw clean package -DskipTests
```

## Architecture

Spring Boot 4.0.2 service for OTP verification via Twilio WhatsApp.

**Profiles:**
- `dev` - Uses `MockVerificationService` with hardcoded OTP "123456", no Twilio credentials required
- `prod` - Uses `VerificationService` with real Twilio Verify API

**Key Components:**
- `VerificationController` - REST API at `/api/otp` with `/send` and `/verify` endpoints
- `VerificationServiceInterface` - Service contract implemented by profile-specific services
- `TwilioConfig` - Configuration properties bound from `twilio.*` prefix, initializes Twilio SDK

**Environment Variables (required for prod profile):**
- `TWILIO_ACCOUNT_SID`
- `TWILIO_AUTH_TOKEN`
- `TWILIO_PHONE_NUMBER`
- `TWILIO_VERIFY_SERVICE_SID`

Phone numbers must be in E.164 format (e.g., +77012345678).

## API Examples

See `requests.http` for IntelliJ HTTP client examples.