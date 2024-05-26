package com.github.valentina810;

import com.github.valentina810.dto.pet.Category;
import com.github.valentina810.dto.pet.Pet;
import com.github.valentina810.dto.pet.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static com.github.valentina810.controller.PetControllerRetrofit.PET_CONTROLLER_RETROFIT;
import static com.github.valentina810.utils.ResponseExecutor.execute;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetControllerTest {

    @Test
    @DisplayName("Проверка создания пользователя")
    public void addPetTest() {
        Pet pet = Pet.builder()
                .id(20)
                .category(Category.builder()
                        .id(1)
                        .name("Кошки").build())
                .name("Василий")
                .photoUrls(List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/"))
                .tags(List.of(Tag.builder()
                        .id(1)
                        .name("cats")
                        .build()))
                .status("available")
                .build();
        Response<Pet> execute = execute(PET_CONTROLLER_RETROFIT.addPet(pet));
        assertAll(
                () -> assertEquals(SC_OK, execute.code()),
                () -> assertEquals(pet, execute.body())
        );
    }
}
