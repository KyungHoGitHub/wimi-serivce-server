package com.example.service.domain.foodSpot;

import com.example.service.common.response.SliceResponse;

public interface FoodSpotService {
    void createFoodSpot(FoodSpotRequestDTO requestDTO,String userId);
    SliceResponse<FoodSpotResponseDTO> getFoodSpotList(String userId, String keyword, int page, int size);
    FoodSpotResponseDTO getFoodSpotDetail(Long foodSpotId,String userId);
    void deleteFoodSpot(Long foodSpotId,String userId);
    void updateFoodSpot(Long foodSpotId,FoodSpotRequestDTO requestDTO,String userId);
}
