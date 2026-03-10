package org.satya.pageObjects;

import io.restassured.response.Response;
import org.satya.base.BasePage;
import org.satya.driver.RestDriver;
import org.satya.utils.TestContext;
import org.testng.Assert;

public class GetLocation extends BasePage {

    private Response response;
    private APIValidations apiValidations;

    public GetLocation(RestDriver driver, TestContext test) {
        super(driver, test);
        apiValidations = new APIValidations(driver, test);
    }

    public Response getResponse(String placeId){
        try{
            // Validate place_id
            if (placeId == null || placeId.trim().isEmpty()) {
                reportResult.fail("✗ place_id is null or empty. Cannot retrieve location details.");
                Assert.fail("place_id is null or empty. Make sure to add a place first before retrieving it.");
            }

            apiObject.addHeaders("content-type","application/json");
            apiObject.addHeaders("accept","application/json");
            apiObject.addQueryParams("key","qaclick123");
            apiObject.addQueryParams("place_id", placeId);
            apiObject.setMethod("GET");
            apiObject.setUrl("/maps/api/place/get/json");

            response = sentRequest();
            if(response.statusCode() == 200){
                reportResult.pass("✓ Successfully retrieved location details - Status Code: " + response.statusCode());
                reportResult.pass("Response Body: " + response.prettyPrint());
                reportResult.pass("Response Headers: " + response.getStatusLine());
                //apiValidations.validateResponseBody(response, "getLocations.json");
            }else{
                reportResult.fail("✗ Failed to retrieve location details - Status Code: " + response.statusCode());
                reportResult.fail("Response Body: " + response.prettyPrint());
                reportResult.fail("Response Headers: " + response.getStatusLine());
                Assert.fail("Failed to retrieve location details - Status Code: " + response.statusCode());
            }

        }catch (Exception e){
            reportResult.fail("✗ Error occurred while sending getLocation API request");
            reportResult.fail("Error Message: " + e.getMessage());
            throw new RuntimeException("Failed to send getLocation API request", e);
        }
        return response;
    }
}
