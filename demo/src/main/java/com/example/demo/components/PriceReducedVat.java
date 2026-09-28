package com.example.demo.components;

import org.springframework.stereotype.Component;

@Component 
public class PriceReducedVat implements PriceCalculator {
    
    public PriceReducedVat() {
        System.out.println("--> Création de PriceReducedVat");
    }
    
    @Override
    public double calculateTotalPrice(double grossPrice) {
        double vatRate = 1.055;
        return grossPrice * vatRate;
    }
}
