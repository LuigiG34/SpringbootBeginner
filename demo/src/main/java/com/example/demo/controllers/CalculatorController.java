package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.response.AverageResponse;
import com.example.demo.exception.EmptyListException;

@RestController 
@RequestMapping("/api/calc")
public class CalculatorController {
    
    @GetMapping("/square/{n}")
    public ResponseEntity<Integer> square(@PathVariable ("n") int n) {
        int result = n * n; 
        return ResponseEntity.ok(result);
    }

    @GetMapping("/add")
    public ResponseEntity<Integer> add(@RequestParam(required = true) int a, @RequestParam(required = false, defaultValue = "0") int b) {
        int result = a + b;
        return ResponseEntity.ok(result);
    }

    @GetMapping("/divide")
    public ResponseEntity<Integer> divide(@RequestParam(required = true) int a, @RequestParam(required = true) int b) {
        if(b == 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(a / b);
    }

    @PostMapping("/average")
    public ResponseEntity<AverageResponse> average(@RequestBody List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new EmptyListException("La liste de nombres est vide.");
        }

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


        AverageResponse response = new AverageResponse(average, min, max);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(EmptyListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleEmptyList(EmptyListException exception) {
        return Map.of("error", exception.getMessage());
    }
}
