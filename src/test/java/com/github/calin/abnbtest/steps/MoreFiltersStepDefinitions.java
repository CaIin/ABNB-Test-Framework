package com.github.calin.abnbtest.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class MoreFiltersStepDefinitions extends SpringBaseStep {

  @When("open More Filters")
  public void theUserClicksMoreFilters() {
    searchResultsPage.openMoreFiltersPage();
  }

  @And("select {int} bedrooms")
  public void iSelectBedrooms(int noOfBedrooms) {
    moreFiltersPage.setNoOfBedrooms(noOfBedrooms);
  }

  @And("click {string} in the Filter modal")
  public void selectFromTheFacilitiesSection(String facility) {
    moreFiltersPage.clickFacility(facility);
  }

  @When("click Show Stays")
  public void clickShowStays() {
    moreFiltersPage.clickShowStays();
  }
}
