package org.satya.stepDefinitions;

import io.cucumber.java.en.Given;
import org.satya.base.BasePage;
import org.satya.pageObjects.AddPlace;
import org.satya.driver.RestDriver;
import org.satya.hooks.Hooks;
import org.satya.pageObjects.GetLocation;
import org.satya.pageObjects.UpdateLocation;
import org.satya.utils.ScenarioContext;

public class MapsAPIStepDef extends BasePage {

    private AddPlace addPlace;
    private ScenarioContext scenarioContext;
    private GetLocation getLocation;
    private UpdateLocation updateLocation;



    public MapsAPIStepDef(RestDriver driver) {
        super(driver, Hooks.getTestContext());
        this.addPlace = new AddPlace(driver, testContext);
        getLocation = new GetLocation(driver, testContext);
        updateLocation = new UpdateLocation(driver, testContext);
        scenarioContext = ScenarioContext.getInstance();
    }

    @Given("I request the maps API to add the place in the server")
    public void i_request_the_maps_api_to_add_the_place_in_the_server() {

        addPlace.addPlaceApiResponse();
        scenarioContext.setValue("place_id", addPlace.getPlaceId());
        System.out.println("Place ID: " + scenarioContext.getValue("place_id"));
    }

    @Given("I request the maps API to get the location details from the server")
    public void iRequestTheMapsAPIToGetTheLocationDetailsFromTheServer() {
        getLocation.getResponse(scenarioContext.getValue("place_id"));
    }

    @Given("I request the maps API to update the location details in the server")
    public void iRequestTheMapsAPIToUpdateTheLocationDetailsInTheServer() {
       updateLocation.getResponse(scenarioContext.getValue("place_id"));

    }
}


