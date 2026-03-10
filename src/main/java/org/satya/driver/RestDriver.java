package org.satya.driver;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestDriver {

    private RequestSpecification request;
    private Response response;

    public RestDriver(){
        request = RestAssured.given().relaxedHTTPSValidation();
    }

    public RequestSpecification requestSpecification(){

        return request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
