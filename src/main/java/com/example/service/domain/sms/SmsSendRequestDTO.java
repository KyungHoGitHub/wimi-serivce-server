package com.example.service.domain.sms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "sms 인증번호 발송 요청 DTO")
public class SmsSendRequestDTO {

    @NotBlank(message = "휴대폰번호는 필수 입니다.")
    @Schema(description = "휴대폰 번호", example = "01012341234", required = true)
    String phoneNumber;

}
