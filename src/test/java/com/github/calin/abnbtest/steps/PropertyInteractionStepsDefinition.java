package com.github.calin.abnbtest.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class PropertyInteractionStepsDefinition extends SpringBaseStep {

    @And("I open the Amenities Pop-up for the Property")
    public void iOpenTheAmenitiesPopUpForTheProperty() {
        propertyDetailsPage.clickShowAllAmenities();
    }

    @Then("{string} is present in the Amenities Pop-up")
    public void isPresentInTheAmenitiesPopUp(String amenity) {
        Assert.assertTrue("Amenity " + amenity + " is not displayed",
                propertyDetailsPage.isAmenityDisplayed(amenity));
    }
}
