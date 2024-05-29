package com.github.valentina810.data.petcontroller;

import com.github.valentina810.dto.pet.Category;
import com.github.valentina810.dto.pet.Pet;
import com.github.valentina810.dto.pet.Tag;
import com.github.valentina810.dto.response.ResponseMessage;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.stream.Stream.of;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class PetControllerTestData {
    private static final AtomicLong petIdGenerator = new AtomicLong(System.currentTimeMillis());

    private static final Long PET_TEST_ID = 20L;
    private static final Long PET_INCORRECT_TEST_ID = -1L;

    public static final Category CATEGORY = Category.builder()
            .id(1)
            .name("Koшка")
            .build();

    private static final List<String> ONE_PHOTO_URL = List.of("https://javarush.com/prices/my");
    private static final List<String> TWO_PHOTO_URLS = List.of("https://javarush.com/prices/my", "https://skill-debugger.marusia.mail.ru/");
    private static final List<Tag> ONE_TAG = List.of(
            Tag.builder()
                    .id(1)
                    .name("cats")
                    .build());
    private static final List<Tag> TWO_TAGS = List.of(
            Tag.builder()
                    .id(1)
                    .name("cats")
                    .build(),
            Tag.builder()
                    .id(1)
                    .name("british")
                    .build());
    private static final Pet SIMPLE_PET = createPet(CATEGORY, "Василий", ONE_PHOTO_URL, ONE_TAG, "available");

    static Stream<Arguments> addPetDataProvider() {
        return of(
                arguments("питомец со всеми полями",
                        createRequest(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, ONE_TAG, "available")),
                        createExpectedData(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, ONE_TAG, "available"))),
                arguments("питомец без категории",
                        createRequest(createPet(null, "Василий", ONE_PHOTO_URL, ONE_TAG, "available")),
                        createExpectedData(createPet(null, "Василий", ONE_PHOTO_URL, ONE_TAG, "available"))),
                arguments("питомец без имени",
                        createRequest(createPet(CATEGORY, null, ONE_PHOTO_URL, ONE_TAG, "available")),
                        createExpectedData(createPet(CATEGORY, null, ONE_PHOTO_URL, ONE_TAG, "available"))),
                arguments("питомец без ссылок на фото",
                        createRequest(createPet(CATEGORY, "Василий", null, ONE_TAG, "available")),
                        createExpectedData(createPet(CATEGORY, "Василий", List.of(), ONE_TAG, "available"))),
                arguments("питомец c несколькими ссылками на фото",
                        createRequest(createPet(CATEGORY, "Василий", TWO_PHOTO_URLS, ONE_TAG, "available")),
                        createExpectedData(createPet(CATEGORY, "Василий", TWO_PHOTO_URLS, ONE_TAG, "available"))),
                arguments("питомец без тэгов",
                        createRequest(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, null, "available")),
                        createExpectedData(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, List.of(), "available"))),
                arguments("питомец с несколькими тегами",
                        createRequest(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, TWO_TAGS, "available")),
                        createExpectedData(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, TWO_TAGS, "available"))),
                arguments("питомец без статуса",
                        createRequest(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, ONE_TAG, null)),
                        createExpectedData(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, ONE_TAG, null))),
                arguments("питомец только с именем",
                        createRequest(createPet(null, "Василий", null, null, null)),
                        createExpectedData(createPet(null, "Василий", List.of(), List.of(), null)))
        );
    }

    static Stream<Arguments> getPetPositiveDataProvider() {
        return of(
                arguments("питомец с указанным id существует",
                        createRequest(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, ONE_TAG, "available")),
                        createExpectedData(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, ONE_TAG, "available"))));
    }

    private static Pet createPet(Category category, String name, List<String> photoUrls, List<Tag> tags, String status) {
        return Pet.builder()
                .id(petIdGenerator.getAndIncrement())
                .category(category)
                .name(name)
                .photoUrls(photoUrls)
                .tags(tags)
                .status(status)
                .build();
    }

    private static AddPetApiRequest createRequest(Pet pet) {
        return AddPetApiRequest.builder()
                .pet(pet)
                .build();
    }

    private static AddPetExpected createExpectedData(Pet pet) {
        return AddPetExpected.builder()
                .statusCode(SC_OK)
                .pet(pet)
                .build();
    }

    static Stream<Arguments> getPetNegativeDataProvider() {

        return of(
                arguments("питомец указанным id не существует",
                        GetPetApiRequest.builder()
                                .idPet(PET_INCORRECT_TEST_ID)
                                .build(),
                        GetPetApiExpected.builder()
                                .statusCode(SC_NOT_FOUND)
                                .responseMessage(ResponseMessage.builder()
                                        .code(1)
                                        .type("error")
                                        .message("Pet not found")
                                        .build())
                                .build()));
    }

    static Stream<Arguments> updatePetDataProvider() {
        return of(
                createUpdateArgument("проверка обновления имени", pet -> {
                    pet.setName("NewName");
                    return pet;
                }),
                createUpdateArgument("проверка обновления категории", pet -> {
                    pet.setCategory(Category.builder()
                            .id(2)
                            .name("dog")
                            .build());
                    return pet;
                }),
                createUpdateArgument("проверка обновления URL фото", pet -> {
                    pet.setPhotoUrls(TWO_PHOTO_URLS);
                    return pet;
                }),
                createUpdateArgument("проверка обновления тегов", pet -> {
                    pet.setTags(TWO_TAGS);
                    return pet;
                }),
                createUpdateArgument("проверка удаления тегов", pet -> {
                    pet.setTags(List.of());
                    return pet;
                }),
                createUpdateArgument("проверка обновления статуса", pet -> {
                    pet.setStatus("pending");
                    return pet;
                }),
                createUpdateArgument("проверка обновления статуса = null", pet ->
                {
                    pet.setStatus(null);
                    return pet;
                }),
                createUpdateArgument("проверка обновления всех полей", pet ->
                {
                    Pet newPet = createPet(Category.builder().id(7).name("Лучшие собаки").build(), "Шарик",
                            TWO_PHOTO_URLS, TWO_TAGS, "sold");
                    newPet.setId(pet.getId());
                    return newPet;
                })
        );
    }

    private static Arguments createUpdateArgument(String testName, UnaryOperator<Pet> updateOperation) {
        return arguments(testName,
                UpdatePetApiRequest.builder()
                        .pet(createPet(CATEGORY, "Василий", ONE_PHOTO_URL, ONE_TAG, "available"))
                        .updatePet((pet) -> {
                            Pet updatedPet = pet.deepCopy();
                            return updateOperation.apply(updatedPet);
                        })
                        .build(),
                UpdatePetApiExpected.builder()
                        .statusCode(SC_OK)
                        .build());
    }

    static Stream<Arguments> deletePetPositiveDataProvider() {
        return of(
                arguments("питомец с указанным id существует",
                        DeletePetApiRequest.builder()
                                .pet(SIMPLE_PET).build(),
                        DeletePetApiExpected.builder()
                                .statusCode(SC_OK)
                                .responseMessage(ResponseMessage.builder()
                                        .code(SC_OK)
                                        .type("unknown")
                                        .message(valueOf(PET_TEST_ID))
                                        .build())
                                .build()));
    }

    static Stream<Arguments> deletePetNegativeDataProvider() {
        return of(
                arguments("питомец с указанным id не существует",
                        DeletePetApiRequest.builder()
                                .idPet(PET_INCORRECT_TEST_ID).build(),
                        DeletePetApiExpected.builder()
                                .statusCode(SC_NOT_FOUND)
                                .build()));
    }
}