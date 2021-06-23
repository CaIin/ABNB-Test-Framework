package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.DateUtils;
import com.github.calin.abnbtest.config.FrameworkContext;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SearchResultsPage extends BasePage {
  @Value("${locator.searchResults.header}")
  String headerLocator;

  @Value("${locator.searchResults.propertyCapacity}")
  String propertyCapacityLocator;

  @Value("${locator.searchResults.propertyBedrooms}")
  String propertyBedroomsCapacitor;

  @Value("${locator.searchResults.moreFilters}")
  String moreFiltersLocator;

  @Value("${locator.searchResults.filters}")
  String filtersLocator;

  @Value("${regex.searchResultsHeader.guestsRegex}")
  String guestsRegEx;

  @Value("${regex.searchResultsHeader.datesRegex.regexp}")
  String datesRegex;

  @Value("${locator.searchResults.resultItem}")
  String resultItemLocator;

  @Value("${locator.searchResults.resultPropertyTitle}")
  String propertyTitleLocator;

  @Value("${locator.searchResults.priceLocator}")
  String priceLocator;

  @Autowired
  public SearchResultsPage(FrameworkContext context) {
    super(context);
  }

  /**
   * Returns the Search header text.
   *
   * @return the Search Header text (as displayed in the web page).
   */
  public String getSearchHeaderText() {
    return getText(getByFor(headerLocator));
  }

  /**
   * Retrieves the number of guests displayed in the search header for current results. Internally
   * uses a RegEx for identifying the number displayed (i.e: 3 guests -> 3).
   *
   * @return the number of guests displayed in the Search Header/
   */
  public int getNumberOfGuests() {
    Pattern p = Pattern.compile(guestsRegEx, Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(getSearchHeaderText());
    if (m.find()) {
      return Integer.parseInt(m.group(1));
    }
    return -1;
  }

  /**
   * Uses NLP to try to identify the dates from the Search Header
   *
   * @return a list of identified dates from the text.
   */
  public List<Date> getSearchDates() {
    String datesText = "";
    Pattern p = Pattern.compile(datesRegex, Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(getSearchHeaderText());
    if (m.find()) {
      datesText = m.group();
    }
    return DateUtils.parseAllDatesFromText(datesText);
  }

  /**
   * Gets the capacity count for results on the first page
   *
   * @return list of property capacity count for the first page of results
   */
  public List<Integer> getListedPropertiesCapacity() {
    return getElementsText(getByFor(propertyCapacityLocator)).stream()
        .map(text -> text.replaceAll("[^\\d]", ""))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  /**
   * Gets the bedroom count for results on the first page
   *
   * @return list of bedroom counts for the first page of results
   */
  public List<Integer> getListedPropertiesBedrooms() {
    return getElementsText(getByFor(propertyBedroomsCapacitor)).stream()
        .map(text -> text.replaceAll("[^\\d]", ""))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  /**
   * Opens Filter Page Modal. If More Filters button is not displayed we open the page via Filters
   * Page.
   */
  public void openMoreFiltersPage() {
    if (webDriver.findElements(getByFor(moreFiltersLocator)).isEmpty()) {
      click(getByFor(filtersLocator));
    } else {
      click(getByFor(moreFiltersLocator));
    }
  }

  /**
   * Clicks the search result at position. Starts from 1.
   *
   * @param position Result index to click on. The counting starts from 1 (i.e 1st).
   */
  public void clickResultAtPosition(final int position) {
    click(getResultAtPosition(position));
  }

  /**
   * Returns the Property(as in accommodation) from search results at position.
   *
   * @param position the index of the property in the Search results list (first index is 1)
   * @return property e
   */
  public String getPropertyTitle(int position) {
    return getText(getResultAtPosition(position).findElement(getByFor(propertyTitleLocator)));
  }

  /**
   * Hovers mouse over the result at index.
   *
   * @param index Index in the Search results list (First index is 1)
   */
  public void hoverOverSearchResult(int index) {
    Actions actions = new Actions(webDriver);
    actions.moveToElement(getResultAtPosition(index)).build().perform();
  }

  /**
   * Gets the price per night for Property at index from search results.
   *
   * @param index location of the Property in the search results
   * @return price per night
   */
  public double getPricePerNight(int index) {
    return Double.parseDouble(
        getText(getResultAtPosition(index).findElement(getByFor(priceLocator)))
            .replaceAll("[^\\d]", ""));
  }

  private WebElement getResultAtPosition(int index) {
    try {
      return webDriver.findElements(getByFor(resultItemLocator)).stream()
          .skip(index - 1)
          .filter(WebElement::isDisplayed)
          .findFirst()
          .orElseThrow(
              () -> new NoSuchElementException("Could not find the Element at index " + index));
    } catch (StaleElementReferenceException ex) {
      ex.printStackTrace();
      return getResultAtPosition(index);
    }
  }

  /**
   * Clicks the search result at position. Starts from 1. Waits until the second tab is opened and
   * switches to it.
   *
   * @param position Result index to click on. The counting starts from 1 (i.e 1st).
   */
  public void clickResultAtPositionAndSwitchToTab(final int position) {
    clickResultAtPosition(position);
    switchToSecondTab();
  }
}
