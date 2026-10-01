package com.example.service.domain.foodSpot;

import com.example.service.common.response.CommonResponse;
import com.example.service.common.response.SliceResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foodSpot")
public class FoodSpotController {

    private final FoodSpotService foodSpotService;

    @Operation(summary = "맛집 리스트 조회", description = "전체 맛집 리스트 목록 조회")
    @GetMapping
    public ResponseEntity<CommonResponse<SliceResponse<FoodSpotResponseDTO>>> getFoodSpotList(
            @AuthenticationPrincipal String userId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(CommonResponse.success(
                foodSpotService.getFoodSpotList(userId, keyword, page, size)));
    }

    @Operation(summary = "특정 맛집 정보 조회")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<FoodSpotResponseDTO>> getFoodSpot(
            @PathVariable("id") Long foodSpotId,
            @AuthenticationPrincipal String userId
    ) {
        FoodSpotResponseDTO foodSpotResponseDTO = foodSpotService.getFoodSpotDetail(foodSpotId, userId);

        return ResponseEntity.ok(CommonResponse.success(foodSpotResponseDTO));
    }

    @Operation(summary = "맛집 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> updateFoodSpot(
            @PathVariable("id") Long foodSpotId,
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody FoodSpotRequestDTO requestDTO
    ) {
        foodSpotService.updateFoodSpot(foodSpotId, requestDTO, userId);

        return ResponseEntity.ok(CommonResponse.of(null, null));
    }

    @Operation(summary = "맛집 생성 요청")
    @PostMapping()
    public ResponseEntity<CommonResponse<Void>> createFoodSpot(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody FoodSpotRequestDTO requestDTO
    ) {
        foodSpotService.createFoodSpot(requestDTO, userId);
        return ResponseEntity.ok(CommonResponse.created(null));
    }

    @Operation(summary = "맛집 정보 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteFoodSpot(
            @PathVariable("id") Long foodSpotId,
            @AuthenticationPrincipal String userId
    ) {
        foodSpotService.deleteFoodSpot(foodSpotId, userId);
        return ResponseEntity.ok(CommonResponse.success(null));
    }

}
