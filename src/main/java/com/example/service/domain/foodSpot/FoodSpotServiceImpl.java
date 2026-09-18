package com.example.service.domain.foodSpot;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodSpotServiceImpl implements FoodSpotService {
    private final FoodSpotRepository foodSpotRepository;

    @Override
    @Transactional
    public void createFoodSpot(FoodSpotRequestDTO requestDTO,String userId) {

    }
}
