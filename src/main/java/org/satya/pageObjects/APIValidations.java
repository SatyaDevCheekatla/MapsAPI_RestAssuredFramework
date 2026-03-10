package org.satya.pageObjects;

import io.restassured.response.Response;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.satya.base.BasePage;
import org.satya.driver.RestDriver;
import org.satya.utils.TestContext;
import org.skyscreamer.jsonassert.JSONAssert;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class APIValidations extends BasePage {

    public APIValidations(RestDriver driver, TestContext test) {
        super(driver, test);
    }


    public void validateStatusCode(int expectedStatusCode, Response response) {
        try {

            if (response.statusCode() == expectedStatusCode) {
                reportResult.pass("✓ Status code validation passed - Expected: " + expectedStatusCode + ", Actual: " + response.statusCode());
            } else {
                reportResult.fail("✗ Status code validation failed - Expected: " + expectedStatusCode + ", Actual: " + response.statusCode());
            }
        } catch (Exception e) {
            reportResult.fail("✗ Error occurred while validating status code");
            reportResult.fail("Error Message: " + e.getMessage());
            throw new RuntimeException("Failed to validate status code", e);
        }
    }

    public void ValidateResponseSchema(String schemaPath, Response response) {
        try {
            // Validate schema path is not null
            if (schemaPath == null || schemaPath.trim().isEmpty()) {
                reportResult.fail("✗ Schema path cannot be null or empty");
                throw new IllegalArgumentException("Schema path cannot be null or empty");
            }

            response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
            reportResult.pass("✓ Response schema validation passed against schema: " + schemaPath);
        } catch (Exception e) {
            reportResult.fail("✗ Error occurred while validating response schema");
            reportResult.fail("Error Message: " + e.getMessage());
            throw new RuntimeException("Failed to validate response schema", e);
        }
    }

    public static String getJson(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public void validateResponseBody(Response response, String responseBody){
        try{
            // Load response validation file from classpath resources
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String resourcePath = "responseValidation/" + responseBody;
            String expectedResponse = new String(
                Files.readAllBytes(Paths.get(classLoader.getResource(resourcePath).toURI()))
            );

            // Use asString() to get actual JSON content, not object reference
            String actualResponse = response.getBody().asString();
            JSONAssert.assertEquals(expectedResponse, actualResponse, true);
            reportResult.pass("✓ Response body validation passed against expected response is true");

        } catch (Exception e) {
            reportResult.fail("✗ Error occurred while validating response body");
            reportResult.fail("Error Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}