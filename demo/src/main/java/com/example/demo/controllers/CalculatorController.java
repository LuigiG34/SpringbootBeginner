package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.response.AverageResponse;

@RestController 
@RequestMapping("/api/calc")
public class CalculatorController {
    
    @GetMapping("/square/{n}")
    public int square(@PathVariable ("n") int n) {
        int result = n * n; 
        return result;
    }

    @GetMapping("/add")
    public int add(@RequestParam(required = true) int a, @RequestParam(required = false, defaultValue = "0") int b) {
        int result = a + b;
        return result;
    }

    @GetMapping("/divide")
    public int divide(@RequestParam(required = true) int a, @RequestParam(required = true) int b) {
        int result = a / b;
        return result;
    }

    @PostMapping("/average")
    public AverageResponse average(@RequestBody List<Integer> numbers) {
        // Moyenne des nombres
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        // Maximum des nombres
        int max = numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        // Minimum des nombres
        int min = numbers.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);

        return new AverageResponse(average, min, max);
    }
}
