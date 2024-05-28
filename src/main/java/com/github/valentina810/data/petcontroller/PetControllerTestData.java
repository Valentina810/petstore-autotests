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

    private static final Long petTestId = 20L;

    public static final Category category = Category.builder()
            .id(1)
            .name("Koшка")
            .build();

    private static final List<String> onePhotoUrl = List.of("https://javarush.com/prices/my");
    private static final List<String> twoPhotoUrls = List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/");
    private static final List<Tag> oneTag = List.of(
            Tag.builder()
                    .id(1)
                    .name("cats")
                    .build());
    private static final List<Tag> twoTags = List.of(
            Tag.builder()
                    .id(1)
                    .name("cats")
                    .build(),
            Tag.builder()
                    .id(1)
                    .name("british")
                    .build());

    static Stream<Arguments> AddPetDataProvider() {
        return of(
                arguments("питомец со всеми полями",
                        createRequest(createPet(category, "Василий", onePhotoUrl, oneTag, "available")),
                        createExpectedData(createPet(category, "Василий", onePhotoUrl, oneTag, "available"))),
                arguments("питомец без категории",
                        createRequest(createPet(null, "Василий", onePhotoUrl, oneTag, "available")),
                        createExpectedData(createPet(null, "Василий", onePhotoUrl, oneTag, "available"))),
                arguments("питомец без имени",
                        createRequest(createPet(category, null, onePhotoUrl, oneTag, "available")),
                        createExpectedData(createPet(category, null, onePhotoUrl, oneTag, "available"))),
                arguments("питомец без ссылок на фото",
                        createRequest(createPet(category, "Василий", null, oneTag, "available")),
                        createExpectedData(createPet(category, "Василий", List.of(), oneTag, "available"))),
                arguments("питомец c несколькими ссылками на фото",
                        createRequest(createPet(category, "Василий", twoPhotoUrls, oneTag, "available")),
                        createExpectedData(createPet(category, "Василий", twoPhotoUrls, oneTag, "available"))),
                arguments("питомец без тэгов",
                        createRequest(createPet(category, "Василий", onePhotoUrl, null, "available")),
                        createExpectedData(createPet(category, "Василий", onePhotoUrl, List.of(), "available"))),
                arguments("питомец с несколькими тегами",
                        createRequest(createPet(category, "Василий", onePhotoUrl, twoTags, "available")),
                        createExpectedData(createPet(category, "Василий", onePhotoUrl, twoTags, "available"))),
                arguments("питомец без статуса",
                        createRequest(createPet(category, "Василий", onePhotoUrl, oneTag, null)),
                        createExpectedData(createPet(category, "Василий", onePhotoUrl, oneTag, null))),
                arguments("питомец только с именем",
                        createRequest(createPet(null, "Василий", null, null, null)),
                        createExpectedData(createPet(null, "Василий", List.of(), List.of(), null)))
        );
    }

    private static Pet createPet(Category category, String name, List<String> photoUrls, List<Tag> tags, String status) {
        return Pet.builder()
                .id(petTestId)
                .category(category)
                .name(name)
                .photoUrls(photoUrls)
                .tags(tags)
                .status(status)
                .build();
    }

    private static PetControllerApiRequest createRequest(Pet pet) {
        return PetControllerApiRequest.builder()
                .pet(pet)
                .build();
    }

    private static PetControllerExpectedData createExpectedData(Pet pet) {
        return PetControllerExpectedData.builder()
                .statusCode(SC_OK)
                .pet(pet)
                .build();
    }
}