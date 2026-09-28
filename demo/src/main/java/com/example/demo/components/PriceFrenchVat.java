package com.example.demo.components;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component 
@Primary 
public class PriceFrenchVat implements PriceCalculator {

    public PriceFrenchVat() {
        System.out.println("--> Création de PriceFrenchVat");
    }

    @Override
    public double calculateTotalPrice(double grossPrice) {
        double vatRate = 1.2;
        return grossPrice * vatRate;
    }

}
