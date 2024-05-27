package com.github.valentina810.utils.allure;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.function.BiConsumer;

import static com.github.valentina810.utils.allure.AssertsMessages.MATCH;
import static io.qameta.allure.Allure.step;

public class AllureAssert {

    private static final BiConsumer<Object, Object> assertExecute = (expected, actual) ->
    {
        Executable exec = () -> Assertions.assertEquals(expected, actual);
        try {
            exec.execute();
        } catch (Throwable e) {
            throw new AssertionError(e);
        }
    };

    @Step("Fail: {0}")
    public static void fail(String message) {
        Executable exec = () -> Assertions.fail(message);
        try {
            exec.execute();
        } catch (Throwable throwable) {
            throw new AssertionError(throwable);
        }
    }

    public static <T> void assertEquals(T expected, T actual) {
        assertExecute.accept(expected, actual);
        step("Assert: Объект " + actual.getClass().getSimpleName() + MATCH);
    }

    @Step("Assert: {2}")
    public static <T> void assertEquals(T expected, T actual, String message) {
        assertExecute.accept(expected, actual);
        step("Assert: " + message);
    }

    @Step("Group assertions:")
    public static void assertAll(Executable... executables) {
        Assertions.assertAll(executables);
    }
}