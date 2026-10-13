package com.example.demo.model.response;

public record AverageResponse(
        Double average,
        Integer min,
        Integer max) {
}