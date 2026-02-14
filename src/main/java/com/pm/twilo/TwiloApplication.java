package com.pm.twilo;

import com.pm.twilo.configuration.TwilioConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TwilioConfig.class)
public class TwiloApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwiloApplication.class, args);
    }

}
