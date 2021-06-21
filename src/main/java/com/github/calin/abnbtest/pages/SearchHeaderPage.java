package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.config.FrameworkContext;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SearchHeaderPage extends BasePage {
    @Value("${homePage.url}") String pageUrl;
    @Value("${locators.homePage.destination}")  String destinationLocator;
    @Value("${locators.homePage.firstSuggestionTemplate}") String firstSuggestionTemplate;
    @Value("${locators.homePage.checkIn}")  String checkInLocator;
    @Value("${locators.homePage.checkOut}") String checkOutLocator;
    @Value("${locators.homePage.guests}") String guestsLocator;
    @Value("${locators.homePage.search}") String searchLocator;


    private final DatePickerPage datePickerPage;
    private final  GuestsPanelPage guestsPanelPage;


    @Autowired
    public SearchHeaderPage(FrameworkContext context,
                            DatePickerPage datePickerPage,
                            GuestsPanelPage guestsPanelPage) {
        super(context);
        this.datePickerPage = datePickerPage;
        this.guestsPanelPage = guestsPanelPage;
    }

    public void open() {
        navigate(pageUrl);
    }

    /**
     * Sets the location where we are searching for accommodation.
     *
     *
     */
    public void setDestination(String destination) {
        sendKeys(getByFor(destinationLocator), destination);
        click(getByFor(String.format(firstSuggestionTemplate, destination)));
    }

    /**
     * Selects Check-In Date from Date Picker
     * @param date check in date
     */
    public void setCheckInDate(Date date) {
        expandElement(checkInLocator);
        datePickerPage.selectDate(date);
    }

    /**
     * Selects Check-Out Date
     * @param date checkout date
     */
    public void setCheckoutDate(Date date) {
        expandElement(checkOutLocator);
        datePickerPage.selectDate(date);
    }

    /**
     * Configures the number of guests for current search
     * @param adults number of adults to be set
     * @param children number of children to be set
     * @param infants number of infants to be set
     */
    public void addGuests(int adults, int children, int infants) {
        expandElement(guestsLocator);
        guestsPanelPage.setNumberOfAdults(adults);
        guestsPanelPage.setNumberOfChildren(children);
        guestsPanelPage.setNumberOfInfants(infants);
    }

    /**
     * Clicks Search Button
     */
    public void search() {
        click(getByFor(searchLocator));
    }

    private void expandElement(String locator) {
        WebElement element = webDriver.findElement(getByFor(locator));
        if (element.getAttribute("aria-expanded") == null ||
                element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
            element.click();
        }

    }
}
