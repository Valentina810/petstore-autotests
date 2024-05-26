package com.github.valentina810.utils;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class AllureAssert {
    @Step("Fail: {0}")
    public static void fail(String message) {
        Executable exec = () -> Assertions.fail(message);
        try {
            exec.execute();
        } catch (Throwable throwable) {
            throw new AssertionError(throwable);
        }
    }
}