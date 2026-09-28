package com.example.service.domain.foodSpot;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodSpotServiceImpl implements FoodSpotService {
    private final FoodSpotRepository foodSpotRepository;

    @Override
    @Transactional
    public void createFoodSpot(FoodSpotRequestDTO requestDTO,String userId) {

        FoodSpot foodSpot = FoodSpot.builder()
                        .name(requestDTO.getName())
                .menu(requestDTO.getMenu())
                .address(requestDTO.getAddress())
                .review(requestDTO.getReview())
                .imageKey(requestDTO.getImageKey())
                .lat(requestDTO.getLat())
                .lng(requestDTO.getLng())
                .createdBy(userId)
                .build();

        requestDTO.getMenuPrices().forEach(m ->
                foodSpot.addMenu(FoodSpotMenuBoard.builder()
                        .name(m.getName())
                        .price(m.getPrice())
                        .orderIndex(m.getOrderIndex())
                        .build()));

        foodSpotRepository.save(foodSpot);
    }
}
