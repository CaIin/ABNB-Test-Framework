package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.config.FrameworkContext;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MoreFiltersPage extends BasePage {

  @Value("${locator.moreFilters.bedroomsCount}")
  String bedroomCountLocator;

  @Value("${locator.moreFilters.addBedroom}")
  String addBedroomLocator;

  @Value("${locator.moreFilters.removeBedroom}")
  String removeBedroomLocator;

  @Value("${locator.moreFilters.showStays}")
  String showStaysLocator;

  @Value("${locator.moreFilters.facilityTemplate}")
  String poolFacilityTemplate;

  @Autowired
  public MoreFiltersPage(FrameworkContext context) {
    super(context);
  }

  /**
   * Gets the More Filters Current number of bedrooms selected.
   *
   * @return number of bedrooms as displayed in the More Filter room
   */
  public int getCurrentNumberOfBedrooms() {
    return Integer.parseInt(getText(getByFor(bedroomCountLocator)));
  }

  /**
   * Sets the More Filters Number of bedrooms for the current search.
   *
   * @param noOfBedrooms the number of bedrooms to be set in the filter
   */
  public void setNoOfBedrooms(int noOfBedrooms) {
    int currentNumberOfBedrooms = getCurrentNumberOfBedrooms();
    Runnable action =
        currentNumberOfBedrooms < noOfBedrooms ? this::addBedroom : this::removeBedroom;
    int actionCount = Math.abs(currentNumberOfBedrooms - noOfBedrooms);
    performActionFor(actionCount, action);
  }

  /**
   * Clicks the facility requested.
   *
   * @param facility the name of the facility to click.
   */
  public void clickFacility(String facility) {
    By by = getByFor(String.format(poolFacilityTemplate, facility));
    scrollElementIntoView(by);
    click(by);
  }

  /** Clicks the Show Stays button */
  public void clickShowStays() {
    click(getByFor(showStaysLocator));
  }

  private void removeBedroom() {
    click(getByFor(removeBedroomLocator));
  }

  private void addBedroom() {
    click(getByFor(addBedroomLocator));
  }
}
