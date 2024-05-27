package com.github.valentina810.utils;

import com.github.valentina810.base.AttachProperties;
import com.github.valentina810.base.CurlLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static com.github.valentina810.utils.allure.AllureAssert.fail;

public class ResponseExecutor {

    public static <T> Response<T> execute(Call<T> call) {
        try {
            Response<T> execute = call.execute();
            CurlLoggingInterceptor.attach(
                    AttachProperties.builder()
                            .attach(execute + " \nbody=" + execute.body())
                            .stepName("Получен ответ")
                            .attachName("Response")
                            .build());
            return execute;
        } catch (IOException e) {
            fail("В процессе обработки запроса возникла ошибка:" + e.getMessage());
            return null;
        }
    }
}