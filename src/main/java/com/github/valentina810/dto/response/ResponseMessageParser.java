package com.github.valentina810.dto.response;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@UtilityClass
public class ResponseMessageParser {

    private final ResponseMessage EMPTY_RESPONSE_MESSAGE = ResponseMessage.builder().build();

    public static ResponseMessage parseErrorResponse(Response<?> response) {
        if (response.errorBody() == null) {
            return EMPTY_RESPONSE_MESSAGE;
        }
        try {
            return new Gson().fromJson(response.errorBody().string(), ResponseMessage.class);
        } catch (IOException | JsonSyntaxException e) {
            log.warn(e.getMessage());
            return EMPTY_RESPONSE_MESSAGE;
        }
    }
}