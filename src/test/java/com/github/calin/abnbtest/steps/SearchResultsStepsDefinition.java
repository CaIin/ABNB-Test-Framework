package com.github.calin.abnbtest.steps;

import com.github.calin.abnbtest.DateUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

public class SearchResultsStepsDefinition extends SpringBaseStep {

  @Then("verify that Results Header dates are correct")
  public void verifyThatResultsHeaderDatesAreCorrect() {
    List<Integer> searchHeaderDaysOfTheYear =
        DateUtils.getDaysOfTheYear(searchResultsPage.getSearchDates());
    List<Integer> checkInCheckoutDatesOfTheYear =
        DateUtils.getDaysOfTheYear(
            Arrays.asList(testContext.getCheckInDate(), testContext.getCheckoutDate()));
    Assert.assertEquals(
        "Expected check-in and check-out dates not found in search header",
        checkInCheckoutDatesOfTheYear,
        searchHeaderDaysOfTheYear);
  }

  @Then("verify that Results Header guest count is correct")
  public void verifyThatResultsHeaderGuestCountIsCorrect() {
    Assert.assertEquals(
        "Number Of guests retrieved does not match the number of guests configured",
        testContext.getNumberOfGuests(),
        searchResultsPage.getNumberOfGuests());
  }

  @Then("verify that Results Guest Capacity >= {int}")
  public void verifyThatResultsGuestCapacityNumberOfGuests(int expectedNumberOfGuests) {
    Assert.assertFalse(
        "Expected all properties to have capacity >= " + expectedNumberOfGuests,
        searchResultsPage.getListedPropertiesCapacity().stream()
            .anyMatch(capacity -> capacity < expectedNumberOfGuests));
  }

  @Then("verify that displayed properties bedrooms >= {int}")
  public void verifyThatPropertiesDisplayedHaveMoreThanBedrooms(int bedrooms) {
    Assert.assertFalse(
        "Expected all properties to have bedrooms >= " + bedrooms,
        searchResultsPage.getListedPropertiesBedrooms().stream()
            .anyMatch(capacity -> capacity < bedrooms));
  }

  @When("I click the {int}(\\w+) search result the property is open in new tab")
  public void iClickTheStSearchResultThePropertyIsOpenInNewTab(int index) {
    testContext.setOriginalWindowHandle(webDriver.getWindowHandle());
    searchResultsPage.clickResultAtPositionAndSwitchToTab(index);
  }

  @Given("the details for the {int}st property in the results list are saved")
  public void hoverOverTheStPropertyInTheResultsListAndGetItsDetails(int index) {
    testContext.setPropertyIndex(index);
    testContext.setPropertyTitle(searchResultsPage.getPropertyTitle(index));
    testContext.setPropertyPricePerNight(searchResultsPage.getPricePerNight(index));
  }
}
