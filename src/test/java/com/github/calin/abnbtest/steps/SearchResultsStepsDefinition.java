package com.github.calin.abnbtest.steps;

import com.github.calin.abnbtest.DateUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultsStepsDefinition extends SpringBaseStep {

    @Then("verify that Results Header dates are correct")
    public void verifyThatResultsHeaderDatesAreCorrect() {
        List<Integer> searchHeaderDaysOfTheYear =
                DateUtils.getDaysOfTheYear(searchResultsPage.getSearchDates());
        List<Integer> checkInCheckoutDatesOfTheYear =
                DateUtils.getDaysOfTheYear( Arrays.asList(testContext.getCheckInDate(),testContext.getCheckoutDate()));
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
                searchResultsPage
                .getListedPropertiesCapacity()
                .stream()
                .anyMatch(capacity -> capacity < expectedNumberOfGuests));
    }

    @Then("verify that properties displayed have more than {int} bedrooms")
    public void verifyThatPropertiesDisplayedHaveMoreThanBedrooms(int bedrooms) {
        List<Integer> bedroomsResults = searchResultsPage.getListedPropertiesBedrooms();
        Assert.assertFalse(
                "Expected all properties to have bedrooms >= "  + bedrooms +  " but found: " + bedroomsResults,
                bedroomsResults
                        .stream()
                        .anyMatch(capacity -> capacity < bedrooms));
    }

    @When("I click the {int}(\\w+) search result the property is open in new tab")
    public void iClickTheStSearchResultThePropertyIsOpenInNewTab(int position) {
        testContext.setOriginalWindowHandle(webDriver.getWindowHandle());
        searchResultsPage.clickResultAtPosition(position);
        List<String> tabs2 = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs2.get(1));
    }
}
