package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.response.ResponseMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class DeletePetApiExpected {
    private int statusCode;
    @Setter
    private ResponseMessage responseMessage;
}