package com.github.valentina810.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

@UtilityClass
@Slf4j
public class PropertyLoader {
    private static final Properties properties = new Properties();

    static {
        String env = getEnv("ENV", "test");
        String fileName = "src/main/resources/application-" + env + ".properties";

        try (InputStream properties = Files.newInputStream(Paths.get(fileName))) {
            PropertyLoader.properties.load(properties);
        } catch (IOException e) {
            log.error("Не удалось загрузить свойства из ресурсного файла, ошибка {}", e.getMessage());
        }
    }

    public static String getEnv(String name, String defaultValue) {
        return ofNullable(getProperty(name))
                .or(() -> ofNullable(getenv(name)))
                .orElse(defaultValue);
    }

    public String loadAppProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}