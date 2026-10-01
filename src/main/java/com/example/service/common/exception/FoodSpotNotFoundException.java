package com.example.service.common.exception;

public class FoodSpotNotFoundException extends RuntimeException{
    public FoodSpotNotFoundException(Long foodSpotId) {
        super("not found foodSpotId: " + foodSpotId);
    }
}
