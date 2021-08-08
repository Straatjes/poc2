package pkg;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import lombok.Getter;

import static io.restassured.mapper.ObjectMapperType.GSON;

public class TestConfig {

    private static TestConfig INSTANCE = null;
    @Getter
    private String baseURI = null;

    private TestConfig(String baseURI) {
        this.baseURI = baseURI;
        RestAssured.config = RestAssuredConfig.config().
                objectMapperConfig(new ObjectMapperConfig(GSON)).
                logConfig(new LogConfig().
                        enableLoggingOfRequestAndResponseIfValidationFails());
    }

    public static TestConfig getInstance(String baseURI) {
        if (INSTANCE == null) {
            synchronized (TestConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TestConfig(baseURI);
                }
            }
        }
        return INSTANCE;
    }

}
