package com.github.valentina810.controller;

import com.github.valentina810.base.RetrofitBase;
import com.github.valentina810.dto.pet.Pet;
import com.github.valentina810.dto.response.ResponseMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PetControllerRetrofit {
    PetControllerRetrofit PET_CONTROLLER_RETROFIT = RetrofitBase
            .createRetrofit("urlPetstore", PetControllerRetrofit.class);

    @POST("pet")
    Call<Pet> addPet(@Body Pet pet);

    @PUT("pet")
    Call<Pet> updatePet(@Body Pet pet);

    @GET("pet/{petId}")
    Call<Pet> getPet(@Path("petId") Long petId);

    @DELETE("pet/{petId}")
    Call<ResponseMessage> deletePet(@Path("petId") Long petId);
}