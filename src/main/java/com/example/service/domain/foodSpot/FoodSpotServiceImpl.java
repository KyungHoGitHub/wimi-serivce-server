package com.example.service.domain.foodSpot;

import com.amazonaws.util.StringUtils;
import com.example.service.common.exception.FoodSpotNotFoundException;
import com.example.service.common.response.SliceResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodSpotServiceImpl implements FoodSpotService {
    private final FoodSpotRepository foodSpotRepository;

    @Override
    @Transactional
    public void createFoodSpot(FoodSpotRequestDTO requestDTO, String userId) {

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

    @Override
    public SliceResponse<FoodSpotResponseDTO> getFoodSpotList(String userId, String keyword, int page, int size) {
        Sort sort = StringUtils.hasValue(keyword)
                ? Sort.by("name").ascending()
                : Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Slice<FoodSpot> slice = foodSpotRepository.search(userId, keyword, pageable);

        List<FoodSpotResponseDTO> content = slice.getContent().stream()
                .map(FoodSpotResponseDTO::from)
                .toList();

        return new SliceResponse<>(content, slice.hasNext());

    }

    @Override
    @Transactional(readOnly = true)
    public FoodSpotResponseDTO getFoodSpotDetail(Long foodSpotId, String userId) {

        FoodSpot foodSpot = foodSpotRepository.findById(foodSpotId).orElseThrow(() -> new FoodSpotNotFoundException(foodSpotId));

        return FoodSpotResponseDTO.from(foodSpot);
    }

    @Override
    public void deleteFoodSpot(Long foodSpotId, String userId) {
        foodSpotRepository.deleteByIdAndCreatedBy(foodSpotId, userId);
    }

    @Override
    @Transactional
    public void updateFoodSpot(Long foodSpotId, FoodSpotRequestDTO requestDTO, String userId) {
        FoodSpot foodSpot = foodSpotRepository.findById(foodSpotId).orElseThrow(() -> new FoodSpotNotFoundException(foodSpotId));

        if(!foodSpot.isCreatedBy(userId)){
            throw new FoodSpotNotFoundException(foodSpotId);
        }

        foodSpot.update(
                requestDTO.getName(),
                requestDTO.getMenu(),
                requestDTO.getAddress(),
                requestDTO.getReview(),
                requestDTO.getImageKey(),
                requestDTO.getLat(),
                requestDTO.getLng()
        );
        foodSpot.replaceMenus(
                requestDTO.getMenuPrices().stream()
                        .map(m -> FoodSpotMenuBoard.builder()
                                .name(m.getName())
                                .price(m.getPrice())
                                .orderIndex(m.getOrderIndex())
                                .build())
                        .toList()
        );
    }
}
