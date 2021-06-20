package com.github.calin.abnbtest.steps;

import com.github.calin.abnbtest.DateUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.util.Assert;

import java.util.Date;

public class ReservationStepsDefinition extends SpringBaseStep {
    @Given("I Open www.airbnb.com")
    public void iOpenWwwAirbnbCom() {
        searchHeaderPage.open();
    }

    @And("Select {string} as a location")
    public void selectAsALocation(String location) {
        testContext.setDestination(location);
        searchHeaderPage.setDestination(location);
    }

    @And("Pick a Check-In date {string}")
    public void pickACheckInDate(String checkIn) {
        Date checkInDate = DateUtils.parseDateNaturalLanguage(checkIn);
        Assert.notNull(checkInDate, "Could not parse the Check In date from input: " + checkIn);
        testContext.setCheckInDate(checkInDate);
        searchHeaderPage.setCheckInDate(checkInDate);
    }

    @And("Pick a Check-Out date {string} date")
    public void pickACheckOutDate(String checkOut) {
        Date checkoutDate = DateUtils.parseDateNaturalLanguage(checkOut, testContext.getCheckInDate());
        Assert.notNull(checkoutDate, "Could not parse the Checkout Date from input: " + checkOut);
        testContext.setCheckoutDate(checkoutDate);
        searchHeaderPage.setCheckoutDate(checkoutDate);
    }

    @And("select the number of guests as {int} adults, {int} child, {int} infants")
    public void addGuestsAdultsChildInfants(int adults, int children, int infants) {
        testContext.setNumberOfGuests(adults + children + infants);
        searchHeaderPage.addGuests(adults, children, infants);
    }

    @And("click Search")
    public void clickSearch() {
        searchHeaderPage.search();
    }
}
