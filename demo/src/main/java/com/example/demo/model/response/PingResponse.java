package com.example.demo.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PingResponse(
        String status,
        @JsonProperty("server_time") LocalDateTime serverTime) {
}
