package com.example.service.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SliceResponse<T> {
    private List<T> content;
    private boolean hasNext;
}
