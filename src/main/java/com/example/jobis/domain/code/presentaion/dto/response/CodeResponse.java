package com.example.jobis.domain.code.presentaion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeResponse {
    private final Long code;
    private final String keyword;
}
