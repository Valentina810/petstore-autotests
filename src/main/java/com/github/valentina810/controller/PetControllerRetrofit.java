package com.github.valentina810.controller;

import com.github.valentina810.base.RetrofitBase;
import com.github.valentina810.dto.pet.Pet;
import com.github.valentina810.dto.response.ErrorResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface PetControllerRetrofit {
    PetControllerRetrofit PET_CONTROLLER_RETROFIT = RetrofitBase
            .createRetrofit("urlPetstore", PetControllerRetrofit.class);

    @POST("pet/{petId}/uploadImage")
    Call<Pet> uploadPetImage(@Path("petId") Long petId);

    @POST("pet")
    Call<Pet> addPet(@Body Pet pet);

    @PUT("pet")
    Call<Pet> updatePet(@Body Pet pet);

    @GET("pet/findByStatus")
    Call<List<Pet>> findByStatus(@Query("status") String status);

    @GET("pet/{petId}")
    Call<Pet> getPet(@Path("petId") Long petId);

    @DELETE("pet/{petId}")
    Call<ErrorResponse> deletePet(@Path("petId") Long petId);
}