package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.pet.Pet;
import lombok.Builder;
import lombok.Getter;

import java.util.function.UnaryOperator;

@Builder
@Getter
public class UpdatePetApiRequest {
    private Pet pet;
    private UnaryOperator<Pet> updatePet;
}