package com.example.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // JWT 인증 설정
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("JWT");

        return new OpenAPI()
                .info(new Info()
                        .title("API 명세서")
                        .description("서비스 API 명세서입니다.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("개발팀")
                                .email("dev@example.com")))
                .components(new Components()
                        .addSecuritySchemes("JWT", securityScheme))
                .addSecurityItem(securityRequirement);  // 전체 API에 JWT 인증 적용
    }
}