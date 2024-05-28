package com.github.valentina810.dto.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@EqualsAndHashCode
public class ErrorResponse {
    @Getter
    private int code;
    private String type;
    private String message;
}