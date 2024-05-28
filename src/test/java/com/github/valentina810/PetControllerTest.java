package com.github.valentina810;

import com.github.valentina810.data.petcontroller.AddPetApiRequest;
import com.github.valentina810.data.petcontroller.AddPetExpected;
import com.github.valentina810.data.petcontroller.GetPetApiExpected;
import com.github.valentina810.data.petcontroller.GetPetApiRequest;
import com.github.valentina810.dto.pet.Pet;
import com.github.valentina810.dto.response.ErrorResponse;
import com.github.valentina810.dto.response.ErrorResponseParser;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
    @Story("AddPet")
    @ParameterizedTest(name = "{0}")
    @MethodSource("com.github.valentina810.data.petcontroller.PetControllerTestData#addPetDataProvider")
    @DisplayName("Проверка создания питомца:")
    public void addPetTest(String testName, AddPetApiRequest request, AddPetExpected expected) {
        checkResponse(execute(PET_CONTROLLER_RETROFIT.addPet(request.getPet())), expected);
        checkResponse(execute(PET_CONTROLLER_RETROFIT.getPet(request.getPet().getId())), expected);

        execute(PET_CONTROLLER_RETROFIT.deletePet(request.getPet().getId()));
    }

    private void checkResponse(Response<Pet> response, AddPetExpected expected) {
        assertAll(
                () -> assertEquals(expected.getStatusCode(), response.code(), CHECK_RESPONSE_CODE),
                () -> assertEquals(expected.getPet(), response.body(), CHECK_RESPONSE_BODY)
        );
    }

    @Feature("PetController")
    @Story("GetPet")
    @ParameterizedTest(name = "{0}")
    @MethodSource("com.github.valentina810.data.petcontroller.PetControllerTestData#getPetPositiveDataProvider")
    @DisplayName("Проверка получения данных о питомце:")
    public void getPetPositiveTest(String testName, AddPetApiRequest request, AddPetExpected expected) {
        checkResponse(execute(PET_CONTROLLER_RETROFIT.addPet(request.getPet())), expected);
        checkResponse(execute(PET_CONTROLLER_RETROFIT.getPet(request.getPet().getId())), expected);

        execute(PET_CONTROLLER_RETROFIT.deletePet(request.getPet().getId()));
    }

    @Feature("PetController")
    @Story("GetPet")
    @ParameterizedTest(name = "{0}")
    @MethodSource("com.github.valentina810.data.petcontroller.PetControllerTestData#getPetNegativeDataProvider")
    @DisplayName("Проверка получения данных о питомце:")
    public void getPetNegativeTest(String testName, GetPetApiRequest request, GetPetApiExpected expected) {
        Response<Pet> response = execute(PET_CONTROLLER_RETROFIT.getPet(request.getIdPet()));
        ErrorResponse errorResponse = ErrorResponseParser.parseErrorResponse(response);
        assertAll(
                () -> assertEquals(expected.getStatusCode(), response.code(), CHECK_RESPONSE_CODE),
                () -> assertEquals(expected.getErrorResponse(), errorResponse, CHECK_RESPONSE_BODY)
        );
    }
}

