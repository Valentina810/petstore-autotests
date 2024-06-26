package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.pet.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class AddPetExpected {
    private int statusCode;
    private Pet pet;
}