package com.github.calin.abnbtest.steps;

import com.github.calin.abnbtest.pages.MapsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class MapStepsDefinitions extends SpringBaseStep {

  @Autowired MapsPage mapsPage;

  @Then("the property is displayed on the map")
  public void thePropertyIsDisplayedOnTheMap() {
    Assert.assertTrue(
        "Map Pin is not displayed", mapsPage.isMapPinDisplayed(testContext.getPropertyTitle()));
  }

  @When("hovering above the property, the color of the map pin changes")
  @When("Hover over that property")
  public void hoveringAboveThePropertyTheColorOfTheMapPinChanges() {
    String mapPinColorBeforeHover = mapsPage.getMapPinColor(testContext.getPropertyTitle());
    searchResultsPage.hoverOverSearchResult(testContext.getPropertyIndex());
    String mapPinColorAfterHover = mapsPage.getMapPinColor(testContext.getPropertyTitle());
    Assert.assertNotEquals(
        "Map Pin color did not change upon hovering over Property",
        mapPinColorBeforeHover,
        mapPinColorAfterHover);
  }

  @When("click on the map pin of the selected property")
  public void clickOnTheMapPinOfTheSelectedProperty() {
    mapsPage.clickMapPin(testContext.getPropertyTitle());
  }

  @Then("the map popup for the selected property is displayed")
  public void theMapPopupForTheSelectedPropertyIsDisplayed() {
    mapsPage.isMapPopupDisplayed(testContext.getPropertyTitle());
  }

  @And("the details in the map popup match the selected property")
  public void theDetailsInTheMapPopupMatchTheSelectedProperty() {
    System.out.println("bla");
  }

  @And("map pop price matches the search result price")
  public void mapPopPriceNightMatchesTheSearchResultPriceNight() {
    Assert.assertEquals(
        "The search results price per night did not match the map popup price",
        testContext.getPropertyPricePerNight(),
        mapsPage.getMapPopupPricePerNight(),
        1.0);
  }
}
