package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@RestController 
@RequestMapping("/api")
public class HealthController {
    
    // Injection des valeurs depuis application.properties
    @Value("${spring.application.name:Application Inconnue}")
    private String appName;

    @Value("1.0.0")
    private String appVersion;

    // --- Records ---

    public record PingResponse(
            String status,
            @JsonProperty("server_time") LocalDateTime serverTime
    ) {}

    public record InfoResponse(
            String name,
            String version
    ) {}

    // --- Routes ---

    @GetMapping("/ping")
    public PingResponse ping() {
        return new PingResponse("EVERYTHING_OK", LocalDateTime.now());
    }

    @GetMapping("/info")
    public InfoResponse info() {
        return new InfoResponse(appName, appVersion);
    }

}
