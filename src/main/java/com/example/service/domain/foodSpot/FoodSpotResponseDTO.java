package com.example.service.domain.foodSpot;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodSpotResponseDTO {
    Long id;
    String name;
    String menu;
    String address;
    String review;
    String imageKey;
    Double lat;
    Double lng;
    List<MenuPrice> menuPrices;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDateTime createdAt;

    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class MenuPrice {
        Long id;
        String name;
        Integer price;
        Integer orderIndex;
    }

    public static FoodSpotResponseDTO from(FoodSpot e) {
        return FoodSpotResponseDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .menu(e.getMenu())
                .address(e.getAddress())
                .review(e.getReview())
                .imageKey(e.getImageKey())
                .lat(e.getLat())
                .lng(e.getLng())
                .createdAt(e.getCreatedAt())
                .menuPrices(e.getMenus().stream()
                        .map(m -> MenuPrice.builder()
                                .id(m.getId())
                                .name(m.getName())
                                .price(m.getPrice())
                                .orderIndex(m.getOrderIndex())
                                .build())
                        .toList())
                .build();
    }
}
