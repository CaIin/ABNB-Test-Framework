package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.config.FrameworkContext;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyDetailsPage extends BasePage {


    @Value("${locator.propertyDetails.showAmenities}") String showAmenitiesLocator;
    @Value("${locator.propertyDetails.amenityTemplate}") String amenityTemplate;

    @Autowired
    public PropertyDetailsPage(FrameworkContext context) {
        super(context);
    }

    public void clickShowAllAmenities() {
        click(getByFor(showAmenitiesLocator));
    }

    public boolean isAmenityDisplayed(String amenity) {
        By amenityBy = getByFor(String.format(amenityTemplate, amenity));
        scrollElementIntoView(amenityBy);
        return webDriver.findElement(amenityBy).isDisplayed();
    }
}
