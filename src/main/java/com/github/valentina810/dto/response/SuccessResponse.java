package com.github.valentina810.dto.response;

import lombok.ToString;

@ToString
public class SuccessResponse {
    private int code;
    private String type;
    private String message;
}