package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.response.ErrorResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetPetApiExpected {
    private int statusCode;
    private ErrorResponse errorResponse;
}