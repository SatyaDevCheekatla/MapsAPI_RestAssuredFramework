package org.satya.pageObjects;

import io.restassured.response.Response;
import org.satya.base.BasePage;
import org.satya.driver.RestDriver;
import org.satya.utils.TestContext;

public class UpdateLocation extends BasePage {

    private Response response;
    private static final String body = "updateLocation.json";

    public UpdateLocation(RestDriver driver, TestContext test) {
        super(driver, test);
    }

    public Response getResponse(String placeId){
        try {
            // Validate place_id
            if (placeId == null || placeId.trim().isEmpty()) {
                reportResult.fail("✗ place_id is null or empty. Cannot update location details.");
                throw new RuntimeException("place_id is null or empty. Make sure to add a place first before updating it.");
            }

            String requestBody = fetchBody(body).replace("${placeid}", placeId);
            apiObject.addHeaders("Content-Type", "application/json");
            apiObject.addHeaders("Accept", "application/json");
            apiObject.addQueryParams("key", "qaclick123");
            apiObject.setUrl("/maps/api/place/update/json");
            apiObject.setBody(requestBody);
            apiObject.setMethod("PUT");

            response = sentRequest();

            if(response.statusCode() == 200){
                reportResult.pass("✓ Successfully updated location - Status Code: " + response.statusCode());
                reportResult.pass("Response Body: " + response.prettyPrint());
            }
        }catch (Exception e){
            reportResult.fail("✗ Error occurred while preparing updateLocation API request");
            reportResult.fail("Error Message: " + e.getMessage());
            throw new RuntimeException("Failed to prepare updateLocation API request", e);
        }

        return response;
    }
}
