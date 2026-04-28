package com.example.service.common.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.http.HttpStatus;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonResponse<T> {

    int status;
    String message;
    T data;

    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.SC_OK)
                .message("success")
                .data(data)
                .build();
    }

    public static <T> CommonResponse<T> created(T data) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.SC_CREATED)
                .message("created")
                .data(data)
                .build();
    }

    public static <T> CommonResponse<T> fail(int status,String message) {
        return CommonResponse.<T>builder()
                .status(status)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> CommonResponse<T> of(SuccessCode code,T data) {
      return CommonResponse.<T>builder()
              .status(code.getStatus().value())
              .message(code.getMessage())
              .data(data)
              .build();
    };

}
