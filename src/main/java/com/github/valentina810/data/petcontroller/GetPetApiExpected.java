package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.response.ResponseMessage;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetPetApiExpected {
    private int statusCode;
    private ResponseMessage responseMessage;
}