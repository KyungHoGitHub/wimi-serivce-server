package com.example.service.domain.foodSpot;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodSpotRequestDTO {
    @NotBlank
    String imageKey;

    @NotBlank
    String name;

    @NotBlank
    String menu;

    @NotBlank
    String address;

    Double lat;

    Double lng;

    String review;

    @Valid
    @Builder.Default
    List<MenuPriceRequestDto> menuPrices = new ArrayList<>();

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class MenuPriceRequestDto {

        @NotBlank
        String name;

        @NotNull
        @Min(0)
        Integer price;

        @NotNull
        Integer orderIndex;
    }
}

