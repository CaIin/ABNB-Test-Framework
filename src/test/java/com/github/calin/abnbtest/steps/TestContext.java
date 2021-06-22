package com.github.calin.abnbtest.steps;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TestContext {

  private String destination;
  private Date checkInDate;
  private Date checkoutDate;
  private int numberOfGuests;
  private String originalWindowHandle;
  private String propertyTitle;
  int propertyIndex;
  double propertyPricePerNight;

  public Date getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(Date checkInDate) {
    this.checkInDate = checkInDate;
  }

  public void setNumberOfGuests(int numberOfGuests) {
    this.numberOfGuests = numberOfGuests;
  }

  public int getNumberOfGuests() {
    return numberOfGuests;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getDestination() {
    return destination;
  }

  public void setCheckoutDate(Date checkoutDate) {
    this.checkoutDate = checkoutDate;
  }

  public Date getCheckoutDate() {
    return checkoutDate;
  }

  public void setOriginalWindowHandle(String originalWindowHandle) {
    this.originalWindowHandle = originalWindowHandle;
  }

  public String getOriginalWindowHandle() {
    return originalWindowHandle;
  }

  public void setPropertyTitle(String propertyTitle) {
    this.propertyTitle = propertyTitle;
  }

  public String getPropertyTitle() {
    return propertyTitle;
  }

  public int getPropertyIndex() {
    return propertyIndex;
  }

  public void setPropertyIndex(int propertyIndex) {
    this.propertyIndex = propertyIndex;
  }

  public void setPropertyPricePerNight(double propertyPricePerNight) {
    this.propertyPricePerNight = propertyPricePerNight;
  }

  public double getPropertyPricePerNight() {
    return propertyPricePerNight;
  }
}
