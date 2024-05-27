package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.pet.Category;
import com.github.valentina810.dto.pet.Pet;
import com.github.valentina810.dto.pet.Tag;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class PetControllerTestData {
    static Stream<Arguments> PetControllerTestDataProvider() {
        return of(
                arguments("питомец со всеми полями",
                        PetControllerApiRequest.builder()
                                .pet(Pet.builder()
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
                                        .build())
                                .build(),
                        PetControllerExpectedData
                                .builder()
                                .statusCode(SC_OK)
                                .pet(Pet.builder()
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
                                        .build())
                                .build()),
                arguments("питомец без категории",
                        PetControllerApiRequest.builder()
                                .pet(Pet.builder()
                                        .id(20)
                                        .name("Василий")
                                        .photoUrls(List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/"))
                                        .tags(List.of(Tag.builder()
                                                .id(1)
                                                .name("cats")
                                                .build()))
                                        .status("available")
                                        .build())
                                .build(),
                        PetControllerExpectedData
                                .builder()
                                .statusCode(SC_OK)
                                .pet(Pet.builder()
                                        .id(20)
                                        .name("Василий")
                                        .photoUrls(List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/"))
                                        .tags(List.of(Tag.builder()
                                                .id(1)
                                                .name("cats")
                                                .build()))
                                        .status("available")
                                        .build())
                                .build()),
                arguments("питомец без имени",
                        PetControllerApiRequest.builder()
                                .pet(Pet.builder()
                                        .id(20)
                                        .category(Category.builder()
                                                .id(1)
                                                .name("Кошки").build())
                                        .photoUrls(List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/"))
                                        .tags(List.of(Tag.builder()
                                                .id(1)
                                                .name("cats")
                                                .build()))
                                        .status("available")
                                        .build())
                                .build(),
                        PetControllerExpectedData
                                .builder()
                                .statusCode(SC_OK)
                                .pet(Pet.builder()
                                        .id(20)
                                        .category(Category.builder()
                                                .id(1)
                                                .name("Кошки").build())
                                        .photoUrls(List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/"))
                                        .tags(List.of(Tag.builder()
                                                .id(1)
                                                .name("cats")
                                                .build()))
                                        .status("available")
                                        .build())
                                .build()),
                arguments("питомец без ссылок на фото",
                        PetControllerApiRequest.builder()
                                .pet(Pet.builder()
                                        .id(20)
                                        .category(Category.builder()
                                                .id(1)
                                                .name("Кошки").build())
                                        .name("Василий")
                                        .tags(List.of(Tag.builder()
                                                .id(1)
                                                .name("cats")
                                                .build()))
                                        .status("available")
                                        .build())
                                .build(),
                        PetControllerExpectedData
                                .builder()
                                .statusCode(SC_OK)
                                .pet(Pet.builder()
                                        .id(20)
                                        .category(Category.builder()
                                                .id(1)
                                                .name("Кошки").build())
                                        .photoUrls(List.of())
                                        .name("Василий")
                                        .tags(List.of(Tag.builder()
                                                .id(1)
                                                .name("cats")
                                                .build()))
                                        .status("available")
                                        .build())
                                .build()),
                arguments("питомец без тэгов",
                        PetControllerApiRequest.builder()
                                .pet(Pet.builder()
                                        .id(20)
                                        .category(Category.builder()
                                                .id(1)
                                                .name("Кошки").build())
                                        .name("Василий")
                                        .photoUrls(List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/"))
                                        .status("available")
                                        .build())
                                .build(),
                        PetControllerExpectedData
                                .builder()
                                .statusCode(SC_OK)
                                .pet(Pet.builder()
                                        .id(20)
                                        .category(Category.builder()
                                                .id(1)
                                                .name("Кошки").build())
                                        .name("Василий")
                                        .tags(List.of())
                                        .photoUrls(List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/"))
                                        .status("available")
                                        .build())
                                .build()),
                arguments("питомец без статуса",
                        PetControllerApiRequest.builder()
                                .pet(Pet.builder()
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
                                        .build())
                                .build(),
                        PetControllerExpectedData
                                .builder()
                                .statusCode(SC_OK)
                                .pet(Pet.builder()
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
                                        .build())
                                .build()),
                arguments("питомец только с именем",
                        PetControllerApiRequest.builder()
                                .pet(Pet.builder()
                                        .id(20)
                                        .name("Василий")
                                        .build())
                                .build(),
                        PetControllerExpectedData
                                .builder()
                                .statusCode(SC_OK)
                                .pet(Pet.builder()
                                        .id(20)
                                        .name("Василий")
                                        .photoUrls(List.of())
                                        .tags(List.of())
                                        .build())
                                .build())
        );
    }
}