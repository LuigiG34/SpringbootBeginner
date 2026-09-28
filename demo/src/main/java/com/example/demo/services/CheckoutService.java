package com.example.demo.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.components.PriceCalculator;

@Service 
public class CheckoutService {

    private final PriceCalculator priceCalculator;

    public CheckoutService(@Qualifier("priceFrenchVat") PriceCalculator priceCalculator) {
        System.out.println("--> Création de CheckoutService");
        this.priceCalculator = priceCalculator;
    }

}
