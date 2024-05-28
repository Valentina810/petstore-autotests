package com.github.valentina810;

import com.github.valentina810.data.petcontroller.PetControllerApiRequest;
import com.github.valentina810.data.petcontroller.PetControllerExpectedData;
import com.github.valentina810.dto.pet.Pet;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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
    @MethodSource("com.github.valentina810.data.petcontroller.PetControllerTestData#AddPetDataProvider")
    @DisplayName("Проверка создания питомца:")
    public void addPetTest(String testName, PetControllerApiRequest request, PetControllerExpectedData expected) {
        checkResponse(execute(PET_CONTROLLER_RETROFIT.addPet(request.getPet())), expected);
        checkResponse(execute(PET_CONTROLLER_RETROFIT.getPet(request.getPet().getId())), expected);

        execute(PET_CONTROLLER_RETROFIT.deletePet(request.getPet().getId()));
    }

    private void checkResponse(Response<Pet> response, PetControllerExpectedData expected) {
        assertAll(
                () -> assertEquals(expected.getStatusCode(), response.code(), CHECK_RESPONSE_CODE),
                () -> assertEquals(expected.getPet(), response.body(), CHECK_RESPONSE_BODY)
        );
    }
}

