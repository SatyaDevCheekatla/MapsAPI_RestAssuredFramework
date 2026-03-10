package org.satya.pageObjects;

import io.restassured.response.Response;
import org.satya.base.BasePage;
import org.satya.driver.RestDriver;
import org.satya.utils.TestContext;

public class AddPlace extends BasePage {

    private Response response;
    private String filename = "AddPlace.json";
    private APIValidations apiValidations;

    public AddPlace(RestDriver driver, TestContext test) {
        super(driver, test);
        apiValidations = new APIValidations(driver, test);
    }

    public Response addPlaceApiResponse(){
        try {
            String requestBody = fetchBody(filename);
            apiObject.setBody(requestBody);
            apiObject.setUrl("/maps/api/place/add/json");
            apiObject.addQueryParams("key","qaclick123");
            apiObject.addHeaders("Content-Type","application/json");
            apiObject.setMethod("POST");



            response = sentRequest();

            // Log response details
            reportResult.info("Status Code: " + response.statusCode());
            reportResult.info("Response Time: " + response.getTime() + "ms");
            reportResult.info("Response Body: " + response.prettyPrint());

            if(response.statusCode() == 200){
                reportResult.pass("✓ Successfully added place - Status Code: " + response.statusCode());
              //  apiValidations.validateResponseBody(response, "addPlace.json");
            }else{
                reportResult.fail("✗ Failed to add place - Status Code: " + response.statusCode());
                reportResult.fail("Response: " + response.prettyPrint());
            }

        }catch (Exception e){
            reportResult.fail("✗ Error occurred while sending addPlace API request");
            reportResult.fail("Error Message: " + e.getMessage());
            reportResult.fail("Exception: " + e.toString());
            throw new RuntimeException("Failed to send addPlace API request", e);
        }

        return response;
    }
    public String getPlaceId(){
        String placeId = null;
        try {
            placeId = response.jsonPath().getString("place_id");
            reportResult.info("Extracted place_id: " + placeId);
        } catch (Exception e) {
            reportResult.fail("✗ Failed to extract place_id from response");
            reportResult.fail("Error Message: " + e.getMessage());
            reportResult.fail("Exception: " + e.toString());
            throw new RuntimeException("Failed to extract place_id from response", e);
        }
        return placeId;
    }
}


