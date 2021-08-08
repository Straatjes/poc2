package pkg;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

public class ReqSpec {
    private static TestConfig testConfig = TestConfig.getInstance("https://restful-booker.herokuapp.com");
    private static String baseURI = testConfig.getBaseURI();

    public static FilterableRequestSpecification postBookingSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/booking").
                build();
    }

    public static FilterableRequestSpecification getBookingSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/booking").
                build();
    }

    public static FilterableRequestSpecification postAuthSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/auth").
                build();
    }

    public static FilterableRequestSpecification putBookingSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/booking").
                build();
    }

    public static FilterableRequestSpecification deleteBookingSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/booking").
                build();
    }
}
