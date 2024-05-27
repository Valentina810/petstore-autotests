package com.github.valentina810;

import com.github.valentina810.data.petcontroller.PetControllerApiRequest;
import com.github.valentina810.data.petcontroller.PetControllerExpectedData;
import com.github.valentina810.dto.pet.Pet;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import retrofit2.Call;
import retrofit2.Response;

import static com.github.valentina810.controller.PetControllerRetrofit.PET_CONTROLLER_RETROFIT;
import static com.github.valentina810.utils.ResponseExecutor.execute;
import static com.github.valentina810.utils.allure.AllureAssert.assertAll;
import static com.github.valentina810.utils.allure.AllureAssert.assertEquals;
import static com.github.valentina810.utils.allure.AssertsMessages.CHECK_RESPONSE_BODY;
import static com.github.valentina810.utils.allure.AssertsMessages.CHECK_RESPONSE_CODE;

public class PetControllerTest {

    @Feature("PetController")
    @ParameterizedTest(name = "{0}")
    @MethodSource("com.github.valentina810.data.petcontroller.PetControllerTestData#PetControllerTestDataProvider")
    @DisplayName("Проверка создания питомца:")
    public void addPetTest(String testName, PetControllerApiRequest request, PetControllerExpectedData expected) {
        checkResponse(PET_CONTROLLER_RETROFIT.addPet(request.getPet()), expected, request);
        checkResponse(PET_CONTROLLER_RETROFIT.getPet(request.getPet().getId()), expected, request);
    }

    private static void checkResponse(Call<Pet> PET_CONTROLLER_RETROFIT, PetControllerExpectedData expected, PetControllerApiRequest request) {
        Response<Pet> createdPet = execute(PET_CONTROLLER_RETROFIT);
        assertAll(
                () -> assertEquals(expected.getStatusCode(), createdPet.code(), CHECK_RESPONSE_CODE),
                () -> assertEquals(expected.getPet(), createdPet.body(), CHECK_RESPONSE_BODY)
        );
    }
}
