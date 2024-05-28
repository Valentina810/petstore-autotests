package com.github.valentina810.utils.allure;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import static com.github.valentina810.utils.allure.AssertsMessages.MATCH;
import static io.qameta.allure.Allure.step;

public class AllureAssert {

    @FunctionalInterface
    private interface AssertCommand {
        void execute();
    }

    @Step("Fail: {0}")
    public static void fail(String message) {
        executeAssert(() -> Assertions.fail(message), "Fail: " + message);
    }

    public static <T> void assertEquals(T expected, T actual) {
        executeAssert(() -> Assertions.assertEquals(expected, actual), "Assert: Объект " + actual.getClass().getSimpleName() + MATCH);
    }

    @Step("Assert: {2}")
    public static <T> void assertEquals(T expected, T actual, String message) {
        executeAssert(() -> Assertions.assertEquals(expected, actual), "Assert: " + message);
    }

    @Step("Group assertions:")
    public static void assertAll(Executable... executables) {
        Assertions.assertAll(executables);
    }

    private static void executeAssert(AssertCommand assertCommand, String stepDescription) {
        try {
            assertCommand.execute();
            step(stepDescription);
        } catch (Throwable e) {
            throw new AssertionError(e);
        }
    }
}