package com.pm.twilo.configuration;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String phoneNumber;
    private String verifyServiceSid;

    @PostConstruct
    public void init(){
        Twilio.init(accountSid, authToken);
    }
}
