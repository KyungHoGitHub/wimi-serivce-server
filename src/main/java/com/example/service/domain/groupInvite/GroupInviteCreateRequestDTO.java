package com.example.service.domain.groupInvite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "그룹 초대 DTO")
public class GroupInviteCreateRequestDTO {

    @Schema(description = "초대하는 유저 ID", example = "user_id", required = true)
    String invitedUserId;

    @Schema(description = "초대 받는 유저 ID", example = "user_id", required = true)
    String invitedBy;

    String groupName;

    @Schema(description = "상태", example = "pending", required = true)
    Status status;

    @Schema(description = "그룹 ID", example = "1", required = true)
    Long groupId;
}
