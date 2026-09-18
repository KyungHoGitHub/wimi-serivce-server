package com.example.service.domain.foodSpot;

import com.example.service.domain.userSummary.UserSummary;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FoodSpotRequestDTO {
    @NotBlank
    private String imageKey;

    @NotBlank
    private String name;

    @NotBlank
    private String menu;

    @NotBlank
    private String address;

    private String review;

    @Valid
    @NotEmpty
    private List<MenuPriceRequestDto> menuPrices;

    @Getter
    @NoArgsConstructor
    public static class MenuPriceRequestDto {

        @NotBlank
        private String name;

        @NotNull
        @Min(0)
        private Integer price;

        @NotNull
        private Integer orderIndex;
    }
}

