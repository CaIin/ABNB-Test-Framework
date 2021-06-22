package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.config.FrameworkContext;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyDetailsPage extends BasePage {

  @Value("${locator.propertyDetails.showAmenities}")
  String showAmenitiesLocator;

  @Value("${locator.propertyDetails.amenityTemplate}")
  String amenityTemplate;

  @Autowired
  public PropertyDetailsPage(FrameworkContext context) {
    super(context);
  }

  /** Clicks on Show All Amenities Button in order to open the Amenities Modal Window. */
  public void clickShowAllAmenities() {
    click(getByFor(showAmenitiesLocator));
  }

  /**
   * Checks if amenity is present in the list of amenities of the Property
   *
   * @param amenity name of the Amenity to be searched. Case Sensitive.
   * @return true if amenity present int the Amenities Modal
   */
  public boolean isAmenityDisplayed(String amenity) {
    By amenityBy = getByFor(String.format(amenityTemplate, amenity));
    return webDriver.findElement(amenityBy).isDisplayed();
  }
}
