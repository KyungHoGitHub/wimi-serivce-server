package com.example.service.domain.dailyComment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DailyCommentController {

    private final DailyCommentService dailyCommentService;

    @GetMapping("/api/daily/{dailyId}/comment")
    public ResponseEntity<List<DailyCommentResponseDTO>> getDailyCommentList(
            @PathVariable("dailyId") Long dailyId,
            @AuthenticationPrincipal String userId
    ) {
        return ResponseEntity.ok(dailyCommentService.getComments(dailyId,userId));
    }

    @PostMapping("/api/daily/{dailyId}/comment")
    public ResponseEntity<DailyCommentResponseDTO> createDailyComment(
            @PathVariable Long dailyId,
            @RequestBody DailyCommentCreateRequestDTO requestDTO,
            @AuthenticationPrincipal String userId
    ) {
        return ResponseEntity.ok(dailyCommentService.createComment(dailyId, requestDTO, userId));
    }

    @PutMapping("/api/daily/{commentId}/comment")
    public ResponseEntity<?> updateDailyComment(@PathVariable Long commentId,
                                                @RequestBody DailyCommentUpdateRequestDTO requestDTO,
                                                @AuthenticationPrincipal String userId
    ){
        DailyCommentResponseDTO result = dailyCommentService.updateComment(commentId, requestDTO, userId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/daily/{dailyId}/comment/{commentId}")
    public ResponseEntity<?> deleteDailyComment(@PathVariable Long dailyId,
                                                @PathVariable Long commentId,
                                                @AuthenticationPrincipal String userId){
        dailyCommentService.deleteComment(commentId,userId);
        return ResponseEntity.ok("success");
    }


}
