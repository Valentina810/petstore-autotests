package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.pet.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PetControllerApiRequest {
    private String testName;
    private Pet pet;
}