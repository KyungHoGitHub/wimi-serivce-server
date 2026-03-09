package com.example.service.domain.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SmsController {

    private final SmsSolaApiService smsSolaApiService;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SmsSendRequestDTO request) {
        String toNumber = request.getPhoneNumber();
        smsSolaApiService.sendVerificationSms(toNumber);

        return ResponseEntity.ok("인증번호가 발송되었습니다.");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifySms(@RequestBody SmsVerifyRequestDTO request) {
        String toNumber = request.getPhoneNumber();
        String inputCode = request.getCode();

        if (smsSolaApiService.verifySms(toNumber,inputCode)) {
            return ResponseEntity.ok("인증 성공!");
        } else {
            return ResponseEntity.badRequest().body("인증번호가 틀렸거나 만료되었습니다.");
        }
    }
}

