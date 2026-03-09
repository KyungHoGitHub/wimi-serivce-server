package com.example.service.domain.sms;

import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.service.DefaultMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsSolaApiService {

    @Value("${solapi.from-number}")
    private String fromNumber;

    private final DefaultMessageService messageService;
    private final StringRedisTemplate redisTemplate;

    public void sendVerificationSms(String toNumber) {

//        SecureRandom random = new SecureRandom();
//        String authCode = String.format("%06d", random.nextInt(1000000));
//
//        Message message = new Message();
//        message.setFrom(fromNumber);
//        message.setTo(toNumber);
//        message.setText("[wimi] 인증번호는 " + authCode + "입니다.");
//
//        try {
//            messageService.send(message);
//        } catch (Exception e) {
//           throw new RuntimeException("SMS 문자 발송 샐패",e);
//        }
        String authCode ="123456";


        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String key = "sms:verify:" + toNumber;
        vop.set(key,authCode, Duration.ofMinutes(60));
    }

    public  boolean verifySms(String toNumber, String authCode) {

        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        return authCode != null && authCode.equals(vop.get("sms:verify:" + toNumber));
    }
}
