package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.config.FrameworkContext;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MapsPage extends BasePage {

  @Value("${locators.mapsPage.mapPinTemplate}")
  String mapPinTemplate;

  @Value("${locators.mapsPage.mapPopupTemplate}")
  String mapPopupTemplate;

  @Value("${locators.mapsPage.mapPopup.priceLocator}")
  String priceLocator;

  @Autowired
  public MapsPage(FrameworkContext context) {
    super(context);
  }

  /**
   * Returns the RGBA color code of the Map Pin on the Map for the propertyTitle.
   *
   * @param propertyTitle the propertyTitle Name
   * @return rgba color code (i.e: rgba(255,255,255,1))
   */
  public String getMapPinColor(String propertyTitle) {
    By mapPinLocator = getByFor(String.format(mapPinTemplate, propertyTitle));
    return webDriver.findElement(mapPinLocator).getCssValue("color");
  }

  /**
   * Checks if the Map Pin is displayed for property sent as an argument.
   *
   * @param propertyTitle - the Title of the property as displayed in the search results
   * @return true if the map pin is displayed.
   */
  public boolean isMapPinDisplayed(String propertyTitle) {
    return webDriver
        .findElement(getByFor(String.format(mapPinTemplate, propertyTitle)))
        .isDisplayed();
  }

  /**
   * Clicks Map pin for property sent as argument
   *
   * @param propertyTitle title of the property to be clicked.
   */
  public void clickMapPin(String propertyTitle) {
    click(getByFor(String.format(mapPinTemplate, propertyTitle)));
  }

  /**
   * Checks if the Map Popup for property is displayed.
   *
   * @param propertyTitle title of the property whose Map popup we check
   * @return true if map popup is displayed
   */
  public boolean isMapPopupDisplayed(String propertyTitle) {
    By mapPopupLocator = getByFor(String.format(mapPopupTemplate, propertyTitle));
    return webDriver.findElement(mapPopupLocator).isDisplayed();
  }

  /**
   * Gets the price per night for Property at index from Map Popup.
   *
   * @return price per night
   */
  public double getMapPopupPricePerNight() {
    return Double.parseDouble(getText(getByFor(priceLocator)).replaceAll("[^\\d]", ""));
  }
}
