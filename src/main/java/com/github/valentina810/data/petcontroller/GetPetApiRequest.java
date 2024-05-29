package com.github.valentina810.data.petcontroller;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetPetApiRequest {
    private Long idPet;
}