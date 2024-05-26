package com.github.valentina810.utils;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static com.github.valentina810.utils.AllureAssert.fail;

public class ResponseExecutor {
    public static <T> Response<T> execute(Call<T> call) {
        try {
            return call.execute();
        } catch (IOException e) {
            fail("В процессе обработки запроса возникла ошибка:" + e.getMessage());
            return null;
        }
    }
}