package com.github.valentina810.base;

import com.github.valentina810.utils.PropertyLoader;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBase {

    public static <T> T createRetrofit(String propertyName, Class<T> clazz) {
        return new Retrofit.Builder()
                .baseUrl(PropertyLoader.loadAppProperty(propertyName))
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(clazz);
    }
}