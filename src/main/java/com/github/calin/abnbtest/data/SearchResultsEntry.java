package com.github.calin.abnbtest.data;

public class SearchResultsEntry {
    String propertyTitle;
    double pricePerNight;
    int numberOfGuests;

    public SearchResultsEntry(String propertyTitle,
                              double pricePerNight,
                              int numberOfGuests,
                              int index) {
        this.propertyTitle = propertyTitle;
        this.numberOfGuests = numberOfGuests;
        this.pricePerNight = pricePerNight;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}
