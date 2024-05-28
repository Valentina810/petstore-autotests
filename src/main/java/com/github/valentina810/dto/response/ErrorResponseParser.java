package com.github.valentina810.dto.response;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@UtilityClass
public class ErrorResponseParser {

    private final ErrorResponse emptyErrorResponse = ErrorResponse.builder().build();

    public static ErrorResponse parseErrorResponse(Response<?> response) {
        if (response.errorBody() == null) {
            return emptyErrorResponse;
        }
        try {
            return new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
        } catch (IOException | JsonSyntaxException e) {
            log.warn(e.getMessage());
            return emptyErrorResponse;
        }
    }
}