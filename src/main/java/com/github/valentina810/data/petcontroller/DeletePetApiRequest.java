package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.pet.Pet;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeletePetApiRequest {
    private Long idPet;
    private Pet pet;
}