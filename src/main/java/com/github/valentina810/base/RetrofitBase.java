package com.github.valentina810.base;

import com.github.valentina810.utils.PropertyLoader;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBase {

    public static <T> T createRetrofit(String propertyName, Class<T> clazz) {
        return new Retrofit.Builder()
                .baseUrl(PropertyLoader.loadAppProperty(propertyName))
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new CurlLoggingInterceptor())
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(clazz);
    }
}