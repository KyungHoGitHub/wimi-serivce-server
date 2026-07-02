package com.example.service.domain.sms;

import com.example.service.common.response.CommonResponse;
import com.example.service.common.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SmsController {

    private final SmsSolaApiService smsSolaApiService;

    @Operation(summary = "sms 인증코드 발송", description = "입력한 휴대폰 번호로 6자리 인증코드가 발송됩니다.")
    @ApiResponse(responseCode = "200", description = "발송 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @PostMapping("/send")
    public ResponseEntity<CommonResponse<String>> sendSms(@Valid @RequestBody SmsSendRequestDTO request) {
        String toNumber = request.getPhoneNumber();
        smsSolaApiService.sendVerificationSms(toNumber);
        return ResponseEntity.status(SuccessCode.SMS_SEND.getStatus())
                .body(CommonResponse.of(SuccessCode.SMS_SEND, "123456"));
    }

    @Operation(summary = "sms 인증코드 검증", description = "입력한 6자리 인증코드 검증.")
    @ApiResponse(responseCode = "200", description = "발송 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @PostMapping("/verify")
    public ResponseEntity<CommonResponse<String>> verifySms(@RequestBody SmsVerifyRequestDTO request) {
        String toNumber = request.getPhoneNumber();
        String inputCode = request.getCode();
        smsSolaApiService.verifySms(toNumber, inputCode);

        return ResponseEntity.status(
                SuccessCode.SMS_VARIFY.getStatus())
                .body(CommonResponse.of(SuccessCode.SMS_VARIFY, "인증코드 검증 성공"));
    }
}

