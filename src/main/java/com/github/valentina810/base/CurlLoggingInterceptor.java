package com.github.valentina810.base;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.StepResult;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.stream.Collectors;

public class CurlLoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String curlCmd = buildCurlCommand(request);
        attachCurlToCurrentStep(curlCmd);
        return chain.proceed(request);
    }

    private String buildCurlCommand(Request request) throws IOException {
        String curlCmd = "curl -X " + request.method();
        String headers = request.headers().names().stream()
                .flatMap(name -> request.headers(name).stream()
                        .map(value -> String.format(" -H \"%s: %s\"", name, value)))
                .collect(Collectors.joining());
        String body = "";
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            Charset charset = StandardCharsets.UTF_8;
            MediaType contentType = request.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(StandardCharsets.UTF_8);
            }
            if (buffer.size() > 0 && charset != null) {
                String requestBody = buffer.readString(charset);
                body = " --data '" + requestBody + "'";
            }
        }
        String url = String.format(" \"%s\"", request.url());
        return curlCmd + headers + body + url;
    }

    private void attachCurlToCurrentStep(String curlCmd) {
        attach(AttachProperties.builder()
                .attach(curlCmd)
                .stepName("Отправить запрос")
                .attachName("CURL Command").build());
    }

    public static void attach(AttachProperties attachment) {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        String uuid = UUID.randomUUID().toString();
        lifecycle.startStep(uuid, new StepResult().setName(attachment.getStepName()).setStatus(io.qameta.allure.model.Status.PASSED));
        Allure.addAttachment(attachment.getAttachName(), "text/plain", attachment.getAttach());
        lifecycle.stopStep(uuid);
    }
}