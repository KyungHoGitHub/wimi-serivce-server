package com.example.service.domain.foodSpot;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FoodSpotController {

    private final FoodSpotService foodSpotService;

    @GetMapping
    public void getFoodSpotList(){
    }

    @GetMapping("/{id}")
    public void getFoodSpot(){
    }

    @PutMapping
    public void updateFoodSpot(
            @AuthenticationPrincipal String userId,
            @RequestBody FoodSpotRequestDTO requestDTO
    ){
        foodSpotService.createFoodSpot(requestDTO,userId);
    }

    @PostMapping("/api/foodSpot")
    public void createFoodSpot(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody FoodSpotRequestDTO requestDTO
    ){
        foodSpotService.createFoodSpot(requestDTO,userId);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodSpot(){
    }

}
